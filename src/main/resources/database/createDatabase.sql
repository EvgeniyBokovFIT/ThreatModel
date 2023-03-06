CREATE TABLE IF NOT EXISTS users
(
    "id"               	SERIAL 		       PRIMARY KEY,
    "login"             VARCHAR(64)        UNIQUE NOT NULL,
    "salt"          	VARCHAR(50)        NOT NULL,
    "password"          VARCHAR(100)       NOT NULL,
    "created_at"        TIMESTAMP          NOT NULL,
    "refresh_token"     TEXT
);

CREATE TABLE IF NOT EXISTS abbreviations
(
    "id"            SERIAL          PRIMARY KEY,
    "abbreviation"  VARCHAR(64)     NOT NULL,
    "decoding"      VARCHAR(512)    UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS definitions
(
    "id"          SERIAL            PRIMARY KEY,
    "definition"  VARCHAR(256)      NOT NULL,
    "meaning"     VARCHAR(512)      UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS information_types
(
    "id"          SERIAL            PRIMARY KEY,
    "type"        VARCHAR(256)      NOT NULL
);

CREATE TABLE IF NOT EXISTS operations_on_data
(
    "id"          SERIAL            PRIMARY KEY,
    "name"        VARCHAR(256)      NOT NULL
);

CREATE TABLE IF NOT EXISTS information_type_operation
(
    "information_type_id"           INTEGER     NOT NULL,
    "operation_id"                  INTEGER     NOT NULL,

    PRIMARY KEY ("information_type_id", "operation_id"),
    CONSTRAINT fk_inf_type FOREIGN KEY ("information_type_id") REFERENCES information_types (id),
    CONSTRAINT fk_operation FOREIGN KEY ("operation_id") REFERENCES operations_on_data (id)
);
