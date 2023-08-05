package ru.netology.moneyTransferService.repository;

import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.response.ResponseTransfer;

import java.util.Optional;

public interface MoneyTransferRepository {
    Optional<Card> findCardsInStorage(String number);

    ResponseTransfer saveOperation(TransferOperation transferOperation);

    TransferOperation findOperation(int id);
}
