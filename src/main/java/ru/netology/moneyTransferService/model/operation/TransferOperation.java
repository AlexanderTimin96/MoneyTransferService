package ru.netology.moneyTransferService.model.operation;

import ru.netology.moneyTransferService.model.card.Card;

import java.math.BigDecimal;

public class TransferOperation {
    private final Card cardFrom;
    private final Card cardTo;
    private final BigDecimal valueForIncrease;
    private final BigDecimal valueForReduce;
    private boolean isOperationSuccessful;

    public TransferOperation(Card cardFrom, Card cardTo, BigDecimal value, BigDecimal commission) {
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.valueForIncrease = value;
        valueForReduce = commission.multiply(value);
    }

    public BigDecimal getValueForReduce() {
        return valueForReduce;
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

    public boolean isOperationSuccessful() {
        return isOperationSuccessful;
    }

    public void setOperationSuccessful(boolean flag) {
        isOperationSuccessful = flag;
    }
}
