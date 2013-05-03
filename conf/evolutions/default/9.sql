# --- Link email flag

# --- !Ups

alter table s1user add column contactEmailSent boolean not null default false;

# --- !Downs

alter table s1User drop column contactEmailSent;
