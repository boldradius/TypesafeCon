# --- First database schema - fixed

# --- !Ups

create table speakerevent (
  speakerid bigint not null references speaker(id) on delete cascade,
  eventid bigint not null references event(id) on delete cascade,
  CONSTRAINT speakerevent_pkey PRIMARY KEY (speakerid, eventid)
);

alter table event drop column speakerid;

# --- !Downs

drop table speakerevent;
alter table event add column speakerid bigint not null references speaker(id);