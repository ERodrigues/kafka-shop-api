create table Shop(
    id bigserial primary key auto_increment,
    identifier varchar not null,
    status varchar not null,
    date_shop date
);

create table Shop_Item (
    id bigserial primary key auto_increment,
    product_identifier varchar(100) not null,
    amount int not null,
    price float not null,
    shop_id bigint references shop(id)
);