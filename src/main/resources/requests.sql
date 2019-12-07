CREATE TABLE houses
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    price       INTEGER CHECK ( price > 0 ),
    district    TEXT,
    underground TEXT
);

INSERT INTO houses
VALUES (1, 6000000, 'Кировский', 'Козья');
INSERT INTO houses
VALUES (2, 12000000, 'Вахитовский', 'Кремлевская');
INSERT INTO houses
VALUES (3, 3000000, 'Московский', 'Козья');
INSERT INTO houses
VALUES (4, 5000000, 'Новосавиновский', 'Козья');
INSERT INTO houses
VALUES (5, 2000000, 'Приволжский', 'Ометьево');