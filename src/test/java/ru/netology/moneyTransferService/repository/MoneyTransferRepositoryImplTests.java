package ru.netology.moneyTransferService.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.moneyTransferService.dataForTest.DataForTest;
import ru.netology.moneyTransferService.exceptions.InputDataException;
import ru.netology.moneyTransferService.model.operation.card.Card;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.DTO.response.ResponseOperation;

import java.util.Optional;

public class MoneyTransferRepositoryImplTests {
    MoneyTransferRepository moneyTransferRepository = new MoneyTransferRepositoryImpl();
    DataForTest data = new DataForTest();

    @Test
    public void findCardsInStorage_withCorrectArguments_test() {
        Optional<Card> expected = Optional.of(data.getCorrectCard1());
        Optional<Card> result = moneyTransferRepository.findCardsInStorage(data.getCorrectCard1().getNumber());
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void findCardsInStorage_withNotCorrectArguments_test() {
        Optional<Card> result = moneyTransferRepository.findCardsInStorage("12121212");
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void saveOperation_withCorrectArguments_test() {
        ResponseOperation result = moneyTransferRepository.saveOperation(data.getCorrectTransferOperation());
        ResponseOperation expected = new ResponseOperation("1");
        Assertions.assertEquals(expected, result);
    }

    @Test(expected = InputDataException.class)
    public void findOperation_withNotCorrectArguments_test() {
        moneyTransferRepository.findOperation(50);
    }

    @Test
    public void findOperation_withCorrectArguments_test() {
        TransferOperation expected = data.getCorrectTransferOperation();
        ResponseOperation resultSaveOperation = moneyTransferRepository.saveOperation(expected);
        int intResultSaveOperation = Integer.parseInt(resultSaveOperation.operationId());
        TransferOperation result = moneyTransferRepository.findOperation(intResultSaveOperation);
        Assertions.assertEquals(expected, result);
    }
}
