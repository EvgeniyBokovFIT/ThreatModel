CREATE TABLE IF NOT EXISTS users
(
    "id"               	INTEGER 		   PRIMARY KEY NOT NULL,
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
    "definition"  VARCHAR(256)       NOT NULL,
    "meaning"     VARCHAR(512)      UNIQUE NOT NULL
);