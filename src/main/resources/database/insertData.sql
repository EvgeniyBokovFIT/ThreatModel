INSERT INTO abbreviations("abbreviation", "decoding")
VALUES ('АРМ', 'автоматизированное рабочее место');

INSERT INTO definitions("definition", "meaning")
VALUES ('Конфиденциальность информации', 'свойство информации быть недоступной для неавторизованных субъектов (лиц, сущностей или процессов)');

INSERT INTO information_types("id", "type")
VALUES (1, 'Персональные данные');

INSERT INTO operations_on_data("id", "name")
VALUES (1, 'Сбор'), (2, 'Обработка');

INSERT INTO information_type_operation("information_type_id", "operation_id")
VALUES (1, 1), (1, 2);

INSERT INTO roles("id", "name")
VALUES (1, 'Администратор ИС'), (2, 'Администратор ИБ');

INSERT INTO permissions("id", "name")
VALUES (1, 'модернизация, настройка и мониторинг работоспособности ТС АРМ'),
       (2, 'установка, модернизация, настройка и мониторинг работоспособности системного ПО');

INSERT INTO role_permission("role_id", "permission_id")
VALUES (1, 1), (1, 2);