package ru.netology.moneyTransferService.repository;

import ru.netology.moneyTransferService.model.operation.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.DTO.response.ResponseOperation;

import java.util.Optional;

public interface MoneyTransferRepository {
    Optional<Card> findCardsInStorage(String number);

    ResponseOperation saveOperation(TransferOperation transferOperation);

    TransferOperation findOperation(int id);
}
