package ru.netology.moneyTransferService.checker;

import org.springframework.stereotype.Component;
import ru.netology.moneyTransferService.exceptions.CardIsExpiredException;
import ru.netology.moneyTransferService.exceptions.InputDataException;
import ru.netology.moneyTransferService.exceptions.InvalidTransactionExceptions;
import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;

import java.util.Calendar;
import java.util.Date;

@Component
public class OperationChecker {
    public void checkOperation(Card cardFrom, Card cardTo) {
        checkForEquality(cardFrom, cardTo);
        checkValidTill(cardFrom);
        checkValidTill(cardTo);
        checkCurrency(cardFrom, cardTo);
    }

    public void checkingDataEntryCard(Card card, RequestForMoneyTransfer requestForMoneyTransfer) {
        if (card.getValidTill().compareTo(requestForMoneyTransfer.getCardFromValidTill()) != 0) {
            throw new InputDataException("Срок действия карты "
                    + card.getNumber() + " некорректный");
        }
        if (!card.getCVC().equals(requestForMoneyTransfer.getCardFromCVV())) {
            throw new InputDataException("Код CVV карты " + card.getNumber() + " некорректный");
        }

        if (!card.getCurrency().equals(requestForMoneyTransfer.getCurrency())) {
            throw new InputDataException("Карта " + card.getNumber() + " имеет валюту счета "
                    + card.getCurrency());
        }
    }

    private void checkForEquality(Card card1, Card card2) {
        if (card1.equals(card2)) {
            throw new InputDataException("Карта списания и перевода совпадают");
        }
    }

    private void checkValidTill(Card card) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        if (card.getValidTill().before(today)) {
            throw new CardIsExpiredException("Карта " + card.getNumber() + " просрочена");
        }
    }

    private void checkCurrency(Card card1, Card card2) {
        if (!card1.getCurrency().equals(card2.getCurrency())) {
            throw new InvalidTransactionExceptions("Карты имеют счета в разных валютах");
        }
    }

    public void checkAmountForReduceValue(TransferOperation operation) {
        if (operation.getCardFrom().getValue().compareTo(operation.getValueForReduce()) < 0) {
            throw new InvalidTransactionExceptions("Недостаточно средств для перевода");
        }
    }

    public void checkCompleteOperation(TransferOperation operation) {
        if (operation.isOperationSuccessful()) {
            throw new InvalidTransactionExceptions("Операция уже была выполнена");
        }
    }

    public void checkCode(String code, String confirmCode) {
        if (!code.equals(confirmCode)) {
            throw new InvalidTransactionExceptions("Код подтверждения неверный");
        }
    }
}
