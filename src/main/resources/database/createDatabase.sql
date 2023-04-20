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

--основные положения
CREATE TABLE IF NOT EXISTS info_general_provisions
(
    "id"                            SERIAL            PRIMARY KEY,
    "purpose"                       VARCHAR(1024)     NOT NULL, --назначение документа
    "information_owner"             VARCHAR(1024)     NOT NULL, --владелец информации
    "responsible_officials"         VARCHAR(1024)     NOT NULL, --ответственные лица
    "model_developer_organisation"  VARCHAR(1024)     NOT NULL, --организация разработавшая модель угроз
    "model_id"                      INTEGER           NOT NULL,

    CONSTRAINT fk_provision_model FOREIGN KEY ("model_id") REFERENCES threat_models (id)
);

--НПА
CREATE TABLE IF NOT EXISTS info_regulations
(
    "id"             SERIAL            PRIMARY KEY,
    "regulation"     VARCHAR(1024)     NOT NULL,
    "model_id"       INTEGER           NOT NULL,

    CONSTRAINT fk_regulation_model FOREIGN KEY ("model_id") REFERENCES threat_models (id)
);

--Перечень информации, обрабатываемой в ИС
CREATE TABLE IF NOT EXISTS info_system_information
(
    "id"                        SERIAL              PRIMARY KEY,
    "information_type"          VARCHAR(128)        NOT NULL,
    "operations"                VARCHAR(1024)       NOT NULL,
    "composition"               VARCHAR(256)        NOT NULL,--состав сведений
    "model_id"                  INTEGER             NOT NULL,

    CONSTRAINT fk_system_information_model FOREIGN KEY ("model_id") REFERENCES threat_models (id)
);

--2.	ОПИСАНИЕ СИСТЕМ И СЕТЕЙ И ИХ ХАРАКТЕРИСТИКА КАК ОБЪЕКТОВ ЗАЩИТЫ
CREATE TABLE IF NOT EXISTS info_system_description
(
    "id"                        SERIAL              PRIMARY KEY,
    "general_information"       VARCHAR(1024)       NOT NULL, --общие сведения о системе
    "system_name"               VARCHAR(128)        NOT NULL, --навзвание ИС
    "model_id"                  INTEGER             NOT NULL,

    CONSTRAINT fk_description_model FOREIGN KEY ("model_id") REFERENCES threat_models (id)
);
