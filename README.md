# Тестовое задание "Шардирование"

## Коротко о решении:

### Первая часть 
Изначально было сделано базовое решение в виде контроллера для обработки запросов вида:

**Вставка элементов**
- POST-запрос по адресу localhost:8080/payments/save
- Body: 
```json
[
            { "sender_id": "1", "recipient_id": "234", "amount": "1" },
            { "sender_id": "2", "recipient_id": "234", "amount": "500.2" },
            { "sender_id": "3", "recipient_id": "234", "amount": "500.2" },
            { "sender_id": "4", "recipient_id": "234", "amount": "500.2" },
            { "sender_id": "5", "recipient_id": "234", "amount": "500.2" },
            { "sender_id": "6", "recipient_id": "234", "amount": "500.2" },
            { "sender_id": "7", "recipient_id": "234", "amount": "100" }
        ]
```
**Получение суммы**
- POST-запрос по адресу localhost:8080/payments/sum
- пустое тело
- параметр sender_id - индекс отправителя

Запись в базу postgresql.

### Вторая часть 
- База была модифицирована таким образом, чтобы производилось секционирование таблицы на три базы;
- Секционирование по остатку от деления на _3_ от hash значения id;
- Затем базы были вынесены на отдельные postgres серверы с использованием:
    - FOREIGN DATA WRAPPER postgres_fdw
    - FOREIGN TABLE
    
### Добавлены некторые базовые тесты
   - Запускаются для базы h2;