CREATE DATABASE IF NOT EXISTS booksync;
USE booksync;

-- Create Genres Table
CREATE TABLE IF NOT EXISTS genres (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    sex VARCHAR(10),
    admin BOOLEAN NOT NULL,
    login VARCHAR(20) NOT NULL,
    password VARCHAR(64) NOT NULL,
    genre_id INT,
    CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) REFERENCES genres(id)
);

-- Create Books Table
CREATE TABLE IF NOT EXISTS books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    qtd_reviews INT,
    review_score DECIMAL(3,2),
    public BOOLEAN NOT NULL,
    genre_id INT,
    CONSTRAINT fk_genre_id_books FOREIGN KEY (genre_id) REFERENCES genres(id)
);

-- Create N to N Relationship Tables (users_genres and books_genres)
CREATE TABLE IF NOT EXISTS users_genres (
    user_id INT,
    genre_id INT,
    PRIMARY KEY (user_id, genre_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_genre_id_ug FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE IF NOT EXISTS books_genres (
    book_id INT,
    genre_id INT,
    PRIMARY KEY (book_id, genre_id),
    CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES books(id),
    CONSTRAINT fk_genre_id_bg FOREIGN KEY (genre_id) REFERENCES genres(id)
);

-- Genres Table Inserts
INSERT INTO genres (name) VALUES ('Fiction');
INSERT INTO genres (name) VALUES ('Mystery');
INSERT INTO genres (name) VALUES ('Science Fiction');
INSERT INTO genres (name) VALUES ('Romance');
INSERT INTO genres (name) VALUES ('Thriller');

-- Users Table Inserts
INSERT INTO users (name, sex, admin, login, password, genre_id) VALUES ('John Doe', 'Male', true, 'john_doe', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 1);-- password123
INSERT INTO users (name, sex, admin, login, password, genre_id) VALUES ('Jane Smith', 'Female', false, 'jane_smith', '1d4598d1949b47f7f211134b639ec32238ce73086a83c2f745713b3f12f817e5', 2);-- pass456
INSERT INTO users (name, sex, admin, login, password, genre_id) VALUES ('Bob Johnson', 'Male', false, 'bob_johnson', '68256910bc07ec7457bcb7f4374e43ab5c98eda8d8eb8e1e53d6e33a47e8afe3', 3);-- securepwd

-- Books Table Inserts
INSERT INTO books (title, qtd_reviews, review_score, public, genre_id) VALUES ('The Catcher in the Rye', 500, 4.2, true, 1);
INSERT INTO books (title, qtd_reviews, review_score, public, genre_id) VALUES ('The Da Vinci Code', 800, 4.5, true, 2);
INSERT INTO books (title, qtd_reviews, review_score, public, genre_id) VALUES ('Dune', 600, 4.7, true, 3);

-- Users_Genres Table Inserts
INSERT INTO users_genres (user_id, genre_id) VALUES (1, 1);
INSERT INTO users_genres (user_id, genre_id) VALUES (2, 2);
INSERT INTO users_genres (user_id, genre_id) VALUES (3, 3);

-- Books_Genres Table Inserts
INSERT INTO books_genres (book_id, genre_id) VALUES (1, 1);
INSERT INTO books_genres (book_id, genre_id) VALUES (2, 2);
INSERT INTO books_genres (book_id, genre_id) VALUES (3, 3);

-- Show contents of the 'genres' table
SELECT * FROM genres;

-- Show contents of the 'users' table
SELECT * FROM users;

-- Show contents of the 'books' table
SELECT * FROM books;

-- Show contents of the 'users_genres' table
SELECT * FROM users_genres;

-- Show contents of the 'books_genres' table
SELECT * FROM books_genres;

-- List users by their favorite genre
SELECT u.name AS user_name, g.name AS favorite_genre
FROM users u
JOIN users_genres ug ON u.id = ug.user_id
JOIN genres g ON ug.genre_id = g.id;

-- List books by genre
SELECT b.title AS book_title, g.name AS genre
FROM books b
JOIN books_genres bg ON b.id = bg.book_id
JOIN genres g ON bg.genre_id = g.id;

DROP TABLE IF EXISTS users_genres;
DROP TABLE IF EXISTS books_genres;

-- Drop the Users Table
DROP TABLE IF EXISTS users;

-- Drop the Books Table
DROP TABLE IF EXISTS books;

-- Drop the Genres Table
DROP TABLE IF EXISTS genres;