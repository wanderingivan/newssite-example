DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `chats_join_table`;
DROP TABLE IF EXISTS `messages`;
DROP TABLE IF EXISTS `chats`;
DROP TABLE IF EXISTS `paragraphs`;
DROP TABLE IF EXISTS `articles`;
DROP TABLE IF EXISTS `group_authorities`;
DROP TABLE IF EXISTS `group_members`;
DROP TABLE IF EXISTS `groups`;
DROP TABLE IF EXISTS `acl_entry`;
DROP TABLE IF EXISTS `acl_object_identity`;
DROP TABLE IF EXISTS `acl_class`;
DROP TABLE IF EXISTS `acl_sid`;
DROP TABLE IF EXISTS `tasks`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;


CREATE TABLE users (
	user_id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(125) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    details VARCHAR(250),
	imagePath VARCHAR(250),
	createdOn TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    enabled TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY(user_id)
)ENGINE=InnoDB;



CREATE TABLE articles (
	article_id BIGINT NOT NULL AUTO_INCREMENT,
	headline VARCHAR(50) NOT NULL UNIQUE,
	category VARCHAR(50) NOT NULL,
	author_id BIGINT NOT NULL,
	caption VARCHAR(250) NOT NULL,
	imagePath VARCHAR(250),
	lastEdited TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    hits BIGINT NULL DEFAULT '0',
	CONSTRAINT author_id_fk FOREIGN KEY(author_id) REFERENCES users(user_id) ON DELETE CASCADE,
	PRIMARY KEY(article_id)
)ENGINE=InnoDB;


CREATE TABLE paragraphs (
	paragraph_id BIGINT NOT NULL AUTO_INCREMENT,
	text VARCHAR(1500) NOT NULL,
	article_id BIGINT NOT NULL,
	article_order INTEGER NOT NULL,
	CONSTRAINT article_id_fk_p FOREIGN KEY(article_id) REFERENCES articles(article_id) ON DELETE CASCADE,
	PRIMARY KEY(paragraph_id)
)ENGINE=InnoDB; 

CREATE TABLE comments (
	comment_id BIGINT NOT NULL AUTO_INCREMENT,
	message VARCHAR(150) NOT NULL,
	posted TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	votes BIGINT NOT NULL DEFAULT 0,
	poster_id BIGINT NOT NULL,
	article_id BIGINT NOT NULL,
	CONSTRAINT poster_id_fk FOREIGN KEY(poster_id) REFERENCES users(user_id)  ON DELETE CASCADE,
	CONSTRAINT article_id_fk FOREIGN KEY(article_id) REFERENCES articles(article_id)  ON DELETE CASCADE,
	PRIMARY KEY(comment_id)
)ENGINE=InnoDB;

CREATE TABLE chats (
	chat_id BIGINT NOT NULL AUTO_INCREMENT,
   	lastUpdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(chat_id)
)ENGINE=InnoDB;

CREATE TABLE messages (
    message_id BIGINT NOT NULL AUTO_INCREMENT,
	message VARCHAR(150) NOT NULL,
	posted TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	poster_id BIGINT NOT NULL,
	chat_id BIGINT NOT NULL,
	`read` TINYINT NOT NULL DEFAULT 0,
	CONSTRAINT poster_id_msg_fk FOREIGN KEY(poster_id) REFERENCES users(user_id)  ON DELETE CASCADE,
	CONSTRAINT chat_id_fk FOREIGN KEY(chat_id) REFERENCES chats(chat_id)  ON DELETE CASCADE,
	PRIMARY KEY(message_id)
)ENGINE=InnoDB;

CREATE TABLE chats_join_table(
	user_id BIGINT,
	chat_id BIGINT,
	PRIMARY KEY(`user_id`,`chat_id`),
	CONSTRAINT user_id_fk FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT chat_id_join_fk FOREIGN KEY(chat_id) REFERENCES chats(chat_id)
)ENGINE=InnoDB;

CREATE TABLE groups (
	id BIGINT NOT NULL AUTO_INCREMENT,
 	group_name VARCHAR(50) NOT NULL,
 	PRIMARY KEY(id)
)ENGINE=InnoDB;

CREATE TABLE group_authorities (
	group_id BIGINT NOT NULL AUTO_INCREMENT,
	authority VARCHAR(50) NOT NULL,
	CONSTRAINT group_id_fk FOREIGN KEY(group_id) REFERENCES groups(id)
)ENGINE=InnoDB;

CREATE TABLE group_members (
	id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(50),
	group_id BIGINT NOT NULL,
	CONSTRAINT usernamee_fk FOREIGN KEY(username) REFERENCES users(username) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT groupidi_fk FOREIGN KEY(group_id) REFERENCES groups(id),
	PRIMARY KEY(id)
)ENGINE=InnoDB;

CREATE TABLE tasks (
	task_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  	name VARCHAR(250) NOT NULL,
  	description VARCHAR(500) NOT NULL,
  	message VARCHAR(500),
	assigner VARCHAR(50) NOT NULL,
    assignee VARCHAR(50) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	completed TIMESTAMP NULL DEFAULT NULL,
	complete TINYINT NOT NULL DEFAULT 0,
	CONSTRAINT assigner_fk FOREIGN KEY(assigner) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT assignee_fk FOREIGN KEY(assignee) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY(task_id)
);

-- Group authority setup

INSERT INTO groups(group_name,id) VALUES("users",1);
INSERT INTO groups(group_name,id) VALUES("writers",2);
INSERT INTO groups(group_name,id) VALUES("admin",3);
INSERT INTO group_authorities(group_id,authority) VALUES(1,"ROLE_USER");
INSERT INTO group_authorities(group_id,authority) VALUES(2,"ROLE_WRITER");
INSERT INTO group_authorities(group_id,authority) VALUES(3,"ROLE_ADMIN");


-- Acl Required Tables

CREATE TABLE `acl_sid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_1` (`sid`,`principal`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

CREATE TABLE `acl_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_2` (`class`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE TABLE `acl_object_identity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) DEFAULT NULL,
  `owner_sid` bigint(20) DEFAULT NULL,
  `entries_inheriting` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_3` (`object_id_class`,`object_id_identity`),
  KEY `foreign_fk_1` (`parent_object`),
  KEY `foreign_fk_3` (`owner_sid`),
  CONSTRAINT `foreign_fk_1` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`),
  CONSTRAINT `foreign_fk_2` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
  CONSTRAINT `foreign_fk_3` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `acl_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_4` (`acl_object_identity`,`ace_order`),
  KEY `foreign_fk_5` (`sid`),
  CONSTRAINT `foreign_fk_4` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`),
  CONSTRAINT `foreign_fk_5` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;




