package ru.netology.moneyTransferService.service;

import org.springframework.stereotype.Service;
import ru.netology.moneyTransferService.checker.OperationChecker;
import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.requestObject.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.requestObject.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.repository.MoneyTransferRepository;

import java.math.BigDecimal;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    private final BigDecimal commission = new BigDecimal(0.01);
    private final MoneyTransferRepository moneyTransferRepository;
    private final OperationChecker checker;

    public MoneyTransferServiceImpl(MoneyTransferRepository moneyTransferRepository, OperationChecker checker) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.checker = checker;
    }

    @Override
    public String transferMoney(RequestForMoneyTransfer requestForMoneyTransfer) {
        Card cardFrom = moneyTransferRepository.findCardsInStorage(requestForMoneyTransfer.getCardFromNumber());
        Card cardTo = moneyTransferRepository.findCardsInStorage(requestForMoneyTransfer.getCardToNumber());

        checker.checkingDataEntryCard(cardFrom, requestForMoneyTransfer);
        checker.checkOperation(cardFrom, cardTo);
        TransferOperation operation = new TransferOperation(cardFrom, cardTo,
                requestForMoneyTransfer.getValue(), commission);
        checker.checkAmountForReduceValue(operation);

        return moneyTransferRepository.saveOperation(operation);
    }

    @Override
    public String confirmOperation(RequestForConfirmOperation requestForConfirmOperation) {
        int ID = Integer.parseInt(requestForConfirmOperation.getOperationId());
        TransferOperation operation = moneyTransferRepository.findOperation(ID);

        checker.checkCompleteOperation(operation);
        checker.checkCode(operation.getCardFrom(), requestForConfirmOperation.getCode());

        makeMoneyTransfer(operation);
        return requestForConfirmOperation.getOperationId();
    }

    private void makeMoneyTransfer(TransferOperation operation) {
        Card cardFrom = operation.getCardFrom();
        Card cardTo = operation.getCardTo();

        cardFrom.reduceValue(operation.getValueForReduce());
        cardTo.increaseValue(operation.getValueForIncrease());

        operation.setOperationSuccessful(true);
    }
}
