
create table if not exists contract
(
    id                  bigint primary key generated always as identity,
    profile_id          bigint                        not null,
    number              varchar(128)                  not null,
    market_place_fx       boolean                       null,
    market_place_forts    boolean                       null,
    market_place_fond     boolean                       null,
    version             bigint                        not null
    );
