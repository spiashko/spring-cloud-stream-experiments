#!/bin/bash

# creates new db and new user who is owner of this db
# arg1 db name
# arg2 user name
# arg3 user pass
create_db_user_pass() {
    psql --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -c "CREATE USER $2 WITH PASSWORD '$3'" && \
    psql --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -c "CREATE DATABASE $1 OWNER=$2"
    psql --username "$POSTGRES_USER" --dbname "$1" -c "CREATE EXTENSION IF NOT EXISTS pgcrypto"
}

create_db_user_pass demo_db demo_user demo_password


