create database test;
create database test_0;
create database test_1;

use test_0;
create table app_order_0(
                            order_id bigint(16) primary key auto_increment,
                            add_time datetime not null ,
                            upd_time datetime not null on update CURRENT_TIMESTAMP);
create table app_order_1(
                            order_id bigint(16) primary key auto_increment,
                            add_time datetime not null ,
                            upd_time datetime not null on update CURRENT_TIMESTAMP);
use test_1;
create table app_order_0(
                            order_id bigint(16) primary key auto_increment,
                            add_time datetime not null ,
                            upd_time datetime not null on update CURRENT_TIMESTAMP);
create table app_order_1(
                            order_id bigint(16) primary key auto_increment,
                            add_time datetime not null ,
                            upd_time datetime not null on update CURRENT_TIMESTAMP);