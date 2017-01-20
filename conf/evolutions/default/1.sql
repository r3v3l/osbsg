# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table action_log_model (
  id                            bigint not null,
  remote_address                varchar(255) not null,
  current_host                  varchar(255) not null,
  current_method                varchar(255) not null,
  current_path                  varchar(255) not null,
  current_uri                   varchar(255) not null,
  current_version               varchar(255) not null,
  user_message                  TEXT not null,
  creationdate                  timestamp,
  constraint pk_action_log_model primary key (id)
);
create sequence action_log_model_seq;

create table business_card_model (
  id                            bigint not null,
  user_id                       bigint,
  firstname                     varchar(255) not null,
  lastname                      varchar(255) not null,
  email                         varchar(255) not null,
  phone                         varchar(255) not null,
  company                       varchar(255) not null,
  address                       varchar(255) not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint pk_business_card_model primary key (id)
);
create sequence business_card_model_seq;

create table company_model (
  id                            bigint not null,
  user                          bigint,
  law_form                      varchar(255) not null,
  country                       varchar(255) not null,
  name                          varchar(255) not null,
  street                        varchar(255) not null,
  city                          varchar(255) not null,
  postal_code                   varchar(255) not null,
  tax_number                    varchar(255) not null,
  creation_date                 timestamp not null,
  updated_date                  timestamp not null,
  constraint uq_company_model_user unique (user),
  constraint uq_company_model_name unique (name),
  constraint uq_company_model_tax_number unique (tax_number),
  constraint pk_company_model primary key (id)
);
create sequence company_model_seq;

create table contact_model (
  id                            bigint not null,
  response                      boolean not null,
  name                          varchar(255) not null,
  email                         varchar(255) not null,
  phone                         varchar(255) not null,
  message                       TEXT not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint pk_contact_model primary key (id)
);
create sequence contact_model_seq;

create table newsletter_model (
  id                            bigint not null,
  email                         varchar(255) not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_newsletter_model_email unique (email),
  constraint pk_newsletter_model primary key (id)
);
create sequence newsletter_model_seq;

create table page_content_model (
  id                            bigint not null,
  page                          bigint,
  title                         varchar(255) not null,
  content                       TEXT not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_page_content_model_page unique (page),
  constraint uq_page_content_model_title unique (title),
  constraint pk_page_content_model primary key (id)
);
create sequence page_content_model_seq;

create table page_meta_tags_model (
  id                            bigint not null,
  page                          bigint,
  title                         varchar(255) not null,
  description                   varchar(255) not null,
  keywords                      TEXT not null,
  robots                        varchar(255) not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_page_meta_tags_model_page unique (page),
  constraint uq_page_meta_tags_model_title unique (title),
  constraint uq_page_meta_tags_model_description unique (description),
  constraint pk_page_meta_tags_model primary key (id)
);
create sequence page_meta_tags_model_seq;

create table page_model (
  id                            bigint not null,
  name                          varchar(255) not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_page_model_name unique (name),
  constraint pk_page_model primary key (id)
);
create sequence page_model_seq;

create table page_model_status_model (
  page_model_id                 bigint not null,
  status_model_id               bigint not null,
  constraint pk_page_model_status_model primary key (page_model_id,status_model_id)
);

create table page_model_role_model (
  page_model_id                 bigint not null,
  role_model_id                 bigint not null,
  constraint pk_page_model_role_model primary key (page_model_id,role_model_id)
);

create table page_open_graph_tags_image_model (
  id                            bigint not null,
  page                          bigint,
  name                          varchar(255) not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_page_open_graph_tags_image_model_page unique (page),
  constraint uq_page_open_graph_tags_image_model_name unique (name),
  constraint pk_page_open_graph_tags_image_model primary key (id)
);
create sequence page_open_graph_tags_image_model_seq;

create table page_open_graph_tags_model (
  id                            bigint not null,
  page                          bigint,
  title                         varchar(255) not null,
  description                   varchar(255) not null,
  tag_type                      varchar(255) not null,
  url                           varchar(255) not null,
  fb_admins                     varchar(255) not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_page_open_graph_tags_model_page unique (page),
  constraint uq_page_open_graph_tags_model_title unique (title),
  constraint uq_page_open_graph_tags_model_description unique (description),
  constraint uq_page_open_graph_tags_model_url unique (url),
  constraint pk_page_open_graph_tags_model primary key (id)
);
create sequence page_open_graph_tags_model_seq;

create table response_contact_model (
  id                            bigint not null,
  contact                       bigint,
  response                      TEXT not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_response_contact_model_contact unique (contact),
  constraint pk_response_contact_model primary key (id)
);
create sequence response_contact_model_seq;

