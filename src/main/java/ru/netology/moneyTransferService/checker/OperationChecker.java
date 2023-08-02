package ru.netology.moneyTransferService.checker;

import org.springframework.stereotype.Component;
import ru.netology.moneyTransferService.exceptions.CardIsExpiredException;
import ru.netology.moneyTransferService.exceptions.InputDataException;
import ru.netology.moneyTransferService.exceptions.InvalidTransactionExceptions;
import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.requestObject.RequestForMoneyTransfer;

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
            throw new InputDataException("The validity period of the card "
                    + card.getNumber() + " is not specified correctly");
        }
        if (!card.getCVC().equals(requestForMoneyTransfer.getCardFromCVV())) {
            throw new InputDataException("The CVV code of the card " + card.getNumber() + " is not correct");
        }

        if (!card.getCurrency().equals(requestForMoneyTransfer.getCurrency())) {
            throw new InputDataException("The card " + card.getNumber() + " has an account only in the currency "
                    + card.getCurrency());
        }
    }

    private void checkForEquality(Card card1, Card card2) {
        if (card1.equals(card2)) {
            throw new InputDataException("The receiving and debiting cards are the same");
        }
    }

    private void checkValidTill(Card card) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        if (card.getValidTill().before(today)) {
            throw new CardIsExpiredException("The card " + card.getNumber() + " is invalid");
        }
    }

    private void checkCurrency(Card card1, Card card2) {
        if (!card1.getCurrency().equals(card2.getCurrency())) {
            throw new InvalidTransactionExceptions("The cards have an account in different currencies");
        }
    }

    public void checkCode(Card card, String code) {
        if (!card.getCode().equals(code)) {
            throw new InvalidTransactionExceptions("Incorrect code");
        }
    }

    public void checkAmountForReduceValue(TransferOperation operation) {
        if (operation.getCardFrom().getValue().compareTo(operation.getValueForReduce()) < 0) {
            throw new InvalidTransactionExceptions("Insufficient funds for transfer");
        }
    }

    public void checkCompleteOperation(TransferOperation operation) {
        if (operation.isOperationSuccessful()) {
            throw new InvalidTransactionExceptions("The operation has already been performed");
        }
    }
}
