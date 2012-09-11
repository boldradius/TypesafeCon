# --- User locations

# --- !Ups

alter table s1user add column latitude varchar(255);
alter table s1user add column longitude varchar(255);
alter table s1user add column locationtime timestamp without time zone;

# --- !Downs

alter table s1user drop column latitude;
alter table s1user drop column longitude;
alter table s1user drop column locationtime;