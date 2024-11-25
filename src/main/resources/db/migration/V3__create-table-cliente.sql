
CREATE TABLE Cliente (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         telefone VARCHAR(20),
                         cpf VARCHAR(14) NOT NULL UNIQUE,
                         ativo BOOLEAN DEFAULT TRUE NOT NULL,
                         criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE compra (
                        cliente_id BIGINT NOT NULL,
                        livro_id BIGINT NOT NULL,
                        PRIMARY KEY (cliente_id, livro_id),
                        FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE,
                        FOREIGN KEY (livro_id) REFERENCES Livro(id) ON DELETE CASCADE
);
