# Projeto_Ciceros
 Repositório do Trabalho de Quarta

Script Banco de Dados

CREATE DATABASE db_cicreso;

USE db_cicreso;

CREATE TABLE tb_login(
	id_log_pk INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(30),
    senha VARCHAR(20),
    pergunta VARCHAR(70),
    resposta VARCHAR(40)
);

INSERT INTO tb_login(usuario, senha) VALUES ("root", "123");

INSERT INTO tb_login (usuario, senha, pergunta, resposta) VALUES ("nnnnnn","nnnnnnnn","nnnnnnn","nnnnnnnnn");

select * from tb_login;

select pergunta from tb_login where usuario ="vinicius";

CREATE TABLE tb_cliente(
	id_cli_pk INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(40),
	endereco VARCHAR(50),
    telefone VARCHAR(15)
);

CREATE TABLE tb_cardapio(
	id_car_pk INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(40),
    descricao VARCHAR(30),
	preco DOUBLE
);

CREATE TABLE tb_pedido(
	id_ped_pk INT AUTO_INCREMENT PRIMARY KEY,
	observacao VARCHAR(100),
    dia_pedido DATETIME,
    cliente INT,
    CONSTRAINT dep_fk FOREIGN KEY (cliente) REFERENCES tb_cliente(id_cli_pk)
);

CREATE TABLE tb_pagamento(
	id_pag_pk INT AUTO_INCREMENT PRIMARY KEY,
    forma_Pagamento VARCHAR(20),
    valor DOUBLE,
    dia_compra DATETIME,
    pedido INT,
    CONSTRAINT ped_fk FOREIGN KEY (pedido) REFERENCES tb_pedido(id_ped_pk)
);




teste para ver se consigo fazer pullrequest