create table sprint3_moto (
   id_moto    number(5)
      generated always as identity,
   id_filial  number(5) not null,
   id_usuario number(5) not null,
   ds_placa   varchar2(20) not null,
   ds_status  varchar2(20) not null,
   constraint pk_moto primary key ( id_moto ),
   constraint fk_filial foreign key ( id_filial )
      references sprint3_filial ( id_filial ),
   constraint fk_usuario foreign key ( id_usuario )
      references sprint3_usuario ( id_usuario )
);