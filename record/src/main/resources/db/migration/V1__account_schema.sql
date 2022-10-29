CREATE SCHEMA record_schema;

CREATE TABLE record_schema.record
(
    id BIGSERIAL NOT NULL,
    acc_id BIGINT NOT NULL,
    oper_name VARCHAR(255) NOT NULL,
    oper_date DATE NOT NULL,
    CONSTRAINT pk_record_schema_record_id PRIMARY KEY (id)
);

COMMENT ON TABLE record_schema.record IS 'Used for storing accounts operations records';
COMMENT ON COLUMN record_schema.record.id IS 'Record ID';
COMMENT ON COLUMN record_schema.record.acc_id IS 'Account ID';
COMMENT ON COLUMN record_schema.record.oper_name IS 'Operation name';
COMMENT ON COLUMN record_schema.record.oper_date IS 'Operation date';
