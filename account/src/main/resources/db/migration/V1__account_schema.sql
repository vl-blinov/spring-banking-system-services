CREATE SCHEMA account_schema;

CREATE TABLE account_schema.account
(
    id BIGSERIAL NOT NULL,
    client_id BIGINT NOT NULL,
    acc_num VARCHAR(255) NOT NULL,
    saldo NUMERIC(10, 2) NOT NULL,
    open_date DATE NOT NULL,
    close_date DATE,
    CONSTRAINT pk_account_schema_account_id PRIMARY KEY (id)
);

COMMENT ON TABLE account_schema.account IS 'Used for storing clients accounts';
COMMENT ON COLUMN account_schema.account.id IS 'Account ID';
COMMENT ON COLUMN account_schema.account.client_id IS 'Owner (client) ID of the current account';
COMMENT ON COLUMN account_schema.account.acc_num IS 'Account number';
COMMENT ON COLUMN account_schema.account.saldo IS 'Account balance';
COMMENT ON COLUMN account_schema.account.open_date IS 'Account open date';
COMMENT ON COLUMN account_schema.account.close_date IS 'Account close date';
