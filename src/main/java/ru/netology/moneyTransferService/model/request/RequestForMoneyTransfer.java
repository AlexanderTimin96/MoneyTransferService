package ru.netology.moneyTransferService.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ru.netology.moneyTransferService.model.card.Amount;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RequestForMoneyTransfer {
    @NotBlank
    @Size(max = 16, min = 16)
    private final String cardFromNumber;
    private final Date cardFromValidTill;
    @NotBlank
    @Size(max = 3, min = 3)
    private final String cardFromCVV;
    @NotBlank
    @Size(max = 16, min = 16)
    private final String cardToNumber;
    private final Amount amount;

    public RequestForMoneyTransfer(String cardFromNumber, String cardFromValidTill, String cardFromCVV,
                                   String cardToNumber, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
            this.cardFromValidTill = formatter.parse(cardFromValidTill);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public Date getCardFromValidTill() {
        return cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public BigDecimal getValue() {
        return amount.getValue();
    }

    public String getCurrency() {
        return amount.getCurrency();
    }
}
