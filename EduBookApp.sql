create database EduBookApp;
use EduBookApp;
create table `comment`(
	id int (10) unsigned not null,
    content varchar(500) not null,
    created datetime not null,
    book_id int(10) unsigned not null,
    user_id int(10) unsigned not null
);
create table `like`(
	id int (10) unsigned not null,
	book_id int(10) unsigned not null,
    user_id int(10) unsigned not null
);
create table download(
	id int (10) unsigned not null,
	book_id int(10) unsigned not null,
    user_id int(10) unsigned not null,
    download_date datetime not null,
    current_page int(10) not null
);
create table `pending_comment`(
	id int (10) unsigned not null,
    content varchar(500) not null,
    created datetime not null,
    book_name nvarchar(255) not null,
    username varchar(100) not null
);
create table category(
	`id` int(10) unsigned not null,
    `name` varchar(100) not null,
    image_link varchar(255)
    );
create table book (
	id int(10) unsigned not null,
    title nvarchar(255) not null,
    brief text not null,
    publisher_name varchar(255) not null,
    publish_date date not null,
    `language` varchar(20) not null,
    image_link varchar(255),
    category_id int(10) unsigned not null,
    request_user_id int(10) unsigned not null,
    author_name varchar(255) not null,
    number_of_pages int(10) not null,
    content_link varchar(255)
);
create table pending_book (
	id int(10) unsigned not null,
    title nvarchar(255) not null,
    brief text not null,
    publisher_name varchar(255) not null,
    author_name varchar(255) not null,
    publish_date date not null,
    `language` varchar(20) not null,
    image_link varchar(255),
    category_id int(10) unsigned not null,
    number_of_pages int(10) not null,
    request_username varchar(255) not null,
    content_link varchar(255)
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
 ALTER TABLE `like`
	ADD PRIMARY KEY (id),
  ADD Key book_id(book_id),
  add key user_id(user_id);
ALTER TABLE download
ADD PRIMARY KEY (id),
  ADD Key book_id(book_id),
  add key user_id(user_id);
ALTER TABLE `pending_comment`
  ADD PRIMARY KEY (id);
ALTER TABLE category
  ADD PRIMARY KEY (id);
ALTER TABLE book
  ADD PRIMARY KEY (id),
  Add key request_user_id(request_user_id),
  ADD KEY category_id(category_id);
ALTER TABLE `user`
  ADD PRIMARY KEY (id);
  ALTER TABLE `role`
  ADD PRIMARY KEY (id);
ALTER TABLE pending_book
  ADD PRIMARY KEY (id),
  ADD KEY category_id(category_id);
ALTER TABLE user_role
  ADD PRIMARY KEY (user_id,role_id),
  ADD KEY role_id(role_id);
  ALTER TABLE `comment`
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
  ALTER TABLE `like`
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
   ALTER TABLE download
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
   ALTER TABLE `pending_comment`
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
  ALTER TABLE book
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
   ALTER TABLE pending_book
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE category
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
ALTER TABLE `user`
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
ALTER TABLE `role`
  MODIFY id int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
Alter table book
    ADD CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category( id) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT fk_request_user FOREIGN KEY (request_user_id) REFERENCES `user`( id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE user_role
  ADD CONSTRAINT user_role_ibfk_1 FOREIGN KEY (user_id) REFERENCES `user` (id) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT user_role_ibfk_2 FOREIGN KEY (role_id) REFERENCES `role` (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (book_id) REFERENCES book (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (user_id) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `like`
  ADD CONSTRAINT `like_ibfk_1` FOREIGN KEY (book_id) REFERENCES book (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `like_ibfk_2` FOREIGN KEY (user_id) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE download
  ADD CONSTRAINT `download_ibfk_1` FOREIGN KEY (book_id) REFERENCES book (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `download_ibfk_2` FOREIGN KEY (user_id) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
  Alter table pending_book
    ADD CONSTRAINT fk_category_pending FOREIGN KEY (category_id) REFERENCES category( id) ON DELETE CASCADE ON UPDATE CASCADE;

insert into category(`id`,`name`,image_link) values
(1,'AI & Machine Learning','/resources/img/ai.jpg'),
(2,'Programming Language','/resources/img/pg.jpg'),
(3,'Web Development & Design','/resources/img/wd.jpg'),
(4,'Databases & Big Data','/resources/img/sql.jpg'),
(5,'Network Security','/resources/img/hack.jpg'),
(6,'Operating Systems','/resources/img/linux.jpg');