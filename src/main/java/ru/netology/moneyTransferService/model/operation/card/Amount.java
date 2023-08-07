package ru.netology.moneyTransferService.model.operation.card;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(currency, amount.currency) && Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value);
    }
}
