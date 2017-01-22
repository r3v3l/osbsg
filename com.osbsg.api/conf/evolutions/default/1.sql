# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table core_role_model (
  id                            bigint not null,
  name                          varchar(255) not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_core_role_model_name unique (name),
  constraint pk_core_role_model primary key (id)
);
create sequence core_role_model_seq;

create table core_role_model_core_status_model (
  core_role_model_id            bigint not null,
  core_status_model_id          bigint not null,
  constraint pk_core_role_model_core_status_model primary key (core_role_model_id,core_status_model_id)
);

create table core_status_model (
  id                            bigint not null,
  name                          varchar(255) not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_core_status_model_name unique (name),
  constraint pk_core_status_model primary key (id)
);
create sequence core_status_model_seq;

alter table core_role_model_core_status_model add constraint fk_core_role_model_core_status_model_core_role_model foreign key (core_role_model_id) references core_role_model (id) on delete restrict on update restrict;
create index ix_core_role_model_core_status_model_core_role_model on core_role_model_core_status_model (core_role_model_id);

alter table core_role_model_core_status_model add constraint fk_core_role_model_core_status_model_core_status_model foreign key (core_status_model_id) references core_status_model (id) on delete restrict on update restrict;
create index ix_core_role_model_core_status_model_core_status_model on core_role_model_core_status_model (core_status_model_id);


# --- !Downs

alter table core_role_model_core_status_model drop constraint if exists fk_core_role_model_core_status_model_core_role_model;
drop index if exists ix_core_role_model_core_status_model_core_role_model;

alter table core_role_model_core_status_model drop constraint if exists fk_core_role_model_core_status_model_core_status_model;
drop index if exists ix_core_role_model_core_status_model_core_status_model;

drop table if exists core_role_model;
drop sequence if exists core_role_model_seq;

drop table if exists core_role_model_core_status_model;

drop table if exists core_status_model;
drop sequence if exists core_status_model_seq;

