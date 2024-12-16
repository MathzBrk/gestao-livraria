-- Excluir a tabela 'compra' se ela já existir
DROP TABLE IF EXISTS compra;

-- Criar a tabela 'compra' com os atributos corretos
CREATE TABLE compra (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        cliente_id BIGINT NOT NULL,
                        livro_id BIGINT NOT NULL,
                        data_compra DATE,
                        quantidade INT,
                        valor_total DOUBLE,
                        CONSTRAINT fk_cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente(id),
                        CONSTRAINT fk_livro_id FOREIGN KEY (livro_id) REFERENCES livro(id)
);