create table role_model (
  id                            bigint not null,
  name                          varchar(255) not null,
  creation_date                 timestamp not null,
  updated_date                  timestamp not null,
  constraint uq_role_model_name unique (name),
  constraint pk_role_model primary key (id)
);
create sequence role_model_seq;

create table role_model_status_model (
  role_model_id                 bigint not null,
  status_model_id               bigint not null,
  constraint pk_role_model_status_model primary key (role_model_id,status_model_id)
);

create table status_model (
  id                            bigint not null,
  name                          varchar(255) not null,
  creation_date                 timestamp not null,
  updated_date                  timestamp not null,
  constraint uq_status_model_name unique (name),
  constraint pk_status_model primary key (id)
);
create sequence status_model_seq;

create table user_model (
  id                            bigint not null,
  email                         varchar(255) not null,
  sha_password                  varbinary(64) not null,
  creation_date                 timestamp not null,
  updated_date                  timestamp not null,
  constraint uq_user_model_email unique (email),
  constraint pk_user_model primary key (id)
);
create sequence user_model_seq;

create table user_model_status_model (
  user_model_id                 bigint not null,
  status_model_id               bigint not null,
  constraint pk_user_model_status_model primary key (user_model_id,status_model_id)
);

create table user_model_role_model (
  user_model_id                 bigint not null,
  role_model_id                 bigint not null,
  constraint pk_user_model_role_model primary key (user_model_id,role_model_id)
);

create table user_profile_model (
  id                            bigint not null,
  user                          bigint,
  firstname                     varchar(255) not null,
  lastname                      varchar(255) not null,
  biography                     TEXT not null,
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_user_profile_model_user unique (user),
  constraint pk_user_profile_model primary key (id)
);
create sequence user_profile_model_seq;

create table user_profile_photo_model (
  id                            bigint not null,
  user                          bigint,
  photo                         varchar(255),
  creation_date                 timestamp not null,
  update_date                   timestamp not null,
  constraint uq_user_profile_photo_model_user unique (user),
  constraint pk_user_profile_photo_model primary key (id)
);
create sequence user_profile_photo_model_seq;

alter table business_card_model add constraint fk_business_card_model_user_id foreign key (user_id) references user_model (id) on delete restrict on update restrict;
create index ix_business_card_model_user_id on business_card_model (user_id);

alter table company_model add constraint fk_company_model_user foreign key (user) references user_model (id) on delete restrict on update restrict;

alter table page_content_model add constraint fk_page_content_model_page foreign key (page) references page_model (id) on delete restrict on update restrict;

alter table page_meta_tags_model add constraint fk_page_meta_tags_model_page foreign key (page) references page_model (id) on delete restrict on update restrict;

alter table page_model_status_model add constraint fk_page_model_status_model_page_model foreign key (page_model_id) references page_model (id) on delete restrict on update restrict;
create index ix_page_model_status_model_page_model on page_model_status_model (page_model_id);

alter table page_model_status_model add constraint fk_page_model_status_model_status_model foreign key (status_model_id) references status_model (id) on delete restrict on update restrict;
create index ix_page_model_status_model_status_model on page_model_status_model (status_model_id);

alter table page_model_role_model add constraint fk_page_model_role_model_page_model foreign key (page_model_id) references page_model (id) on delete restrict on update restrict;
create index ix_page_model_role_model_page_model on page_model_role_model (page_model_id);

alter table page_model_role_model add constraint fk_page_model_role_model_role_model foreign key (role_model_id) references role_model (id) on delete restrict on update restrict;
create index ix_page_model_role_model_role_model on page_model_role_model (role_model_id);

alter table page_open_graph_tags_image_model add constraint fk_page_open_graph_tags_image_model_page foreign key (page) references page_model (id) on delete restrict on update restrict;

alter table page_open_graph_tags_model add constraint fk_page_open_graph_tags_model_page foreign key (page) references page_model (id) on delete restrict on update restrict;

alter table response_contact_model add constraint fk_response_contact_model_contact foreign key (contact) references contact_model (id) on delete restrict on update restrict;

alter table role_model_status_model add constraint fk_role_model_status_model_role_model foreign key (role_model_id) references role_model (id) on delete restrict on update restrict;
create index ix_role_model_status_model_role_model on role_model_status_model (role_model_id);

alter table role_model_status_model add constraint fk_role_model_status_model_status_model foreign key (status_model_id) references status_model (id) on delete restrict on update restrict;
create index ix_role_model_status_model_status_model on role_model_status_model (status_model_id);

alter table user_model_status_model add constraint fk_user_model_status_model_user_model foreign key (user_model_id) references user_model (id) on delete restrict on update restrict;
create index ix_user_model_status_model_user_model on user_model_status_model (user_model_id);

