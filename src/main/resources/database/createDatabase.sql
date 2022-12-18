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