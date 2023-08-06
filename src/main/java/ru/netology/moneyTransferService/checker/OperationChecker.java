package ru.netology.moneyTransferService.checker;

import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;

public interface OperationChecker {
    boolean areCardDataCorrect(Card card, RequestForMoneyTransfer requestForMoneyTransfer);

    boolean isCardValid(Card card);

    boolean areCurrenciesMatch(Card card1, Card card2);

    boolean isEnoughMoney(TransferOperation operation);

    boolean hasOperationBeenPerformed(TransferOperation operation);

    boolean isCodeValid(String code, String confirmCode);
}
