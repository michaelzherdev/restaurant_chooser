DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 1000;

INSERT INTO users (name, email, password) VALUES
	('Admin', 'admin@gmail.com', 'admin'),
	('User', 'user@yandex.ru', 'user'),
	('Vasya', 'vasya@mail.ru', 'qwerty');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 1000),
  ('ROLE_USER', 1000),
  ('ROLE_USER', 1001),
  ('ROLE_USER', 1002);

 INSERT INTO restaurants (name, description) VALUES
 ('Luxoria', 'A beautiful restaurant with delicious food'),
 ('Cornello', 'If you love eat, with us you can do it good'),
 ('Slavyanochka', 'Slavyanian cuisine. Taste of Eastern Europe.');

INSERT INTO dishes (name, price) VALUES
  ('Hamburger', 2.45),
  ('Lasagna', 3.5),
  ('Gazpacho', 3.0),
  ('Spaghetti', 1.5),
  ('Solyanka', 1.5),
  ('Fish soup', 1.75),
  ('Kharcho', 1.7),
  ('Khinkali', 2.5),
  ('Kebab', 3.25),
  ('Okroshka', 1.8),
  ('Pork chop', 3.75),
  ('Meat dumplings', 2.75),
  ('Pizza', 3.0),
  ('Ragout', 2.5),
  ('Baked pudding', 2.0);