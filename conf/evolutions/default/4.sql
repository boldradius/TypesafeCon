# --- Users

# --- !Ups

create sequence s_userid;

create table s1user (
  id bigint not null DEFAULT nextval('s_userid'),
  name varchar(255),
  email varchar(255),
  twitter varchar(255),
  facebook varchar(255),
  phone varchar(255),
  website varchar(255),
  CONSTRAINT user_pkey PRIMARY KEY (id)
);

# --- !Downs

drop table s1user;
drop sequence s_userid;
