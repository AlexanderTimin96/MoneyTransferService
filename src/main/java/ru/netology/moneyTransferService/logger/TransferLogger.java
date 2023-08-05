package ru.netology.moneyTransferService.logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.netology.moneyTransferService.model.operation.TransferOperation;
import ru.netology.moneyTransferService.model.request.RequestForMoneyTransfer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransferLogger {
    @Value("${pathNameLogFile}")
    private String pathNameLogFile;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");

    public void createFileLog() {
        String[] path = pathNameLogFile.split("/");
        int fileLength = path[path.length - 1].length();
        String pathDir = pathNameLogFile.substring(0, pathNameLogFile.length() - fileLength);

        File dir = new File(pathDir);
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(pathNameLogFile);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logSuccessfulOperation(TransferOperation operation) {
        StringBuilder str = new StringBuilder();
        str.append("[")
                .append(dtf.format(LocalDateTime.now()))
                .append("] (ПЕРЕВОД ВЫПОЛНЕН) Номер карты отправителя: ")
                .append(operation.getCardFrom().getNumber())
                .append(", номер карты получателя: ")
                .append(operation.getCardTo().getNumber())
                .append(", сумма перевода: ")
                .append(operation.getValueForIncrease().setScale(0, RoundingMode.HALF_UP))
                .insert(str.length() - 2, ".")
                .append(" ")
                .append(operation.getCardFrom().getCurrency())
                .append(", комиссия: ")
                .append(operation.getValueForReduce().subtract(operation.getValueForIncrease()).setScale(0, RoundingMode.HALF_UP))
                .insert(str.length() - 2, ".")
                .append(" ")
                .append(operation.getCardFrom().getCurrency())
                .append("\n");

        printLog(str);
    }

    public void logInvalidRequest(RequestForMoneyTransfer requestForMoneyTransfer, String msg) {
        StringBuilder str = new StringBuilder();
        str.append("[")
                .append(dtf.format(LocalDateTime.now()))
                .append("] (НЕКОРРЕКТНЫЙ ЗАПРОС НА ПЕРЕВОД: ")
                .append(msg)
                .append(") Номер карты отправителя: ")
                .append(requestForMoneyTransfer.getCardFromNumber())
                .append(", номер карты получателя: ")
                .append(requestForMoneyTransfer.getCardToNumber())
                .append(", сумма перевода: ")
                .append(requestForMoneyTransfer.getValue().setScale(0, RoundingMode.HALF_UP))
                .insert(str.length() - 2, ".")
                .append(" ")
                .append(requestForMoneyTransfer.getCurrency())
                .append("\n");

        printLog(str);
    }

    public void logOperationCancel(TransferOperation operation, String msg) {
        StringBuilder str = new StringBuilder();
        str.append("[")
                .append(dtf.format(LocalDateTime.now()))
                .append("] (ПЕРЕВОД ОТКЛОНЕН: ")
                .append(msg)
                .append(") Номер карты отправителя: ")
                .append(operation.getCardFrom().getNumber())
                .append(", номер карты получателя: ")
                .append(operation.getCardTo().getNumber())
                .append(", сумма перевода: ")
                .append(operation.getValueForIncrease().setScale(0, RoundingMode.HALF_UP))
                .insert(str.length() - 2, ".")
                .append(" ")
                .append(operation.getCardFrom().getCurrency())
                .append(", комиссия: ")
                .append(operation.getValueForReduce().subtract(operation.getValueForIncrease()).setScale(0, RoundingMode.HALF_UP))
                .insert(str.length() - 2, ".")
                .append(" ")
                .append(operation.getCardFrom().getCurrency())
                .append("\n");
        printLog(str);
    }

    private synchronized void printLog(StringBuilder str) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(pathNameLogFile, true))) {
            bf.write(str.toString());
            bf.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
