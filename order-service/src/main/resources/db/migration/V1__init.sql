create table orders
(
    id          bigserial primary key,
    user_id     bigint not null,
    total_price numeric(8,2) not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null,
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product numeric(8,2) not null,
    price             numeric(8,2) not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

