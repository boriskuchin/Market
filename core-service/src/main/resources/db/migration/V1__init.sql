create table if not exists products
(
    id          bigserial primary key,
    name        varchar(255),
    price        double
);

insert into products (name, price)
values ('Хлеб', 10.5),
       ('Соль', 12),
       ('Сахар', 15.3),
       ('Мука', 11),
       ('Курица', 20),
       ('Говядина', 30.3),
       ('Рыба', 35.2),
       ('Краб', 50.1),
       ('Икра', 80.6),
       ('Макароны', 16),
       ('Картошка', 9),
       ('Нут', 13),
       ('Спаржа', 15),
       ('Капуста', 6),
       ('Брокколи', 17.95),
       ('Сыр', 17.65),
       ('Колбаса', 22.99),
       ('Кетчуп', 12.76),
       ('Свинина', 22.2),
       ('Молоко', 10);



create table users (
                       id         bigserial primary key,
                       username   varchar(255) not null,
                       password   varchar(255) not null,
                       email      varchar(50) unique);

create table roles (
                       id         bigserial primary key,
                       name       varchar(255) not null
);

CREATE TABLE users_roles (
                             user_id bigint not null references users (id),
                             role_id bigint not null references roles (id),
                             primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price numeric(8,2) not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product numeric(8,2) not null,
    price             numeric(8,2) not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

