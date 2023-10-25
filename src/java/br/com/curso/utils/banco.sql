Create table pedido(
  idpedido serial primary key,
  nomepedido varchar(100) not null,
  datapedido date not null,
  valortotal numeric (15,2),
  pago varchar(1) not null,
  idcliente int not null,
  constraint fk_cliente foreign key (idcliente) references cliente(idcliente),
  idservico int not null,
  constraint fk_servico foreign key (idservico) references servico(idservico),
  idveiculo int not null,
  constraint fk_veiculo foreign key (idveiculo) references veiculo(idveiculo)
);

create table veiculo (
	idveiculo serial primary key,
	placaveiculo varchar(7) not null,
	cor varchar(40) not null,
	ano date not null,
	idtipo int not null,
  constraint fk_tipo foreign key (idtipo) references tipoveiculo(idtipo),
    idmodelo int not null,
  constraint fk_modelo foreign key (idmodelo) references modeloveiculo(idmodelo),
    idmarca int not null,
  constraint fk_marca foreign key (idmarca) references marcaveiculo(idmarca),
	imagemveiculo text
);