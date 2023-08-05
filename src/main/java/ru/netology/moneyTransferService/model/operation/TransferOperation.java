package ru.netology.moneyTransferService.model.operation;

import ru.netology.moneyTransferService.model.card.Card;

import java.math.BigDecimal;

public class TransferOperation {
    private final Card cardFrom;
    private final Card cardTo;
    private final BigDecimal valueForIncrease;
    private final BigDecimal valueForReduce;
    private boolean isOperationSuccessful;

    public TransferOperation(Card cardFrom, Card cardTo, BigDecimal value, double commission) {
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.valueForIncrease = value;
        valueForReduce = new BigDecimal(commission + 1).multiply(value);
    }

    public Card getCardFrom() {
        return cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public BigDecimal getValueForIncrease() {
        return valueForIncrease;
    }

    public BigDecimal getValueForReduce() {
        return valueForReduce;
    }

    public boolean isOperationSuccessful() {
        return isOperationSuccessful;
    }

    public void setOperationSuccessful(boolean flag) {
        isOperationSuccessful = flag;
    }
}
