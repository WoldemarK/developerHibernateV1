--liquibase formatted sql
-- create type status as enum ('ACTIVE','DELETED');

create table if not exists specialty
(
    id   bigint primary key generated by default as identity,
    name varchar(255) unique
);
create table if not exists skill
(
    id   bigint primary key generated by default as identity,
    name varchar(255) unique
);
create table if not exists developer
(
    id          bigint primary key generated by default as identity,
    firstName   varchar(50) not null,
    lastName    varchar(50) not null,
    specialtyId bigint,
    foreign key (specialtyId) references specialty (id) on delete cascade on update no action

);
create table if not exists developer_skill
(
    developerID bigint,
    skillID     bigint,
    foreign key (developerID) references developer (id) on delete cascade on update no action,
    foreign key (skillID) references skill (id) on delete cascade on update no action
);

insert into specialty( name)
values ('Java'),
       ('C++'),
       ('php'),
       ('C#'),
       ('Sql'),
       ('JavaScript');
insert into skill(name)
values ('JDBC'),
       ('Spring'),
       ('Maven'),
       ('JPA + Hibernate'),
       ('Mockito'),
       ('JUnit5'),
       ('Liquibase');
insert into developer(firstName, lastName, specialtyId)
values ('Tom', 'Tomson', 1),
       ('Mark', 'Markov', 2),
       ('Sara', 'Sarman',1),
       ('Rik', 'Sanchs',3),
       ('Morty', 'Cran',5),
       ('Ilya', 'Gali',6);
insert into developer_skill(developerID, skillID) values (1,1),
                                                         (1,2),
                                                         (1,3),
                                                         (1,4),
                                                         (1,5),
                                                         (1,6),
                                                         (2,3),
                                                         (2,1),
                                                         (2,7),
                                                         (2,6),
                                                         (3,1),
                                                         (4,3),
                                                         (5,6),
                                                         (6,4);
