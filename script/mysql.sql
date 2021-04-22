drop database if exists database spring_security_mydb;
create database spring_security_mydb
character set utf8mb4
collate utf8mb4_general_ci;


create table t_user(id int  not null primary key,username varchar(50) not null,password varchar(500) not null,enabled boolean not null, expiredate date);

create table t_role(id int  not null primary key,name varchar(50) not null);

create table t_permit(id int  not null primary key,name varchar(50) not null,code varchar(50) not null,url varchar(500) not null);

create table t_user_role(id int  not null primary key,userid int not null,roleid  int not null);

create table t_role_permit(id int  not null primary key,userid int not null,roleid  int not null);


insert into t_user(1,'admin','123456',1,null);
insert into t_user(2,'user','123456',1,null);
insert into t_user(3,'guest','123456',1,null);

insert into t_role(1,'admin_role');
insert into t_role(2,'user_role');
insert into t_role(3,'guest_role');

insert into t_user_role(1,1,1);
insert into t_user_role(2,2,2);
insert into t_user_role(3,3,3);

insert into t_permit(1,'user add','100000','/add');
insert into t_permit(2,'user modify','100001','/add');
insert into t_permit(3,'user delete','100002','/add');