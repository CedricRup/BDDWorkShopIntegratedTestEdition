#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE santa;
    CREATE DATABASE santa_cuke;
    GRANT ALL PRIVILEGES ON DATABASE santa TO postgres;
    GRANT ALL PRIVILEGES ON DATABASE santa_cuke TO postgres;
EOSQL
