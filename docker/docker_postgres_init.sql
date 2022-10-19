CREATE USER account
    WITH PASSWORD 'account'
    CREATEDB;

CREATE DATABASE account_db
    WITH OWNER = account
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;