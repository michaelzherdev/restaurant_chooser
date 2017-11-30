-- DROP DATABASE IF EXISTS restaurant;
-- CREATE DATABASE restaurant;
--\c restaurant;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS menus CASCADE;
DROP TABLE IF EXISTS dishes CASCADE;
DROP TABLE IF EXISTS votes CASCADE;
DROP TABLE IF EXISTS menu_dish_link CASCADE;
DROP SEQUENCE IF EXISTS user_seq;
DROP SEQUENCE IF EXISTS restaurant_seq;
DROP SEQUENCE IF EXISTS menu_seq;
DROP SEQUENCE IF EXISTS dish_seq;
DROP SEQUENCE IF EXISTS vote_seq;

CREATE SEQUENCE user_seq START 1000;
CREATE SEQUENCE restaurant_seq START 1000;
CREATE SEQUENCE menu_seq START 1000;
CREATE SEQUENCE dish_seq START 1000;
CREATE SEQUENCE vote_seq START 1000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP           DEFAULT now(),
  enabled    BOOL                DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id         INTEGER PRIMARY KEY DEFAULT nextval('restaurant_seq'),
  name        VARCHAR            NOT NULL,
  description TEXT               NOT NULL
);

CREATE TABLE menus (
  id         INTEGER PRIMARY KEY DEFAULT nextval('menu_seq'),
  restaurant_id INTEGER            NOT NULL,
  day           TIMESTAMP DEFAULT now(),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE dishes (
  id         INTEGER PRIMARY KEY DEFAULT nextval('dish_seq'),
  name    VARCHAR            NOT NULL,
  price   REAL               NOT NULL
);

CREATE TABLE votes (
  id         INTEGER PRIMARY KEY DEFAULT nextval('vote_seq'),
  user_id       INTEGER            NOT NULL,
  restaurant_id INTEGER            NOT NULL,
  vote_time     TIMESTAMP DEFAULT now(),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE menu_dish_link (
  id            SERIAL PRIMARY KEY,
  menu_id       INTEGER            NOT NULL,
  dish_id       INTEGER            NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE,
  FOREIGN KEY (dish_id) REFERENCES dishes (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_dish_link_unique_menu_dish_idx ON menu_dish_link (menu_id, dish_id)
