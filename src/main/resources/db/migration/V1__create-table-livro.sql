CREATE TABLE Autor (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(255) NOT NULL,
                       nacionalidade VARCHAR(100) NOT NULL
);

CREATE TABLE Livro (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       titulo VARCHAR(255) NOT NULL,
                       categoria VARCHAR(50) NOT NULL,
                       id_autor BIGINT NOT NULL,
                       ano_publicacao INT NOT NULL,
                       preco DOUBLE NOT NULL,
                       quantidade_estoque INT NOT NULL,
                       disponivel BOOLEAN NOT NULL DEFAULT TRUE,
                       CONSTRAINT fk_autor FOREIGN KEY (id_autor) REFERENCES Autor(id)
);
