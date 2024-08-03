-- Cria o schema FOTOEXPRESS
 CREATE SCHEMA fotoexpresss;

-- Cria a tabela Pacote
CREATE TABLE fotoexpress.pacote (
    id int PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    acompanhamento INT NOT NULL,
    descricao TEXT,
    valor DECIMAL(10, 2) NOT NULL
);

-- Insere dados de exemplo na tabela Pacote
INSERT INTO fotoexpress.pacote (id, nome, acompanhamento, descricao, valor) VALUES
('Pacote Básico', 10, 'Pacote básico com fotos digitais.', 100.00);
--                                                                        ,
-- ('2', 'Pacote Premium', 20, 'Pacote completo com fotos digitais, álbum e impressão.', 300.00),
-- ('3', 'Pacote Premium', 20, 'Pacote completo com fotos digitais, álbum e impressão.', 300.00);