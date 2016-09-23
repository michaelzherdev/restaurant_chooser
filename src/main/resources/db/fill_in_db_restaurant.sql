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

INSERT INTO restaurants (name, description, votes) VALUES
  ('Luxoria', 'A beautiful restaurant with delicious food', 0),
  ('Cornello', 'If you love eat, with us you can do it good', 1),
  ('Slavyanochka', 'Slavyanian cuisine. Taste of Eastern Europe.', 0);

INSERT INTO menus (restaurant_id, day) VALUES
  (1, now()), (2, now()), (3, now());

INSERT INTO dishes (name, price, menu_id) VALUES
  ('Hamburger', 2.45, 1),
  ('Lasagna', 3.5, 1),
  ('Gazpacho', 3.0, 1),
  ('Spaghetti', 1.5, 2),
  ('Solyanka', 1.5, 2),
  ('Fish soup', 1.75, 2),
  ('Kharcho', 1.7, 2),
  ('Khinkali', 2.5, 2),
  ('Kebab', 3.25, 3),
  ('Pork chop', 3.75, 3),
  ('Pizza', 3.0, 3),
  ('Baked pudding', 2.0, 3);