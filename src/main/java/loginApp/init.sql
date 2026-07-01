CREATE DATABASE IF NOT EXISTS loginapp_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE loginapp_db;

CREATE TABLE IF NOT EXISTS users (
    id       INT PRIMARY KEY AUTO_INCREMENT,
    yuuzaName VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

INSERT INTO users (yuuzaName, password) VALUES
    ('admin', 'pass123'),
    ('test',  'test123');
