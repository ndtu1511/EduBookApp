create database EduBookApp;
use EduBookApp;
create table `comment`(
	id int (10) unsigned not null,
    content varchar(500) not null,
    created datetime not null,
    book_id int(10) unsigned not null,
    user_id int(10) unsigned not null
);
create table category(
	`id` int(10) unsigned not null,
    `name` varchar(100) not null,
    image_link varchar(255)
    );
insert into category(`id`,`name`,image_link) values
(1,'AI & Machine Learning','/resources/img/ai.jpg'),
(2,'Programming Language','/resources/img/pg.jpg'),
(3,'Web Development & Design','/resources/img/wd.jpg'),
(4,'Databases & Big Data','/resources/img/sql.jpg'),
(5,'Network Security','/resources/img/hack.jpg'),
(6,'Operating Systems','/resources/img/linux.jpg');

create table publisher(
	id int(10) unsigned not null,
    `name` varchar(100) not null
);
create table author(
	id int(10) unsigned not null,
    `name` nvarchar(100) not null,
    image_link varchar(255)
);
create table book (
	id int(10) unsigned not null,
	isbn varchar(60) not null,
    title nvarchar(255) not null,
    brief text not null,
    publisher_id int(10) unsigned not null,
    publish_date date not null,
    `language` varchar(20) not null,
    image_link varchar(255),
    category_id int(10) unsigned not null
);
create table author_book(
	book_id int(10) unsigned not null,
    author_id int(10) unsigned not null
);
create table `user` (
	id int(10) unsigned not null,
    username varchar(100) not null,
    email varchar(255) not null,
    `password` varchar(60) not null
);
create table `role`(
	id int(10) unsigned not null,
    `name` varchar(50) not null
);
create table user_role(
	user_id int(10) unsigned not null,
    role_id int(10) unsigned not null
);
ALTER TABLE `comment`
  ADD PRIMARY KEY (id),
  ADD Key book_id(book_id),
  add key user_id(user_id);
ALTER TABLE category
  ADD PRIMARY KEY (id);
ALTER TABLE publisher
  ADD PRIMARY KEY (id);
ALTER TABLE author
  ADD PRIMARY KEY (id);
ALTER TABLE book
  ADD PRIMARY KEY (id),
  ADD KEY publisher_id(publisher_id),
  ADD KEY category_id(category_id);
ALTER TABLE author_book
  ADD PRIMARY KEY (book_id,author_id),
  ADD KEY author_id(author_id);
ALTER TABLE `user`
  ADD PRIMARY KEY (id);
  ALTER TABLE `role`
  ADD PRIMARY KEY (id);
ALTER TABLE user_role
  ADD PRIMARY KEY (user_id,role_id),
  ADD KEY role_id(role_id);
  ALTER TABLE `comment`
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
  ALTER TABLE book
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE category
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
ALTER TABLE publisher
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE author
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE `user`
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE `role`
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
Alter table book
	ADD CONSTRAINT fk_publisher FOREIGN KEY (publisher_id) REFERENCES publisher (id) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category( id) ON DELETE CASCADE ON UPDATE CASCADE;
Alter table author_book
	ADD CONSTRAINT author_book_ibfk_1 FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT author_book_ibfk_2 FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE user_role
  ADD CONSTRAINT user_role_ibfk_1 FOREIGN KEY (user_id) REFERENCES `user` (id) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT user_role_ibfk_2 FOREIGN KEY (role_id) REFERENCES `role` (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (book_id) REFERENCES book (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (user_id) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
