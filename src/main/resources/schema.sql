DROP SCHEMA IF EXISTS test CASCADE;

CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS test.Company
(
    id          SERIAL PRIMARY KEY not null ,
    identifier  uuid UNIQUE not null ,
    name        VARCHAR(50) not null UNIQUE 
);

CREATE TABLE IF NOT EXISTS test.Employee
(
    id          SERIAL PRIMARY KEY not null ,
    identifier  uuid UNIQUE not null ,
    name        VARCHAR(50) not null ,
    surname     VARCHAR(50) NOT NULL ,
    city        VARCHAR(50),
    company_id  INTEGER REFERENCES test.Company(id)
);

CREATE INDEX cityIndex ON test.Employee(city);

INSERT INTO test.Employee VALUES (1,'Franek', 'Kimono', 'Wroclaw');
SELECT * FROM test.Employee;

