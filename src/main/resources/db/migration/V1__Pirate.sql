create table crews
(
    id serial not null
        constraint crews_pkey
            primary key,
    captain varchar(255),
    nationality varchar(255),
    size integer not null
);

create table orders
(
    id serial not null
        constraint orders_pkey
            primary key,
    client varchar(255),
    description varchar(255),
    order_status varchar(255),
    reward integer not null,
    crew_id serial
        constraint fkoqlc23wl8bviqgumlyy4hsjik
            references crews
);

create table ships
(
    id serial not null
        constraint ships_pkey
            primary key,
    name varchar(255),
    ship_type varchar(255),
    speed integer not null,
    crew_id serial
        constraint fkobrhdshhfq0ugoo9vumrmgsiv
            references crews
);

create table hold_items
(
    id serial not null
        constraint hold_items_pkey
            primary key,
    hold_type varchar(255),
    name varchar(255),
    quantity integer not null,
    ship_id serial
        constraint fkic45fxwcgta0hbld8c8est27l
            references ships
);

create table weapons
(
    id serial not null
        constraint weapons_pkey
            primary key,
    name varchar(255),
    quantity integer not null,
    weapons_type varchar(255),
    wear varchar(255),
    ship_id serial
        constraint fkp837wr183pba3855q399btyvl
            references ships
);
