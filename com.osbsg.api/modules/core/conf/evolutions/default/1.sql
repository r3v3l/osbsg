# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table core_status_model (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  creation_date                 datetime(6) not null,
  update_date                   datetime(6) not null,
  constraint uq_core_status_model_name unique (name),
  constraint pk_core_status_model primary key (id)
);


# --- !Downs

drop table if exists core_status_model;

