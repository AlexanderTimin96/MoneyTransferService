package ru.netology.moneyTransferService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.netology.moneyTransferService.checker.OperationChecker;
import ru.netology.moneyTransferService.checker.OperationCheckerImpl;
import ru.netology.moneyTransferService.exceptions.InputDataException;
import ru.netology.moneyTransferService.exceptions.InvalidTransactionExceptions;
import ru.netology.moneyTransferService.logger.TransferLogger;
import ru.netology.moneyTransferService.logger.TransferLoggerImpl;
import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.response.ResponseTransfer;
import ru.netology.moneyTransferService.repository.MoneyTransferRepository;

import java.util.Optional;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {
    @Value("${commission}")
    private double COMMISSION;
    @Value("${confirmCode}")
    private String CONFIRM_CODE;

    private final MoneyTransferRepository moneyTransferRepository;
    private final OperationChecker checker;
    private final TransferLogger logger;

    public MoneyTransferServiceImpl(MoneyTransferRepository moneyTransferRepository, OperationChecker checker,
                                    TransferLogger logger) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.checker = checker;
        this.logger = logger;
        logger.createFileLog();
    }

    @Override
    public ResponseTransfer transferMoney(RequestForMoneyTransfer requestForMoneyTransfer) {
        Optional<Card> optionalCardFrom = moneyTransferRepository
                .findCardsInStorage(requestForMoneyTransfer.getCardFromNumber());
        Optional<Card> optionalCardTo = moneyTransferRepository
                .findCardsInStorage(requestForMoneyTransfer.getCardToNumber());

        if (optionalCardFrom.isEmpty() || optionalCardTo.isEmpty()) {
            throwInputDataException(requestForMoneyTransfer, "Введенные данные карт некорректные!");
        }
        Card cardFrom = optionalCardFrom.get();
        Card cardTo = optionalCardTo.get();

        if (!checker.areCardDataCorrect(cardFrom, requestForMoneyTransfer)) {
            String str = "Введенные данные карт некорректные!";
            throwInputDataException(requestForMoneyTransfer, str);
        }
        if (!checker.isCardValid(cardFrom)) {
            String str = "Срок действия карты отправителя истек!";
            throwInvalidTransactionExceptions(requestForMoneyTransfer, str);
        }
        if (!checker.isCardValid(cardTo)) {
            String str = "Срок действия карты получателя истек!";
            throwInvalidTransactionExceptions(requestForMoneyTransfer, str);
        }
        if (!checker.areCurrenciesMatch(cardFrom, cardFrom)) {
            String str = "Карты имеют счета в разных валютах!";
            throwInvalidTransactionExceptions(requestForMoneyTransfer, str);
        }

        TransferOperation operation = new TransferOperation(cardFrom, cardTo,
                requestForMoneyTransfer.getValue(), COMMISSION);

        if (!checker.isEnoughMoney(operation)) {
            String str = "Недостаточно средств для перевода!";
            throwInvalidTransactionExceptions(operation, str);
        }

        return moneyTransferRepository.saveOperation(operation);
    }

    @Override
    public ResponseTransfer confirmOperation(RequestForConfirmOperation requestForConfirmOperation) {
        int id = Integer.parseInt(requestForConfirmOperation.getOperationId());
        TransferOperation operation = moneyTransferRepository.findOperation(id);

        if (checker.hasOperationBeenPerformed(operation)) {
            String str = "Операция была выполнена ранее!";
            throwInvalidTransactionExceptions(operation, str);
        }
        if (!checker.isCodeValid(requestForConfirmOperation.getCode(), CONFIRM_CODE)) {
            String str = "Код подтверждения операции неверный!";
            throwInvalidTransactionExceptions(operation, str);
        }

        makeMoneyTransfer(operation);
        return new ResponseTransfer(requestForConfirmOperation.getOperationId());
    }

    private void makeMoneyTransfer(TransferOperation operation) {
        Card cardFrom = operation.getCardFrom();
        Card cardTo = operation.getCardTo();

        cardFrom.reduceValue(operation.getValueForReduce());
        cardTo.increaseValue(operation.getValueForIncrease());

        operation.setOperationSuccessful(true);
        logger.logSuccessfulOperation(operation);
    }

    private void throwInputDataException(RequestForMoneyTransfer requestForMoneyTransfer, String str) {
        logger.logInvalidRequest(requestForMoneyTransfer, str);
        throw new InputDataException(str);
    }

    private void throwInvalidTransactionExceptions(RequestForMoneyTransfer requestForMoneyTransfer, String str) {
        logger.logInvalidRequest(requestForMoneyTransfer, str);
        throw new InvalidTransactionExceptions(str);
    }

    private void throwInvalidTransactionExceptions(TransferOperation operation, String str) {
        logger.logOperationCancel(operation, str);
        throw new InvalidTransactionExceptions(str);
    }
}
