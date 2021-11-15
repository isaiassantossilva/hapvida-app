#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER hapvida;
    CREATE DATABASE hapvida;
    GRANT ALL PRIVILEGES ON DATABASE hapvida TO hapvida;
EOSQL