CREATE TABLE topico
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    titulo       VARCHAR(255) NOT NULL,
    mensagem     TEXT         NOT NULL,
    data_criacao TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado       ENUM('aberto', 'fechado', 'pendente') NOT NULL DEFAULT 'aberto',
    autor        VARCHAR(100) NOT NULL,
    curso        VARCHAR(100) NOT NULL
);