package ru.netology.moneyTransferService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.netology.moneyTransferService.checker.OperationChecker;
import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.response.ResponseTransfer;
import ru.netology.moneyTransferService.repository.MoneyTransferRepository;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    @Value("${commission}")
    private double COMMISSION;
    @Value("${confirmCode}")
    private final String CONFIRM_CODE = "0000";

    private final MoneyTransferRepository moneyTransferRepository;
    private final OperationChecker checker;

    public MoneyTransferServiceImpl(MoneyTransferRepository moneyTransferRepository, OperationChecker checker) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.checker = checker;
    }

    @Override
    public ResponseTransfer transferMoney(RequestForMoneyTransfer requestForMoneyTransfer) {
        Card cardFrom = moneyTransferRepository.findCardsInStorage(requestForMoneyTransfer.getCardFromNumber());
        Card cardTo = moneyTransferRepository.findCardsInStorage(requestForMoneyTransfer.getCardToNumber());

        checker.checkingDataEntryCard(cardFrom, requestForMoneyTransfer);
        checker.checkOperation(cardFrom, cardTo);
        TransferOperation operation = new TransferOperation(cardFrom, cardTo,
                requestForMoneyTransfer.getValue(), COMMISSION);
        checker.checkAmountForReduceValue(operation);

        return moneyTransferRepository.saveOperation(operation);
    }

    @Override
    public ResponseTransfer confirmOperation(RequestForConfirmOperation requestForConfirmOperation) {
        int id = Integer.parseInt(requestForConfirmOperation.getOperationId());
        TransferOperation operation = moneyTransferRepository.findOperation(id);

        checker.checkCompleteOperation(operation);
        checker.checkCode(requestForConfirmOperation.getCode(), CONFIRM_CODE);

        makeMoneyTransfer(operation);
        return new ResponseTransfer(requestForConfirmOperation.getOperationId());
    }

    private void makeMoneyTransfer(TransferOperation operation) {
        Card cardFrom = operation.getCardFrom();
        Card cardTo = operation.getCardTo();

        cardFrom.reduceValue(operation.getValueForReduce());
        cardTo.increaseValue(operation.getValueForIncrease());

        operation.setOperationSuccessful(true);
    }
}
