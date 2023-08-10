package ru.netology.moneyTransferService.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneyTransferService.model.DTO.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.DTO.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.DTO.response.ResponseOperation;
import ru.netology.moneyTransferService.service.MoneyTransferService;

@RestController
@RequestMapping("/")
public class MoneyTransferController {
    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping("transfer")
    public ResponseOperation transferMoney(@RequestBody @Validated RequestForMoneyTransfer moneyTransferOperation) {
        return moneyTransferService.transferMoney(moneyTransferOperation);
    }

    @PostMapping("confirmOperation")
    public ResponseOperation confirmOperation(@RequestBody @Validated RequestForConfirmOperation requestForConfirmOperation) {
        return moneyTransferService.confirmOperation(requestForConfirmOperation);
    }
}
