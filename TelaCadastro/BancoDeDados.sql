CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_usuario VARCHAR(255),
    email VARCHAR(255),
    idade INT,
    sexo VARCHAR(10),
    genero_favorito VARCHAR(50),
    senha VARCHAR(255)
);