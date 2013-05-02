# Links

# --- !Ups

create table link (
  sourceid bigint not null references s1user(id) ON DELETE CASCADE,
  targetid bigint not null references s1user(id) ON DELETE CASCADE,
  note varchar(500)
);

# --- !Downs

drop table link;
