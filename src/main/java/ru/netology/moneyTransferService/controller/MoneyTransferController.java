package ru.netology.moneyTransferService.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneyTransferService.model.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.response.ResponseTransfer;
import ru.netology.moneyTransferService.service.MoneyTransferService;

@RestController
@RequestMapping("/")
public class MoneyTransferController {
    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping("transfer")
    public ResponseTransfer transferMoney(@RequestBody @Validated RequestForMoneyTransfer moneyTransferOperation) {
        return moneyTransferService.transferMoney(moneyTransferOperation);
    }

    @PostMapping("confirmOperation")
    public ResponseTransfer confirmOperation(@RequestBody @Validated RequestForConfirmOperation requestForConfirmOperation) {
        return moneyTransferService.confirmOperation(requestForConfirmOperation);
    }
}
