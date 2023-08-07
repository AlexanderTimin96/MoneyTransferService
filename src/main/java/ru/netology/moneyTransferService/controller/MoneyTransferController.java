package ru.netology.moneyTransferService.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneyTransferService.model.DTO.request.RequestForConfirmOperation;
import ru.netology.moneyTransferService.model.DTO.request.RequestForMoneyTransfer;
import ru.netology.moneyTransferService.model.DTO.response.Response;
import ru.netology.moneyTransferService.service.MoneyTransferService;

@RestController
@RequestMapping("/")
public class MoneyTransferController {
    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping("transfer")
    public Response transferMoney(@RequestBody @Validated RequestForMoneyTransfer moneyTransferOperation) {
        return moneyTransferService.transferMoney(moneyTransferOperation);
    }

    @PostMapping("confirmOperation")
    public Response confirmOperation(@RequestBody @Validated RequestForConfirmOperation requestForConfirmOperation) {
        return moneyTransferService.confirmOperation(requestForConfirmOperation);
    }
}
