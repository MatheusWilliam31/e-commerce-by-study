create schema if not exists users;

create table users.user (
    id bigserial primary key,
    name varchar(100) not null,
    cpf varchar(100) not null,
    email varchar(100) not null,
    address varchar(100) not null,
    phoneNumber varchar(100) not null,
    registrationDate timestamp not null
);