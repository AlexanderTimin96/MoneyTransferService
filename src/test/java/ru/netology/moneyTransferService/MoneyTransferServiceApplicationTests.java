package ru.netology.moneyTransferService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.netology.moneyTransferService.dataForTest.DataForTest;
import ru.netology.moneyTransferService.model.DTO.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class MoneyTransferServiceApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Container
    private static final GenericContainer<?> moneyTransferService = new GenericContainer<>("moneytransferservice:latest")
            .withExposedPorts(5500);

    DataForTest data = new DataForTest();

    @Test
    public void moneyTransferService_transfer_Test() {
        ResponseEntity<Response> responseFromTransfer = restTemplate.postForEntity("http://localhost:"
                        + moneyTransferService.getMappedPort(5500) + "/transfer",
                data.getCorrectRequestForMoneyTransfer(), Response.class);
        Response expected = data.getResponseTransfer();
        Assertions.assertEquals(expected, responseFromTransfer.getBody());
    }

    @Test
    public void moneyTransferService_confirmOperation_Test() {
        ResponseEntity<Response> responseFromConfirmOperation = restTemplate.postForEntity("http://localhost:"
                        + moneyTransferService.getMappedPort(5500) + "/confirmOperation",
                data.getRequestForConfirmOperation(), Response.class);
        Response expected = data.getResponseTransfer();
        Assertions.assertEquals(expected, responseFromConfirmOperation.getBody());
    }
}