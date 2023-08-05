package ru.netology.moneyTransferService.checker;

import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;

public interface OperationChecker {
    boolean checkDataEntryCard(Card card, RequestForMoneyTransfer requestForMoneyTransfer);

    boolean checkValidTill(Card card);

    boolean checkCurrency(Card card1, Card card2);

    boolean checkAmountForReduceValue(TransferOperation operation);

    boolean checkCompleteOperation(TransferOperation operation);

    boolean checkCode(String code, String confirmCode);
}
