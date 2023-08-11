# Сервис перевода денег

## Задача

Данный проект является курсовой работой. С заданием можно
ознакомиться [здесь](https://github.com/netology-code/jd-homeworks/blob/master/diploma/moneytransferservice.md)

## Описание проекта

REST-сервис предоставляет интерфейс для перевода денег с одной карты на другую
по заранее
описанной [спецификации](https://github.com/AlexanderTimin96/MoneyTransferService/blob/main/MoneyTransferServiceSpecification.yaml)

FRONT-приложение подключается к разработанному сервису и использует его функционал
для перевода денег и доступен [по адресу](https://github.com/serp-ya/card-transfer)

## Запуск приложения

Для запуска введите в терминале:

```
./mvnw clean package
docker build -t moneytransferservice .
docker-compose up -d
```

## Описание проекта

- приложение разработано с использованием Spring Boot 3.1.2
- использован сборщик пакетов MAVEN
- для запуска используется docker, docker-compose

## Описание архитектуры приложения

Архитектура приложения - многослойная (клиент-серверная), где процессы представления,
обработки и управления данными являются логически отделенными друг от друга процессами.

Модель многослойной архитектуры помогает создать гибкое и многократно используемое программное обеспечение.

* money transfer controller layer - используется как фасад для доступа к функционалу приложения
* money transfer service layer - реализует бизнес-логику работы приложения по переводу денег с карты на карту
* money transfer repository layer - хранение карт и операций по переводу денег


* web config - настройки Cross-Origin Resource Sharing
* operation checker - проводит проверки над операциями по переводу денег и сравнения введенных данных с данными карт в репозитории
* transfer logger - осуществляет логирование всех операций
* model - шаблон TDO и объектов данных, которыми управляет приложение
* exception handler advice - обработчик исключений

Настройки приложения хранятся в файле application.properties:
* server.port
* cross.origin.host.name
* commission
* confirmCode
* pathNameLogFile

Все изменения записываются в файл (указанный в application.properties) — лог переводов в формате с указанием:
* даты
* времени
* результата операции
* карты, с которой было списание
* карты зачисления
* суммы
* комиссии

## Эндпоинты

* `/transfer` - принимает объект запроса на перевод и возвращает id операции при корректном запросе

  Выполняемые проверки:
  * номер карты - обязательное, минимум 16 знаков
  * карта отправителя и получателя должны находится в репозитории
  * ММ/ГГ - обязательное, минимум 5 знаков
  * карты не должны быть просроченные. Срок действия карты отправителя должен совпадать со сроком действия карты в репозитории
  * CVV - обязательное, минимум 3 знака
  * CVV - код должен совпадать с кодом карты отправителя в репозитории
  * сумма перевода - обязательное, не может быть равное или меньше 1
  * номер карты отправителя и карты получателя не должны совпадать
  * у карт получателя и отправителя должен быть счет в валюте перевода
  * на счете отправителя достаточно средств для выполнения операции с учетом комиссии

* `/confirmOperation` - принимает объект запроса на верификацию с кодом верификации и id операции. Возвращает номер
  операции при успешном выполнении

  Выполняемые проверки:
  * код верификации должен совпадать с кодом верификации, хранящимся в репозитории (На данный момент FRONT отправляет всегда "0000")
  * запрос на данную операцию должен храниться в репозитории
  * операция с данным id не должна быть выполнена ранее

Если какая-либо из указанных проверок не соблюдается, то выбрасывается исключение и клиенту возвращается ответ с
ошибкой

При успешном завершении операции перевода отображается соответствующее модальное окно, поля формы очищаются

## Тестирование приложения

Код покрыт unit тестами с использованием mockito

Интеграционные тесты проведены с использованием testcontainers
(для запуска необходимо собрать docker - образ, после раскомментировать тест и запустить его)

Для мануального тестирования приложения в репозитории имеются карты:

Корректные карты:

` Номер карты: "1111111111111111",
Срок действия: "01/25",
CVV: "111",
Валюта: "RUR",
Баланс: 1000 руб`

`Номер карты: "2222222222222222",
Срок действия: "02/25",
CVV: "222",
Валюта: "RUR",
Баланс: 25 000 руб`

Карта с просроченным периодом действия:

`Номер карты: "3333333333333333",
Срок действия: "03/23",
CVV: "333",
Валюта: "RUR",
Баланс: 10 000 руб`

Карта с невалидным валютным счетом:

`Номер карты: "4444444444444444",
Срок действия: "04/25",
CVV: "444",
Валюта: "EUR",
Баланс: 15 000 руб`