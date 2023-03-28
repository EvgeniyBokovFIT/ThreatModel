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

CREATE TABLE IF NOT EXISTS roles
(
    "id"          SERIAL            PRIMARY KEY,
    "name"        VARCHAR(256)      NOT NULL
);

CREATE TABLE IF NOT EXISTS permissions
(
    "id"          SERIAL            PRIMARY KEY,
    "name"        VARCHAR(512)      NOT NULL
);

CREATE TABLE IF NOT EXISTS role_permission
(
    "role_id"           INTEGER     NOT NULL,
    "permission_id"     INTEGER     NOT NULL,

    PRIMARY KEY ("role_id", "permission_id"),
    CONSTRAINT fk_role FOREIGN KEY ("role_id") REFERENCES roles (id),
    CONSTRAINT fk_permission FOREIGN KEY ("permission_id") REFERENCES permissions (id)
);

CREATE TABLE IF NOT EXISTS objects_of_influence
(
    "id"          SERIAL            PRIMARY KEY,
    "name"        VARCHAR(256)      NOT NULL
);

CREATE TABLE IF NOT EXISTS components_of_objects
(
    "id"          SERIAL            PRIMARY KEY,
    "object_id"   INTEGER           NOT NULL,
    "name"        VARCHAR(512)      NOT NULL,

    CONSTRAINT fk_object_component FOREIGN KEY ("object_id") REFERENCES objects_of_influence (id)
);

--Таблицы для хранения информации пользователей
CREATE TABLE IF NOT EXISTS threat_models
(
    "id"          SERIAL            PRIMARY KEY,
    "name"        VARCHAR(256)      NOT NULL,
    "user_id"     INTEGER           NOT NULL,

    CONSTRAINT fk_model_user FOREIGN KEY ("user_id") REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS info_abbreviations
(
    "id"            SERIAL            PRIMARY KEY,
    "abbreviation"  VARCHAR(256)      NOT NULL,
    "decoding"      VARCHAR(512)      UNIQUE NOT NULL,
    "model_id"      INTEGER           NOT NULL,

    CONSTRAINT fk_abbreviation_model FOREIGN KEY ("model_id") REFERENCES threat_models (id)
);

CREATE TABLE IF NOT EXISTS info_definitions
(
    "id"          SERIAL            PRIMARY KEY,
    "definition"  VARCHAR(256)      NOT NULL,
    "meaning"     VARCHAR(512)      UNIQUE NOT NULL,
    "model_id"    INTEGER           NOT NULL,

    CONSTRAINT fk_definition_model FOREIGN KEY ("model_id") REFERENCES threat_models (id)
);
