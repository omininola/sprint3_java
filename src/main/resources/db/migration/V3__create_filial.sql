create table sprint3_filial (
   id_filial   number(5)
      generated always as identity,
   nm_filial   varchar2(255) not null,
   ds_endereco varchar2(255) not null,
   constraint pk_filial primary key ( id_filial )
);