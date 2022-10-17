CREATE SCHEMA client_schema;

CREATE TABLE client_schema.client
(
    id BIGSERIAL NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    place_of_birth VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    passport VARCHAR(255) NOT NULL,
    CONSTRAINT pk_client_schema_client_id PRIMARY KEY (id)
);

COMMENT ON TABLE client_schema.client IS 'Used for registering clients';
COMMENT ON COLUMN client_schema.client.id IS 'Client ID';
COMMENT ON COLUMN client_schema.client.full_name IS 'Client full name';
COMMENT ON COLUMN client_schema.client.place_of_birth IS 'Client place of birth';
COMMENT ON COLUMN client_schema.client.date_of_birth IS 'Client date of birth';
COMMENT ON COLUMN client_schema.client.address IS 'Client address';
COMMENT ON COLUMN client_schema.client.passport IS 'Client passport data';
