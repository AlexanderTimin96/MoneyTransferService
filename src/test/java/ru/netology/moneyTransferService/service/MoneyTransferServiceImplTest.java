package ru.netology.moneyTransferService.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.netology.moneyTransferService.checker.OperationChecker;
import ru.netology.moneyTransferService.dataForTest.DataForTest;
import ru.netology.moneyTransferService.exceptions.InputDataException;
import ru.netology.moneyTransferService.exceptions.InvalidTransactionExceptions;
import ru.netology.moneyTransferService.logger.TransferLogger;
import ru.netology.moneyTransferService.repository.MoneyTransferRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoneyTransferServiceImplTest {
    DataForTest data = new DataForTest();
    @Mock
    private MoneyTransferRepository moneyTransferRepository;
    @Mock
    private TransferLogger logger;
    @Mock
    private OperationChecker checker;
    @InjectMocks
    private MoneyTransferServiceImpl moneyTransferService;

    @Test(expected = InputDataException.class)
    public void transferMoney_withBadRequest_test1() {
        when(moneyTransferRepository.findCardsInStorage(data.getNotCorrectRequestForMoneyTransfer().getCardFromNumber()))
                .thenReturn(Optional.empty());
        when(moneyTransferRepository.findCardsInStorage(data.getNotCorrectRequestForMoneyTransfer().getCardToNumber()))
                .thenReturn(Optional.of(data.getCorrectCard2()));

        moneyTransferService.transferMoney(data.getNotCorrectRequestForMoneyTransfer());
    }

    @Test(expected = InputDataException.class)
    public void transferMoney_withBadRequest_test2() {
        when(moneyTransferRepository.findCardsInStorage(data.getNotCorrectRequestForMoneyTransfer().getCardFromNumber()))
                .thenReturn(Optional.of(data.getCorrectCard1()));
        when(moneyTransferRepository.findCardsInStorage(data.getNotCorrectRequestForMoneyTransfer().getCardToNumber()))
                .thenReturn(Optional.of(data.getCorrectCard2()));

        when(checker.areCardDataCorrect(data.getCorrectCard1(), data.getNotCorrectRequestForMoneyTransfer()))
                .thenReturn(false);
        moneyTransferService.transferMoney(data.getNotCorrectRequestForMoneyTransfer());
    }

    @Test(expected = InvalidTransactionExceptions.class)
    public void transferMoney_withBadRequest_test3() {
        when(moneyTransferRepository.findCardsInStorage(data.getNotCorrectRequestForMoneyTransfer().getCardFromNumber()))
                .thenReturn(Optional.of(data.getCorrectCard1()));
        when(moneyTransferRepository.findCardsInStorage(data.getNotCorrectRequestForMoneyTransfer().getCardToNumber()))
                .thenReturn(Optional.of(data.getCorrectCard2()));

        when(checker.areCardDataCorrect(data.getCorrectCard1(), data.getNotCorrectRequestForMoneyTransfer()))
                .thenReturn(true);
        when(checker.isCardValid(data.getCorrectCard1()))
                .thenReturn(false);

        moneyTransferService.transferMoney(data.getNotCorrectRequestForMoneyTransfer());
    }
}
