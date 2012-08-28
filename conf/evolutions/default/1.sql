# --- First database schema - fixed

# --- !Ups

create sequence s_eventid;
create sequence s_speakerid;

create table speaker (
  id bigint not null DEFAULT nextval('s_eventid'),
  name varchar(255),
  title varchar(255),
  about text,
  email varchar(255),
  twtter varchar(255),
  url varchar(255),
  CONSTRAINT speaker_pkey PRIMARY KEY (id)
);

create table event (
  id bigint not null DEFAULT nextval('s_eventid'),
  title varchar(255),
  description text,
  starttime timestamp without time zone NOT NULL DEFAULT now(),
  endtime timestamp without time zone NOT NULL DEFAULT now(),
  location varchar(255),
  speakerid bigint not null references speaker(id),
  CONSTRAINT event_pkey PRIMARY KEY (id)
);

# --- !Downs

drop table speaker;
drop table event;

drop sequence s_eventid;
drop sequence s_speakerid;

