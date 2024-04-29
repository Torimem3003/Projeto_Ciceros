# Projeto_Ciceros
 Repositório do Trabalho de Quarta

Script Banco de Dados

CREATE DATABASE db_cicreso;

USE db_cicreso;

CREATE TABLE tb_login(
	id_log_pk INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(30) UNIQUE,
    senha VARCHAR(20),
    pergunta VARCHAR(70),
    resposta VARCHAR(40)
);

INSERT INTO tb_login(usuario, senha, pergunta, resposta) VALUES ("root", "123","Qual o nome do seu carro favorito ?", "Opara SS");

CREATE TABLE tb_cliente(
	id_cli_pk INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
	endereco VARCHAR(90),
    telefone VARCHAR(25)
);


CREATE TABLE tb_cardapio(
	id_car_pk INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45),
    descricao VARCHAR(120),
	preco DOUBLE
);

CREATE TABLE tb_pedido(
	id_ped_pk INT AUTO_INCREMENT PRIMARY KEY,
	observacao VARCHAR(200),
    dia_pedido DATE,
    cliente INT,
    CONSTRAINT dep_fk FOREIGN KEY (cliente) REFERENCES tb_cliente(id_cli_pk)
);

CREATE TABLE tb_pagamento(
	id_pag_pk INT AUTO_INCREMENT PRIMARY KEY,
    forma_Pagamento VARCHAR(40),
    valor DOUBLE,
    dia_compra DATE,
    pedido INT,
    CONSTRAINT ped_fk FOREIGN KEY (pedido) REFERENCES tb_pedido(id_ped_pk)
);






