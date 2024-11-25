alter table autor add column ativo tinyint;
update autor set ativo = 1;
alter table autor modify ativo tinyint not null;