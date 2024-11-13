-- V1__criar_tabela_veiculos.sql
CREATE TABLE veiculos (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          modelo VARCHAR(255),
                          marca VARCHAR(255),
                          ano INT,
                          tipo VARCHAR(50),
                          cor VARCHAR(50)
);
