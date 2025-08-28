CREATE DATABASE crediya_loan_application;

CREATE SCHEMA IF NOT EXISTS loan_application;

CREATE TABLE loan_application.states
(
    state_id    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE loan_application.loan_types
(
    loan_type_id    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name            VARCHAR(100)   NOT NULL,
    min_amount      NUMERIC(15, 2) NOT NULL,
    max_amount      NUMERIC(15, 2) NOT NULL,
    interest_rate   NUMERIC(5, 2)  NOT NULL,
    auto_validation BOOLEAN DEFAULT FALSE
);

CREATE TABLE loan_application.loan_applications
(
    application_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount         NUMERIC(15, 2) NOT NULL,
    term           INTEGER        NOT NULL,
    email          VARCHAR(255)   NOT NULL,
    state_id       UUID           NOT NULL,
    loan_type_id   UUID           NOT NULL,
    CONSTRAINT fk_state FOREIGN KEY (state_id) REFERENCES loan_application.states (state_id),
    CONSTRAINT fk_loan_type FOREIGN KEY (loan_type_id) REFERENCES loan_application.loan_types (loan_type_id)
);

