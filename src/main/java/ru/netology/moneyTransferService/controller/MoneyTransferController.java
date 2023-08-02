package ru.netology.moneyTransferService.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneyTransferService.model.requestObject.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.requestObject.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.service.MoneyTransferService;

@RestController
@RequestMapping("/")
public class MoneyTransferController {
    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping("transfer")
    public String transferMoney(@RequestBody @Validated RequestForMoneyTransfer moneyTransferOperation) {
        return moneyTransferService.transferMoney(moneyTransferOperation);
    }

    @PostMapping("confirmOperation")
    public String confirmOperation(@RequestBody RequestForConfirmOperation requestForConfirmOperation) {
        return moneyTransferService.confirmOperation(requestForConfirmOperation);
    }
}
