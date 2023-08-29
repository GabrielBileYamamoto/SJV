create table pokemon (
    idpokemon serial primary key,
    nomepokemon varchar(100)
);

insert into pokemon (nomepokemon)
values ('Pikachu');

/*create table veiculo (
	idveiculo serial primary key,
	marcaveiculo varchar(100) not null,
	modeloveiculo varchar(100) not null
);

insert into veiculo (marcaveiculo,modeloveiculo)
values ('TOYOTA', 'COROLLA');

create table imovel (
	idimovel serial primary key,
	bairroimovel varchar(100) not null,
	cidadeimovel varchar(100) not null
);

insert into imovel (bairroimovel, cidadeimovel)
values ('CENTRO', 'JALES');
*/

