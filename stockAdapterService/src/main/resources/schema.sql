
create table if not exists message
(
    id                  bigint primary key generated always as identity,
    profile_id          bigint                        not null,
    message             jsonb                         not null,
    version             bigint                        not null
    );

