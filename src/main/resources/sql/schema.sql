CREATE TABLE IF NOT EXISTS messages (
    id                     VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    name                   VARCHAR      NOT NULL,
    text                   VARCHAR      NOT NULL
);

