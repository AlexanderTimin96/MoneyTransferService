package ru.netology.moneyTransferService.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneyTransferService.exceptions.InputDataException;
import ru.netology.moneyTransferService.model.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MoneyTransferRepositoryImpl implements MoneyTransferRepository {
    private final Map<String, Card> cardStorage = new HashMap<>();

    private final Map<Integer, TransferOperation> moneyTransferOperations = new ConcurrentHashMap<>();
    private final AtomicInteger operationID = new AtomicInteger(1);

    {
        Card card1 = new Card("1111111111111111", "01/25", "111", "RUR", "0000");
        Card card2 = new Card("2222222222222222", "02/25", "222", "RUR", "0000");
        Card card3 = new Card("3333333333333333", "03/23", "333", "RUR", "0000");
        Card card4 = new Card("4444444444444444", "04/25", "444", "EUR", "0000");

        card1.increaseValue(new BigDecimal(1_000_00));
        card2.increaseValue(new BigDecimal(25_000_00));
        card3.increaseValue(new BigDecimal(10_000_00));
        card4.increaseValue(new BigDecimal(15_000_00));

        cardStorage.put(card1.getNumber(), card1);
        cardStorage.put(card2.getNumber(), card2);
        cardStorage.put(card3.getNumber(), card3);
        cardStorage.put(card4.getNumber(), card3);
    }

    public Card findCardsInStorage(String number) {
        if (!cardStorage.containsKey(number)) {
            throw new InputDataException("Incorrect card " + number + " number");
        }
        return cardStorage.get(number);
    }

    public String saveOperation(TransferOperation transferOperation) {
        moneyTransferOperations.put(operationID.get(), transferOperation);
        return  Integer.toString(operationID.getAndIncrement());
    }

    @Override
    public TransferOperation findOperation(int id) {
        if (!moneyTransferOperations.containsKey(id)) {
            throw new InputDataException("This transfer request is missing");
        }
        return moneyTransferOperations.get(id);
    }
}
