-- Create Database
CREATE DATABASE IF NOT EXISTS my_library_database;
USE my_library_database;

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    sex VARCHAR(10),
    admin BOOLEAN NOT NULL,
    login VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

-- Create Books Table
CREATE TABLE IF NOT EXISTS books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    qtd_reviews INT,
    review_score DECIMAL(3,2),
    public BOOLEAN NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

-- Create Genres Table
CREATE TABLE IF NOT EXISTS genres (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

-- Create N to N Relationship Tables (users_genres and books_genres)
CREATE TABLE IF NOT EXISTS users_genres (
    user_id INT,
    genre_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id),
    PRIMARY KEY (user_id, genre_id)
);

CREATE TABLE IF NOT EXISTS books_genres (
    book_id INT,
    genre_id INT,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id),
    PRIMARY KEY (book_id, genre_id)
);