alter table user_model_status_model add constraint fk_user_model_status_model_status_model foreign key (status_model_id) references status_model (id) on delete restrict on update restrict;
create index ix_user_model_status_model_status_model on user_model_status_model (status_model_id);

alter table user_model_role_model add constraint fk_user_model_role_model_user_model foreign key (user_model_id) references user_model (id) on delete restrict on update restrict;
create index ix_user_model_role_model_user_model on user_model_role_model (user_model_id);

alter table user_model_role_model add constraint fk_user_model_role_model_role_model foreign key (role_model_id) references role_model (id) on delete restrict on update restrict;
create index ix_user_model_role_model_role_model on user_model_role_model (role_model_id);

alter table user_profile_model add constraint fk_user_profile_model_user foreign key (user) references user_model (id) on delete restrict on update restrict;

alter table user_profile_photo_model add constraint fk_user_profile_photo_model_user foreign key (user) references user_model (id) on delete restrict on update restrict;


# --- !Downs

alter table business_card_model drop constraint if exists fk_business_card_model_user_id;
drop index if exists ix_business_card_model_user_id;

alter table company_model drop constraint if exists fk_company_model_user;

alter table page_content_model drop constraint if exists fk_page_content_model_page;

alter table page_meta_tags_model drop constraint if exists fk_page_meta_tags_model_page;

alter table page_model_status_model drop constraint if exists fk_page_model_status_model_page_model;
drop index if exists ix_page_model_status_model_page_model;

alter table page_model_status_model drop constraint if exists fk_page_model_status_model_status_model;
drop index if exists ix_page_model_status_model_status_model;

alter table page_model_role_model drop constraint if exists fk_page_model_role_model_page_model;
drop index if exists ix_page_model_role_model_page_model;

alter table page_model_role_model drop constraint if exists fk_page_model_role_model_role_model;
drop index if exists ix_page_model_role_model_role_model;

alter table page_open_graph_tags_image_model drop constraint if exists fk_page_open_graph_tags_image_model_page;

alter table page_open_graph_tags_model drop constraint if exists fk_page_open_graph_tags_model_page;

alter table response_contact_model drop constraint if exists fk_response_contact_model_contact;

alter table role_model_status_model drop constraint if exists fk_role_model_status_model_role_model;
drop index if exists ix_role_model_status_model_role_model;

alter table role_model_status_model drop constraint if exists fk_role_model_status_model_status_model;
drop index if exists ix_role_model_status_model_status_model;

alter table user_model_status_model drop constraint if exists fk_user_model_status_model_user_model;
drop index if exists ix_user_model_status_model_user_model;

alter table user_model_status_model drop constraint if exists fk_user_model_status_model_status_model;
drop index if exists ix_user_model_status_model_status_model;

alter table user_model_role_model drop constraint if exists fk_user_model_role_model_user_model;
drop index if exists ix_user_model_role_model_user_model;

alter table user_model_role_model drop constraint if exists fk_user_model_role_model_role_model;
drop index if exists ix_user_model_role_model_role_model;

alter table user_profile_model drop constraint if exists fk_user_profile_model_user;

alter table user_profile_photo_model drop constraint if exists fk_user_profile_photo_model_user;

drop table if exists action_log_model;
drop sequence if exists action_log_model_seq;

drop table if exists business_card_model;
drop sequence if exists business_card_model_seq;

drop table if exists company_model;
drop sequence if exists company_model_seq;

drop table if exists contact_model;
drop sequence if exists contact_model_seq;

drop table if exists newsletter_model;
drop sequence if exists newsletter_model_seq;

drop table if exists page_content_model;
drop sequence if exists page_content_model_seq;

drop table if exists page_meta_tags_model;
drop sequence if exists page_meta_tags_model_seq;

drop table if exists page_model;
drop sequence if exists page_model_seq;

drop table if exists page_model_status_model;

drop table if exists page_model_role_model;

drop table if exists page_open_graph_tags_image_model;
drop sequence if exists page_open_graph_tags_image_model_seq;

drop table if exists page_open_graph_tags_model;
drop sequence if exists page_open_graph_tags_model_seq;

drop table if exists response_contact_model;
drop sequence if exists response_contact_model_seq;

drop table if exists role_model;
drop sequence if exists role_model_seq;

drop table if exists role_model_status_model;

drop table if exists status_model;
drop sequence if exists status_model_seq;

drop table if exists user_model;
drop sequence if exists user_model_seq;

drop table if exists user_model_status_model;

drop table if exists user_model_role_model;

drop table if exists user_profile_model;
drop sequence if exists user_profile_model_seq;

drop table if exists user_profile_photo_model;
drop sequence if exists user_profile_photo_model_seq;

