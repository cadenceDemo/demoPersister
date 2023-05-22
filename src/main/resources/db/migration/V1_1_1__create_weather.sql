CREATE TABLE IF NOT EXISTS currentWeather (
    id serial PRIMARY KEY,
    city VARCHAR ( 150 ) NOT NULL,
    temperature float NOT NULL,
    created_on TIMESTAMP NOT NULL
);