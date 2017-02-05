# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table core_role_model (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  creation_date                 datetime(6) not null,
  update_date                   datetime(6) not null,
  constraint uq_core_role_model_name unique (name),
  constraint pk_core_role_model primary key (id)
);

create table core_role_model_core_status_model (
  core_role_model_id            bigint not null,
  core_status_model_id          bigint not null,
  constraint pk_core_role_model_core_status_model primary key (core_role_model_id,core_status_model_id)
);

create table core_status_model (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  creation_date                 datetime(6) not null,
  update_date                   datetime(6) not null,
  constraint uq_core_status_model_name unique (name),
  constraint pk_core_status_model primary key (id)
);

create table core_user_model (
  id                            bigint auto_increment not null,
  email                         varchar(255) not null,
  sha_password                  varbinary(64) not null,
  creation_date                 datetime(6) not null,
  update_date                   datetime(6) not null,
  constraint uq_core_user_model_email unique (email),
  constraint pk_core_user_model primary key (id)
);

create table core_user_model_core_status_model (
  core_user_model_id            bigint not null,
  core_status_model_id          bigint not null,
  constraint pk_core_user_model_core_status_model primary key (core_user_model_id,core_status_model_id)
);

create table core_user_model_core_role_model (
  core_user_model_id            bigint not null,
  core_role_model_id            bigint not null,
  constraint pk_core_user_model_core_role_model primary key (core_user_model_id,core_role_model_id)
);

alter table core_role_model_core_status_model add constraint fk_core_role_model_core_status_model_core_role_model foreign key (core_role_model_id) references core_role_model (id) on delete restrict on update restrict;
create index ix_core_role_model_core_status_model_core_role_model on core_role_model_core_status_model (core_role_model_id);

alter table core_role_model_core_status_model add constraint fk_core_role_model_core_status_model_core_status_model foreign key (core_status_model_id) references core_status_model (id) on delete restrict on update restrict;
create index ix_core_role_model_core_status_model_core_status_model on core_role_model_core_status_model (core_status_model_id);

alter table core_user_model_core_status_model add constraint fk_core_user_model_core_status_model_core_user_model foreign key (core_user_model_id) references core_user_model (id) on delete restrict on update restrict;
create index ix_core_user_model_core_status_model_core_user_model on core_user_model_core_status_model (core_user_model_id);

alter table core_user_model_core_status_model add constraint fk_core_user_model_core_status_model_core_status_model foreign key (core_status_model_id) references core_status_model (id) on delete restrict on update restrict;
create index ix_core_user_model_core_status_model_core_status_model on core_user_model_core_status_model (core_status_model_id);

alter table core_user_model_core_role_model add constraint fk_core_user_model_core_role_model_core_user_model foreign key (core_user_model_id) references core_user_model (id) on delete restrict on update restrict;
create index ix_core_user_model_core_role_model_core_user_model on core_user_model_core_role_model (core_user_model_id);

alter table core_user_model_core_role_model add constraint fk_core_user_model_core_role_model_core_role_model foreign key (core_role_model_id) references core_role_model (id) on delete restrict on update restrict;
create index ix_core_user_model_core_role_model_core_role_model on core_user_model_core_role_model (core_role_model_id);


# --- !Downs

alter table core_role_model_core_status_model drop foreign key fk_core_role_model_core_status_model_core_role_model;
drop index ix_core_role_model_core_status_model_core_role_model on core_role_model_core_status_model;

alter table core_role_model_core_status_model drop foreign key fk_core_role_model_core_status_model_core_status_model;
drop index ix_core_role_model_core_status_model_core_status_model on core_role_model_core_status_model;

alter table core_user_model_core_status_model drop foreign key fk_core_user_model_core_status_model_core_user_model;
drop index ix_core_user_model_core_status_model_core_user_model on core_user_model_core_status_model;

alter table core_user_model_core_status_model drop foreign key fk_core_user_model_core_status_model_core_status_model;
drop index ix_core_user_model_core_status_model_core_status_model on core_user_model_core_status_model;

alter table core_user_model_core_role_model drop foreign key fk_core_user_model_core_role_model_core_user_model;
drop index ix_core_user_model_core_role_model_core_user_model on core_user_model_core_role_model;

alter table core_user_model_core_role_model drop foreign key fk_core_user_model_core_role_model_core_role_model;
drop index ix_core_user_model_core_role_model_core_role_model on core_user_model_core_role_model;

drop table if exists core_role_model;

drop table if exists core_role_model_core_status_model;

drop table if exists core_status_model;

drop table if exists core_user_model;

drop table if exists core_user_model_core_status_model;

drop table if exists core_user_model_core_role_model;

