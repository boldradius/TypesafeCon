# --- User locations

# --- !Ups

alter table s1user add column firstname varchar(255);
alter table s1user add column lastname varchar(255);
alter table s1user drop column name;

# --- !Downs

alter table s1user add column name varchar(255);
alter table s1user drop column firstname;
alter table s1user drop column lastname;