--liquibase formatted sql

--changeset nikolajs:1

CREATE TABLE payment
(
    id VARCHAR(255) PRIMARY KEY,
    amount DECIMAL(5,2) NOT NULL,
    debtor_iban VARCHAR(255) NOT NULL,
    created_at TIMESTAMP
);