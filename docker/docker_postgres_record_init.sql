CREATE USER record
    WITH PASSWORD 'record'
    CREATEDB;

CREATE DATABASE record_db
    WITH OWNER = record
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;