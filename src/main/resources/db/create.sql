 SET MODE PostgreSQL;

 CREATE TABLE IF NOT EXISTS restaurants (
 id int PRIMARY KEY auto_increment,
  name VARCHAR,
  address VARCHAR,
  zipcode VARCHAR,
  phone VARCHAR,
  website VARCHAR,
  email VARCHAR
 );

 CREATE TABLE IF NOT EXISTS foodtypes (
  id int PRIMARY KEY auto_increment,
  name VARCHAR
 );

 CREATE TABLE IF NOT EXISTS reviews (
  id int PRIMARY KEY auto_increment,
  written by VARCHAR,
  content VARCHAR,
  rating VARCHAR,
  restaurant id INTEGER
 );

 CREATE TABLE IF NOT EXISTS restaurants_foodtypes (
 id int PRIMARY KEY auto_increment,
 foodtypeid INTEGER,
 restaurant id INTEGER

 );