FROM openjdk:17

EXPOSE 5500

ADD target/MoneyTransferService-0.0.1-SNAPSHOT.jar moneytransferservice.jar

ENTRYPOINT ["java", "-jar", "moneytransferservice.jar"]