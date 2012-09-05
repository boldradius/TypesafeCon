# --- Messages

# --- !Ups

create sequence s_messageid;

create table message (
  id bigint not null DEFAULT nextval('s_messageid'),
  senderid bigint not null references s1user(id),
  senttime timestamp without time zone NOT NULL DEFAULT now(),
  content varchar(255) not null,
  index bigint not null DEFAULT 0,
  eventid bigint references event(id),
  touserid bigint references s1user(id),
  CONSTRAINT message_pkey PRIMARY KEY (id)
);

# --- !Downs

drop table message;
drop sequence s_messageid;
