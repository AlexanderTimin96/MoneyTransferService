FROM openjdk:17-jdk-slim

EXPOSE 5500

ADD target/MoneyTransferService-0.0.1-SNAPSHOT.jar moneytransferservice.jar

ENTRYPOINT ["java", "-jar", "moneytransferservice.jar"]