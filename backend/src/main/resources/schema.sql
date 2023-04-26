CREATE TABLE IF NOT EXISTS USERS (
    id serial PRIMARY KEY,
    name varchar(50) NOT NULL,
    email varchar(50) NOT NULL
);