drop table databasechangeloglock cascade;

drop table databasechangelog cascade;

drop table roles cascade;

drop table users_has_users cascade;

drop table messages cascade;

drop table users cascade;

INSERT INTO public.roles (id, name)
VALUES (1, 'admin');

INSERT INTO public.roles (id, name)
VALUES (2, 'user');

