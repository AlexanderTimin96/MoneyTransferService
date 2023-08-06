package ru.netology.moneyTransferService.checker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.moneyTransferService.dataForTest.DataForTest;

public class OperationCheckerTests {
    OperationChecker checker = new OperationCheckerImpl();
    DataForTest data = new DataForTest();

    @Test
    public void isCardValid_withCorrectCard_test() {
        boolean result = checker.isCardValid(data.getCorrectCard1());
        Assertions.assertTrue(result);
    }

    @Test
    public void isCardValid_withExpiredCard_test() {
        boolean result = checker.isCardValid(data.getExpiredCard());
        Assertions.assertFalse(result);
    }

    @Test
    public void isEnoughMoney_withCorrectAmount_test() {
        boolean result = checker.isEnoughMoney(data.getCorrectTransferOperation());
        Assertions.assertTrue(result);
    }

    @Test
    public void isEnoughMoney_withNotCorrectAmount_test() {
        boolean result = checker.isEnoughMoney(data.getNotCorrectTransferOperation());
        Assertions.assertFalse(result);
    }

    @Test
    public void isCodeValid_withCorrectCode_test() {
        boolean result = checker.isCodeValid("0000", "0000");
        Assertions.assertTrue(result);
    }

    @Test
    public void isCodeValid_withNotCorrectCode_test() {
        boolean result = checker.isCodeValid("0000", "0001");
        Assertions.assertFalse(result);
    }

    @Test
    public void hasOperationBeenPerformed_withNotPerformed_test() {
        boolean result = checker.hasOperationBeenPerformed(data.getCorrectTransferOperation());
        Assertions.assertFalse(result);
    }

    @Test
    public void hasOperationBeenPerformed_withPerformed_test() {
        boolean result = checker.hasOperationBeenPerformed(data.getNotCorrectTransferOperation());
        Assertions.assertTrue(result);
    }

    @Test
    public void areCurrenciesMatch_withMatchingCurrencies_test() {
        boolean result = checker.areCurrenciesMatch(data.getCorrectCard1(), data.getCorrectCard2());
        Assertions.assertTrue(result);
    }

    @Test
    public void areCurrenciesMatch_withMismatchedCurrencies_test() {
        boolean result = checker.areCurrenciesMatch(data.getCorrectCard1(), data.getCardWithWrongCurrency());
        Assertions.assertFalse(result);
    }

    @Test
    public void areCardDataCorrect_withCorrectRequest_test() {
        boolean result = checker.areCardDataCorrect(data.getCorrectCard1(), data.getCorrectRequestForMoneyTransfer());
        Assertions.assertTrue(result);
    }

    @Test
    public void areCardDataCorrect_withNotCorrectRequest_test1() {
        boolean result = checker.areCardDataCorrect(data.getCorrectCard2(), data.getCorrectRequestForMoneyTransfer());
        Assertions.assertFalse(result);
    }

    @Test
    public void areCardDataCorrect_withNotCorrectRequest_test2() {
        boolean result = checker.areCardDataCorrect(data.getCorrectCard1(), data.getNotCorrectRequestForMoneyTransfer());
        Assertions.assertFalse(result);
    }
}
