package ru.netology.moneyTransferService.model.operation.card;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Card {
    private final String number;
    private final String CVC;
    private final Date validTill;
    private final Amount amount;

    public Card(String number, String validTill, String CW, String currency) {
        this.number = number;
        this.CVC = CW;
        this.amount = new Amount(new BigDecimal(0), currency);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
            this.validTill = formatter.parse(validTill);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    public String getNumber() {
        return number;
    }

    public String getCVC() {
        return CVC;
    }

    public Date getValidTill() {
        return validTill;
    }

    public BigDecimal getValue() {
        return amount.getValue();
    }

    public String getCurrency() {
        return amount.getCurrency();
    }

    public void increaseValue(BigDecimal value) {
        amount.setValue(amount.getValue().add(value));
    }

    public void reduceValue(BigDecimal value) {
        amount.setValue(amount.getValue().subtract(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number) && Objects.equals(CVC, card.CVC)
                && Objects.equals(validTill, card.validTill) && Objects.equals(amount, card.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, CVC, validTill, amount);
    }
}
