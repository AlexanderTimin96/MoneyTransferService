package ru.netology.moneyTransferService.dataForTest;

import ru.netology.moneyTransferService.model.DTO.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.DTO.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.DTO.response.ResponseOperation;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.operation.card.Amount;
import ru.netology.moneyTransferService.model.operation.card.Card;

import java.math.BigDecimal;

public class DataForTest {
    private final Card correctCard1;
    private final Card correctCard2;
    private final Card expiredCard;
    private final Card cardWithWrongCurrency;

    private final TransferOperation correctTransferOperation;
    private final TransferOperation notCorrectTransferOperation;

    private final RequestForMoneyTransfer correctRequestForMoneyTransfer;
    private final RequestForMoneyTransfer notCorrectRequestForMoneyTransfer;
    private final ResponseOperation responseTransfer;
    private final RequestForConfirmOperation requestForConfirmOperation;

    public DataForTest() {
        correctCard1 = new Card("1111111111111111", "01/25", "111", "RUR");
        correctCard1.increaseValue(new BigDecimal(1_000_00));
        correctCard2 = new Card("2222222222222222", "02/25", "222", "RUR");
        correctCard2.increaseValue(new BigDecimal(1_000_00));
        expiredCard = new Card("3333333333333333", "03/23", "333", "RUR");
        cardWithWrongCurrency = new Card("4444444444444444", "04/25", "444", "EUR");
        responseTransfer = new ResponseOperation("1");
        requestForConfirmOperation = new RequestForConfirmOperation("1", "0000");

        correctTransferOperation = new TransferOperation(correctCard1, correctCard2,
                new BigDecimal(500_00), 0.01);
        notCorrectTransferOperation = new TransferOperation(correctCard1, correctCard2,
                new BigDecimal(5000_00), 0.01);
        notCorrectTransferOperation.setOperationSuccessful(true);

        correctRequestForMoneyTransfer = new RequestForMoneyTransfer("1111111111111111",
                "01/25", "111", "2222222222222222",
                new Amount(new BigDecimal(500_00), "RUR"));
        notCorrectRequestForMoneyTransfer = new RequestForMoneyTransfer("1111111111111111",
                "01/25", "112", "2222222222222222",
                new Amount(new BigDecimal(5000_00), "RUR"));
    }

    public ResponseOperation getResponseTransfer() {
        return responseTransfer;
    }

    public RequestForConfirmOperation getRequestForConfirmOperation() {
        return requestForConfirmOperation;
    }

    public RequestForMoneyTransfer getNotCorrectRequestForMoneyTransfer() {
        return notCorrectRequestForMoneyTransfer;
    }

    public RequestForMoneyTransfer getCorrectRequestForMoneyTransfer() {
        return correctRequestForMoneyTransfer;
    }

    public TransferOperation getCorrectTransferOperation() {
        return correctTransferOperation;
    }

    public TransferOperation getNotCorrectTransferOperation() {
        return notCorrectTransferOperation;
    }

    public Card getCorrectCard1() {
        return correctCard1;
    }

    public Card getCorrectCard2() {
        return correctCard2;
    }

    public Card getExpiredCard() {
        return expiredCard;
    }

    public Card getCardWithWrongCurrency() {
        return cardWithWrongCurrency;
    }
}
