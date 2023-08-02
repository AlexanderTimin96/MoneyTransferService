package ru.netology.moneyTransferService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.netology.moneyTransferService.model.card.Card;

import java.text.ParseException;

@SpringBootApplication
public class MoneyTransferServiceApplication {
    public static void main(String[] args) throws ParseException {
        SpringApplication.run(MoneyTransferServiceApplication.class, args);


    }
}
