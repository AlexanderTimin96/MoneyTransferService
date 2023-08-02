package ru.netology.moneyTransferService.model.card;

import com.sun.jdi.event.StepEvent;

import java.math.BigDecimal;

public class Amount {
    private final String currency;
    private BigDecimal value;

    public Amount(BigDecimal value, String currency) {
       this.value = value;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
