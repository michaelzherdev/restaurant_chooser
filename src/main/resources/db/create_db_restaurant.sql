-- DROP DATABASE IF EXISTS restaurant;
-- CREATE DATABASE restaurant;
-- \c restaurant;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS votes;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id		 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id          SERIAL PRIMARY KEY NOT NULL,
  name       VARCHAR NOT NULL,
  description TEXT NOT NULL
);

CREATE TABLE dishes (
	id          SERIAL PRIMARY KEY NOT NULL,
	name       	VARCHAR NOT NULL,
	price		REAL NOT NULL
);

CREATE TABLE votes (
	id			SERIAL PRIMARY KEY NOT NULL,
	user_id		INTEGER NOT NULL,
	vote_time	TIMESTAMP DEFAULT now(),
	FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);