CREATE TABLE IF NOT EXISTS file(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    filename CHAR(255) NOT NULL ,
    upload_time CHAR(14) NOT NULL ,
    size UNSIGNED BIG INT NOT NULL
);