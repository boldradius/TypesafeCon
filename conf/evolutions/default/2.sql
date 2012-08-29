# --- First database schema - fixed

# --- !Ups

alter table event add column code varchar(255) not null;

# --- !Downs

alter table event drop column code;
