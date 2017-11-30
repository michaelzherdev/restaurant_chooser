DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM votes;

ALTER SEQUENCE user_seq RESTART WITH 1000;
ALTER SEQUENCE restaurant_seq RESTART WITH 1000;
ALTER SEQUENCE menu_seq RESTART WITH 1000;
ALTER SEQUENCE dish_seq RESTART WITH 1000;
ALTER SEQUENCE vote_seq RESTART WITH 1000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', '$2a$10$gM/KJ4qlO2yWTVBJcHhV3.TIUsGQV/wGW9mRUL1nEvQzx3vaxHVTS'), --admin
  ('User', 'user@yandex.ru', '$2a$10$f6OL5eOTCOEgLVxvErO98OPBWgvs8j/no0kze3N9ME7fXCRGI26s.');   --user

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 1000),
  ('ROLE_USER', 1001);

INSERT INTO restaurants (name, description) VALUES
  ('Luxoria', 'A beautiful restaurant with calm and comfortable atmosphere'),
  ('Frau Engel', 'If you love eat, with us you can do it good. Herzlich willkommen!'),
  ('Slavyanka', 'Slavonic cuisine. Taste of Eastern Europe.');

INSERT INTO menus (restaurant_id, day) VALUES
  (1000, '2017-11-30 00:00:00'), (1001, '2017-12-01 00:00:00'), (1002, '2017-12-01 00:00:00'), (1000, '2017-12-01 00:01:00');
--
INSERT INTO dishes (name, price) VALUES
  ('Hamburger', 2.45),
  ('Lasagna', 3.5),
  ('Gazpacho', 3.0),
  ('Spaghetti', 1.5),
  ('Baked pudding', 1.5),
  ('Fish soup', 1.75),
  ('Pork chop', 1.7),
  ('Kebab', 2.5),
  ('Cheburek', 3.25),
  ('Kharcho soup', 3.75),
  ('Pizza', 3.0),
  ('Solyanka', 2.0);

INSERT INTO menu_dish_link (menu_id, dish_id) VALUES
  (1000, 1000),(1000, 1001),(1000, 1002),(1000, 1003),
  (1001, 1004),(1001, 1001),(1001, 1006),(1001, 1007),
  (1002, 1008),(1002, 1009),(1002, 1010),(1002, 1011);

INSERT INTO votes (vote_time, user_id, restaurant_id) VALUES ('2017-12-01 10:00:00', 1001, 1001);