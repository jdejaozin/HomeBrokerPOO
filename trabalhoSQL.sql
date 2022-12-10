CREATE SCHEMA `trabalho` ;

use trabalho;

create table cliente(
	id_cliente		int not null AUTO_INCREMENT,
    login		   	varchar(100) not null,
    senha          	varchar(100) not null,
    nome           	varchar(100) not null,
	cpf       	   	varchar(11) not null,
    endereco	   	varchar(100) not null,
    telefone	   	varchar(11) not null,
    id_conta		int,
    tipo		   	varchar(5) not null,
    data_criacao   	DateTime not null,
	data_alteracao 	DateTime,
    PRIMARY KEY (id_cliente),
    FOREIGN KEY (id_conta) REFERENCES conta(id_conta)
);

insert into cliente (login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)
values ("adm1", "123", "João Paulo", "95550664003", "Rua dos Bobos, 0", "34987641029", "ADM", "80-09-01", "01-09-01");
insert into cliente (login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)
values ("adm2", "123", "João Pedro", "72510999001", "Rua dos Bobos, 10", "3488755301", "ADM", "80-09-01", "01-09-01");
insert into cliente (login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)
values ("adm3", "123", "João Armless", "77328134054", "Rua dos Bobos, 340", "34976378219", "ADM", "80-09-01", "01-09-01");

create table tickers(
	id_ticker		int not null AUTO_INCREMENT,
    nome_empresa	varchar(100) not null,
    ticker			varchar(3) not null,
    total_ativos	int not null,
    preco_inicial	double not null,
    data_criacao   	DateTime not null,
	data_alteracao 	DateTime,
    PRIMARY KEY (id_ticker)
);

insert into tickers (nome_empresa, ticker, total_ativos, preco_inicial, data_criacao, data_alteracao)
values ("João Pedro & Paulo", "JPP", 5, 50.0, "80-09-01", "01-09-01");
insert into tickers (nome_empresa, ticker, total_ativos, preco_inicial, data_criacao, data_alteracao)
values ("Magalu", "MAG", 5, 50.0, "80-09-01", "01-09-01");
insert into tickers (nome_empresa, ticker, total_ativos, preco_inicial, data_criacao, data_alteracao)
values ("Only fans", "ONF", 5, 50.0, "80-09-01", "01-09-01");

create table conta(
	id_conta		int not null AUTO_INCREMENT,
    id_cliente		int not null,
    saldo			double,
    data_criacao   	DateTime not null,
	data_alteracao 	DateTime,
    PRIMARY KEY (id_conta),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);



drop table cliente;
drop table tickers;
drop table conta;
