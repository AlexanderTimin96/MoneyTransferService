package ru.netology.moneyTransferService.repository;

import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;

public interface MoneyTransferRepository {
    Card findCardsInStorage(String number);

    String saveOperation(TransferOperation transferOperation);

    TransferOperation findOperation(int id);
}
