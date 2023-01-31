create table `order`(
                        order_id bigint(16) primary key auto_increment,
                        add_time datetime not null ,
                        upd_time datetime not null on update CURRENT_TIMESTAMP);