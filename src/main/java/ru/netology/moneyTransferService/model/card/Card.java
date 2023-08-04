package ru.netology.moneyTransferService.model.card;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
