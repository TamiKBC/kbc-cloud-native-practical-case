create table users(
                      username text not null primary key,
                      password text not null,
                      enabled boolean not null
);
CREATE TABLE AUTHORITIES (
                             USERNAME TEXT NOT NULL,
                             AUTHORITY TEXT NOT NULL
);

alter table SHOPPING_LIST
add column username text;

insert into users (username, password, enabled) VALUES ('Tami','password',true);
insert into users (username, password, enabled) VALUES ('Harry','password',true);
insert into users (username, password, enabled) VALUES ('Ginny','password',true);
insert into users (username, password, enabled) VALUES ('Ron','password',true);