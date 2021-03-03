create table merchant
(
    merchant_id   long(11)     not null primary key AUTO_INCREMENT,
    merchant_name varchar(200) not null,
    merchant_type varchar(200),
    legal_person  varchar(200),
    come_in_time  datetime
);

create table store
(
    store_id       long(11)     not null primary key AUTO_INCREMENT,
    store_name     varchar(200) not null,
    address        varchar(200),
    business_hours varchar(200),
    mobile         varchar(200)
);

create table merchant_store_rel
(
    merchant_id long(11) not null,
    store_id    long(11) not null
);

