create table sprint3_usuario (
   id_usuario number(5)
      generated always as identity,
   ds_email   varchar2(255) not null,
   ds_senha   varchar2(255) not null,
   ds_role    varchar2(20) not null,
   constraint pk_usuario primary key ( id_usuario ),
   constraint uk_email unique ( ds_email )
);