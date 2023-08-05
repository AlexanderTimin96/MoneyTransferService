package ru.netology.moneyTransferService.checker;

import org.springframework.stereotype.Component;
import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;

import java.util.Calendar;
import java.util.Date;

@Component
public class OperationChecker {
    public boolean checkDataEntryCard(Card card, RequestForMoneyTransfer requestForMoneyTransfer) {
        if (card.getValidTill().compareTo(requestForMoneyTransfer.getCardFromValidTill()) != 0) {
            return false;
        }
        if (!card.getCVC().equals(requestForMoneyTransfer.getCardFromCVV())) {
            return false;
        }
        if (!card.getCurrency().equals(requestForMoneyTransfer.getCurrency())) {
            return false;
        }
        if (card.getNumber().equals(requestForMoneyTransfer.getCardToNumber())) {
            return false;
        }
        return true;
    }

    public boolean checkValidTill(Card card) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        return card.getValidTill().after(today);
    }

    public boolean checkCurrency(Card card1, Card card2) {
        return card1.getCurrency().equals(card2.getCurrency());
    }

    public boolean checkAmountForReduceValue(TransferOperation operation) {
        return operation.getCardFrom().getValue().compareTo(operation.getValueForReduce()) >= 0;
    }

    public boolean checkCompleteOperation(TransferOperation operation) {
        return operation.isOperationSuccessful();
    }

    public boolean checkCode(String code, String confirmCode) {
        return code.equals(confirmCode);
    }
}
