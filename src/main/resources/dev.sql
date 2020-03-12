drop table IF EXISTS payment;

create TABLE payment (
   id uuid,
   amount numeric(19,2),
   sender_id INT ,
   recipient_id INT
)PARTITION BY HASH(id);

create EXTENSION IF NOT EXISTS postgres_fdw;

-- run on server_1

--create database pay_shard;
--\c pay_shard;

--create TABLE payment_1 (
--   id uuid NOT NULL PRIMARY KEY,
--   amount numeric(19,2),
--   sender_id INT NOT NULL,
--   recipient_id INT NOT NULL
--);

--locally
DROP SERVER IF EXISTS server_1 CASCADE;
create SERVER server_1
    FOREIGN DATA WRAPPER postgres_fdw
        OPTIONS (host '127.0.0.1', port '5433', dbname 'pay_shard');

DROP USER MAPPING IF EXISTS FOR postgres SERVER server_1;
create USER MAPPING
    FOR postgres SERVER server_1
        OPTIONS (user 'postgres', password 'root');

create FOREIGN TABLE payment_1
    PARTITION OF payment
    FOR VALUES with (MODULUS 3, REMAINDER 0)
    SERVER server_1;

-- END SERVER 1



-- BEGIN SERVER 2
-- run on server_2

--create database pay_shard;
--\c pay_shard;

--create TABLE payment_2 (
--   id uuid NOT NULL PRIMARY KEY,
--   amount numeric(19,2),
--   sender_id INT NOT NULL,
--   recipient_id INT NOT NULL
--);


--on local
DROP SERVER IF EXISTS server_2 CASCADE;
create SERVER server_2
    FOREIGN DATA WRAPPER postgres_fdw
        OPTIONS (host '127.0.0.1', port '5434', dbname 'pay_shard');

DROP USER MAPPING IF EXISTS FOR postgres SERVER server_2;
create USER MAPPING
    FOR postgres SERVER server_2
        OPTIONS (user 'postgres', password 'root');

create FOREIGN TABLE payment_2
    PARTITION OF payment
    FOR VALUES with (MODULUS 3, REMAINDER 1)
    SERVER server_2;

-- END SERVER 2



-- BEGIN SERVER 3
-- run on server_3

--create database pay_shard;
--\c pay_shard;

--create TABLE payment_3 (
--   id uuid NOT NULL PRIMARY KEY,
--   amount numeric(19,2),
--   sender_id INT NOT NULL,
--   recipient_id INT NOT NULL
--);


--on local
DROP SERVER IF EXISTS server_3 CASCADE;
create SERVER server_3
    FOREIGN DATA WRAPPER postgres_fdw
        OPTIONS (host '127.0.0.1', port '5435', dbname 'pay_shard');

DROP USER MAPPING IF EXISTS FOR postgres SERVER server_3;
create USER MAPPING
    FOR postgres SERVER server_3
        OPTIONS (user 'postgres', password 'root');

create FOREIGN TABLE payment_3
    PARTITION OF payment
    FOR VALUES with (MODULUS 3, REMAINDER 2)
    SERVER server_3;

-- END SERVER 3



-- AS EXAMPLE
-- FOR TABLE IN LOCAL SERVER

--create TABLE payment_3 PARTITION OF payment(
--       id NOT NULL PRIMARY KEY,
--       amount NOT NULL,
--       sender_id NOT NULL,
--       recipient_id NOT NULL
--    )
--    FOR VALUES with (MODULUS 3, REMAINDER 2);

-- END TABLE IN LOCAL SERVER


--тест на payment
--Big decimal сравнение
--еще какие-то тесты

--описание задачи
-- убрать ru.yandex
--выложить на гит
-- скинуть ссылку отложенно
