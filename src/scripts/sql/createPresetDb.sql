-- MySQL dump 10.13  Distrib 5.5.52, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: NEWSSITEDATA
-- ------------------------------------------------------
-- Server version	5.5.52-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acl_class`
--

DROP TABLE IF EXISTS `acl_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_2` (`class`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_class`
--

LOCK TABLES `acl_class` WRITE;
/*!40000 ALTER TABLE `acl_class` DISABLE KEYS */;
INSERT INTO `acl_class` VALUES (7,'com.newssite.model.Article'),(6,'com.newssite.model.User');
/*!40000 ALTER TABLE `acl_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_entry`
--

DROP TABLE IF EXISTS `acl_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_entry`
--

LOCK TABLES `acl_entry` WRITE;
/*!40000 ALTER TABLE `acl_entry` DISABLE KEYS */;
INSERT INTO `acl_entry` VALUES (1,1,0,8,2,1,0,0),(3,3,0,10,2,1,0,0),(4,4,0,11,2,1,0,0),(5,5,0,12,2,1,0,0),(7,6,0,13,2,1,0,0),(8,7,0,9,2,1,0,0),(9,8,0,9,2,1,0,0),(10,9,0,9,2,1,0,0),(11,10,0,9,2,1,0,0),(12,11,0,9,2,1,0,0),(13,12,0,9,2,1,0,0),(14,13,0,9,2,1,0,0),(15,14,0,9,2,1,0,0),(16,15,0,9,2,1,0,0),(17,16,0,9,2,1,0,0),(18,17,0,9,2,1,0,0),(19,18,0,9,2,1,0,0),(22,21,0,14,2,1,0,0),(23,22,0,9,2,1,0,0),(24,23,0,15,2,1,0,0),(25,24,0,16,2,1,0,0);
/*!40000 ALTER TABLE `acl_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_object_identity`
--

DROP TABLE IF EXISTS `acl_object_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_object_identity`
--

LOCK TABLES `acl_object_identity` WRITE;
/*!40000 ALTER TABLE `acl_object_identity` DISABLE KEYS */;
INSERT INTO `acl_object_identity` VALUES (1,6,18,NULL,8,1),(3,6,11,NULL,10,0),(4,6,14,NULL,11,0),(5,6,13,NULL,12,0),(6,6,12,NULL,13,0),(7,7,1,NULL,9,0),(8,7,2,NULL,9,0),(9,7,3,NULL,9,0),(10,7,4,NULL,9,0),(11,7,5,NULL,9,0),(12,7,6,NULL,9,0),(13,7,7,NULL,9,0),(14,7,8,NULL,9,0),(15,7,9,NULL,9,0),(16,7,10,NULL,9,0),(17,7,12,NULL,9,0),(18,7,15,NULL,9,0),(21,6,20,NULL,14,1),(22,6,10,NULL,9,0),(23,6,22,NULL,15,1),(24,6,23,NULL,16,1);
/*!40000 ALTER TABLE `acl_object_identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_sid`
--

DROP TABLE IF EXISTS `acl_sid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_sid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_1` (`sid`,`principal`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_sid`
--

LOCK TABLES `acl_sid` WRITE;
/*!40000 ALTER TABLE `acl_sid` DISABLE KEYS */;
INSERT INTO `acl_sid` VALUES (10,1,'admin'),(7,1,'anonymousUser'),(14,1,'newuser'),(16,1,'newuser9001'),(11,1,'testuser2'),(12,1,'user51'),(9,1,'username2'),(15,1,'username23'),(13,1,'username4'),(8,1,'username9001');
/*!40000 ALTER TABLE `acl_sid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `headline` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `caption` varchar(250) NOT NULL,
  `imagePath` varchar(250) DEFAULT NULL,
  `lastEdited` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hits` bigint(20) DEFAULT '0',
  PRIMARY KEY (`article_id`),
  UNIQUE KEY `headline` (`headline`),
  KEY `author_id_fk` (`author_id`),
  CONSTRAINT `author_id_fk` FOREIGN KEY (`author_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (1,'Aliquam varius','international',10,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc tempus mollis ante sit amet vehicula. Nam suscipit lectus eget consectetur facilisis. Duis consectetur fermentum nisl, facilisis blandit nunc pellentesque ac.','mnW1wgW.jpg','2016-09-30 09:40:18',197),(2,'Lorem ipsum dolor sit','politics',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"','p1.jpg','2016-09-30 09:33:41',20),(3,'Ipsum Lorem','politics',10,'Nullam elit tellus, laoreet nec accumsan sit amet, semper in est. Donec interdum eget odio sed mattis.','p2.jpg','2016-09-30 09:39:24',56),(4,'Nam faucibus urna','politics',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"','p3.jpg','2016-09-30 09:35:03',24),(5,'Proin id scelerisque ','politics',10,'Curabitur ultricies, magna non dictum egestas, enim turpis consequat risus, nec imperdiet quam eros et nisi. Sed at suscipit justo. Quisque mollis sed lorem a porttitor. Nam dignissim euismod malesuada.','p4.jpg','2016-09-30 09:36:05',27),(6,'Nullam elit tellus','sports',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','s1.jpg','2016-09-30 10:13:58',995),(7,'Morbi ultrices','sports',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','s2.jpg','2016-09-30 10:15:46',173),(8,'Integer mattis luctus','sports',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','s3.jpg','2016-09-30 10:16:18',43),(9,'Proin id sceler','international',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"','i2.jpg','2016-09-30 09:41:04',23),(10,'Lorem ipsum','international',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"','i3.jpg','2016-09-30 09:42:34',71),(12,'Fusce risus','entertainment',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','e2.jpg','2016-09-30 10:17:13',136),(15,'Cras cursus ex','entertainment',10,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','e3.jpg','2016-09-30 10:16:54',17);
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`username`,`authority`),
  CONSTRAINT `username_fk_auth` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chats` (
  `chat_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats`
--

LOCK TABLES `chats` WRITE;
/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chats_join_table`
--

DROP TABLE IF EXISTS `chats_join_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chats_join_table` (
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `chat_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`chat_id`),
  KEY `chat_id_join_fk` (`chat_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `chat_id_join_fk` FOREIGN KEY (`chat_id`) REFERENCES `chats` (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats_join_table`
--

LOCK TABLES `chats_join_table` WRITE;
/*!40000 ALTER TABLE `chats_join_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `chats_join_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(150) NOT NULL,
  `posted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `votes` bigint(20) NOT NULL DEFAULT '0',
  `poster_id` bigint(20) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `poster_id_fk` (`poster_id`),
  KEY `article_id_fk` (`article_id`),
  CONSTRAINT `poster_id_fk` FOREIGN KEY (`poster_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `article_id_fk` FOREIGN KEY (`article_id`) REFERENCES `articles` (`article_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'Sed vulputate purus ac tempus feugiat. Morbi malesuada ante risus, ut placerat velit convallis vitae. ','2016-09-30 10:32:52',1,20,3),(2,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 10:34:11',-1,10,3),(3,'\"username2 said: Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna inter','2016-09-30 10:34:56',1,20,3),(4,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 10:37:52',0,20,5),(5,'Maecenas iaculis odio non elit posuere vulputate. Phasellus ac enim sit amet lectus elementum vehicula ut vitae lacus.','2016-09-30 10:47:13',1,20,7),(6,'Aenean sed sapien in mauris malesuada','2016-09-30 10:48:18',0,20,6),(8,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et fini','2016-09-30 11:04:31',0,20,10),(9,'Maecenas eget vestibulum dolor. Nullam in mollis nulla, at pellentesque orci. Quisque id eros efficitur, rhoncus sapien quis, placerat sem.','2016-09-30 11:31:05',1,12,12),(10,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:16',6,12,1),(11,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:24',5,12,4),(12,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:33',5,12,15),(13,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:42',5,10,15),(15,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:55',5,10,9),(17,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:22:18',5,20,8),(18,'Aliquam molestie porta velit, in consequat ipsum imperdiet sed.','2016-09-30 13:25:21',1,11,2),(19,'In scelerisque tellus eu auctor luctus. Donec sit amet dui non tellus convallis consequat. In commodo neque id ligula pretium vulputate. Nam sit amet ','2016-09-30 13:26:22',1,12,2),(20,'Proin ac ante quis lectus luctus tincidunt. In rutrum purus sed tincidunt aliquet. Etiam feugiat condimentum libero non ultrices. Vivamus sollicitudin','2016-09-30 13:28:10',-1,22,1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_authorities`
--

DROP TABLE IF EXISTS `group_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_authorities` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(50) NOT NULL,
  KEY `group_id_fk` (`group_id`),
  CONSTRAINT `group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_authorities`
--

LOCK TABLES `group_authorities` WRITE;
/*!40000 ALTER TABLE `group_authorities` DISABLE KEYS */;
INSERT INTO `group_authorities` VALUES (1,'ROLE_USER'),(2,'ROLE_WRITER'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `group_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_members` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usernamee_fk` (`username`),
  KEY `groupidi_fk` (`group_id`),
  CONSTRAINT `usernamee_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `groupidi_fk` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
INSERT INTO `group_members` VALUES (1,'admin',3),(2,'username2',2),(4,'username3',1),(6,'username4',1),(7,'username1',1);
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'users'),(2,'writers'),(3,'admins');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(150) NOT NULL,
  `posted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `poster_id` bigint(20) NOT NULL,
  `chat_id` bigint(20) NOT NULL,
  `read` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `poster_id_msg_fk` (`poster_id`),
  KEY `chat_id_fk` (`chat_id`),
  CONSTRAINT `poster_id_msg_fk` FOREIGN KEY (`poster_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `chat_id_fk` FOREIGN KEY (`chat_id`) REFERENCES `chats` (`chat_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paragraphs`
--

DROP TABLE IF EXISTS `paragraphs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paragraphs` (
  `paragraph_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(1500) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `article_order` int(11) NOT NULL,
  PRIMARY KEY (`paragraph_id`),
  KEY `article_id_fk_p` (`article_id`),
  CONSTRAINT `article_id_fk_p` FOREIGN KEY (`article_id`) REFERENCES `articles` (`article_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paragraphs`
--

LOCK TABLES `paragraphs` WRITE;
/*!40000 ALTER TABLE `paragraphs` DISABLE KEYS */;
INSERT INTO `paragraphs` VALUES (1,'Donec aliquam aliquam justo, quis rhoncus purus ultricies nec. Vivamus ut laoreet tortor. In lacinia placerat leo et accumsan. Mauris venenatis cursus tempus. Integer \r\ntempus mollis ante sit amet vehicula. Nam suscipit lectus eget consectetur facilisis. Duis consectetur fermentum nisl, facilisis blandit nunc pellentesque ac. leo lacus, rutrum luctus enim a, auctor mollis quam. Etiam porttitor erat sed tellus mattis eleifend a et velit. In vel diam purus. Phasellus ornare tincidunt metus dictum aliquet. Pellentesque pellentesque massa non nisl commodo, sit amet hendrerit felis sagittis. Proin lobortis nunc sapien, vel commodo augue placerat sit amet. Etiam finibus ipsum elit, id placerat est tristique quis. Vivamus accumsan quam vel purus vehicula, sit amet convallis elit convallis.',1,0),(2,'Quisque id mi a ligula gravida pharetra vitae ut nisi. Etiam pharetra ligula cursus dui elementum pretium. Suspendisse tempus arcu at ante dictum, id aliquet erat euismod. Proin ut pharetra magna. Proin rhoncus odio a mi sodales, nec sodales massa laoreet. Vivamus sodales diam vel elementum egestas. Sed a magna sed dolor faucibus varius et nec mauris. Nulla bibendum felis sapien, quis sodales tellus accumsan ut. Suspendisse potenti. Pellentesque maximus elit turpis, viverra egestas magna fermentum a. Integer vehicula tincidunt congue. Aenean consequat tincidunt pellentesque.',1,1),(3,'Nam ut blandit quam. Nullam felis mi, blandit nec interdum non, scelerisque eget neque. Vestibulum id porttitor leo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nunc a elit nec lectus accumsan tincidunt sodales vel augue. Pellentesque consequat facilisis nisl et interdum. Duis hendrerit nisi eu justo aliquam blandit. Ut dolor massa, rhoncus sit amet pulvinar quis, hendrerit nec nisi. Pellentesque sodales porta malesuada. In hac habitasse platea dictumst. Duis sodales sodales condimentum. Donec odio nibh, vulputate sit amet semper ac, interdum in nisi. Nulla facilisi. Integer at dolor in enim iaculis lacinia vel vel neque. Curabitur sodales, massa in viverra rutrum, nulla dui efficitur mauris, vel aliquam justo urna aliquet est. Morbi cursus, mi eget posuere placerat, tortor diam iaculis augue, et facilisis odio est tempus purus.',1,2),(4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc tempus mollis ante sit amet vehicula. Nam suscipit lectus eget consectetur facilisis. Duis consectetur fermentum nisl, facilisis blandit nunc pellentesque ac. Mauris est lacus, bibendum ac sapien sit amet, mollis luctus nisi. Curabitur sed elementum ligula. Ut vel nunc nisl. Nunc quis pulvinar felis, eget volutpat nisi. Fusce pellentesque ligula nec convallis semper. Vestibulum quis rutrum risus. Aenean nulla urna, ultrices eget pretium sit amet, commodo aliquet nunc. Suspendisse ac mauris quis massa cursus fermentum non sed arcu. Sed convallis nulla sed vestibulum eleifend. Sed malesuada risus sed porta sagittis. Nam nec ligula lacinia, fringilla nisl ac, consequat velit.',1,3),(5,'Cras volutpat mauris et nulla tempus, ac suscipit purus ultricies. In quis tristique turpis, ut tempor erat. Fusce nec turpis eu odio molestie consectetur. In hac habitasse platea dictumst. Mauris odio lacus, porttitor non sapien sit amet, efficitur pellentesque elit. Curabitur gravida dictum nibh nec ullamcorper. Proin gravida et leo vitae rhoncus.',1,4),(6,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ligula mauris, molestie nec lectus non, ultricies mollis arcu. Ut sit amet justo et tellus placerat fermentum. Quisque dictum sapien eget odio aliquam, sed gravida ipsum pellentesque. Pellentesque sit amet porta turpis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi nec ligula dignissim, pharetra odio vel, rhoncus nisi. Sed ultrices porta tempor.',2,0),(7,'Aenean dictum, neque a cursus accumsan, urna risus molestie augue, eu tincidunt est lectus et ex. Suspendisse sagittis justo eros, non viverra magna dictum vitae. Donec scelerisque est et ipsum scelerisque tristique. Ut quis lectus aliquam, porta est at, tristique augue. Phasellus eu nisi elit. Mauris dapibus, erat aliquet rutrum malesuada, nibh ligula condimentum nulla, ac euismod dolor turpis at risus. Integer vulputate orci quis nunc sagittis, eu feugiat ante porttitor. Aenean nec efficitur neque, non aliquet risus.',2,1),(8,'Pellentesque risus nunc, blandit ac neque et, fermentum venenatis lacus. Ut ac mauris posuere, consectetur ipsum eu, varius erat. Nunc in arcu risus. Fusce rhoncus enim ut lobortis mattis. Aliquam molestie porta velit, in consequat ipsum imperdiet sed. Fusce pellentesque tempus ultricies. Mauris at arcu sit amet eros molestie lobortis. Quisque commodo scelerisque erat, vel tristique tellus placerat a',2,2),(9,'Nam vitae ornare tellus. Curabitur sagittis ornare eros, eleifend cursus felis rutrum id. Suspendisse tempus ex id quam laoreet tincidunt. In tincidunt magna tortor, in varius dui aliquam eget. Vestibulum rutrum facilisis fringilla. Nullam a fermentum nulla. Integer erat odio, scelerisque eget sapien at, accumsan venenatis justo.',2,3),(10,'Praesent elementum ullamcorper urna, in malesuada ante facilisis vel. Praesent vel semper arcu. Donec accumsan mollis faucibus. In at sodales orci. Etiam nec augue in sapien pulvinar sollicitudin. Curabitur vehicula lobortis est aliquet ultrices. Sed dignissim turpis risus, ut lobortis ante gravida eget. Sed et lacus sit amet justo rutrum porta non in velit. Curabitur dictum metus sed interdum porttitor. Aliquam placerat libero eu augue lacinia dapibus. Fusce posuere elit urna. Integer sit amet neque quis nulla eleifend elementum id fermentum nisi. Nulla sed leo vitae metus porttitor suscipit pretium eu augue. Pellentesque auctor mi ut luctus mattis.',2,4),(11,'Vivamus tellus nulla, porta vel eleifend at, interdum sit amet arcu. Cras aliquet mollis tortor. Suspendisse a purus at nulla placerat finibus vel dictum dolor. Phasellus posuere lorem et accumsan laoreet. Curabitur malesuada, libero non rhoncus eleifend, turpis ligula mattis ligula, ut feugiat lacus massa vitae neque. Aliquam ut commodo velit, id pharetra lorem. Maecenas vitae blandit massa. Suspendisse at consequat felis, ac ornare diam. Cras at purus tincidunt, sollicitudin justo eu, tempor eros. Vestibulum feugiat egestas elit, non placerat libero aliquet eu. In hac habitasse platea dictumst. Phasellus in vehicula risus. Aliquam dictum mattis magna, nec accumsan leo auctor quis. Donec et fringilla nunc. Mauris ac arcu tincidunt, auctor ex at, tincidunt libero.',3,0),(12,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et finibus augue. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed suscipit facilisis iaculis. Praesent lobortis ipsum id leo lobortis, vitae ornare tellus euismod. Phasellus quis sapien id tortor tincidunt ullamcorper in at massa. Integer auctor neque vel venenatis posuere. Pellentesque pulvinar blandit vehicula. Sed eu diam quam. Suspendisse tincidunt nunc non lacinia suscipit. Morbi tempor eu dui vel sagittis.',3,1),(13,'Vivamus sollicitudin scelerisque orci, sed sollicitudin nisi lacinia placerat. Nulla molestie libero at bibendum viverra. Duis euismod mi eu nibh pharetra rutrum. Sed condimentum euismod libero, id rhoncus nisi ullamcorper et. Nulla in ultricies erat, quis sollicitudin odio. Nam convallis eros vitae tortor pharetra, vitae faucibus ligula eleifend. Morbi accumsan laoreet mollis. In congue ligula nec suscipit malesuada. Ut bibendum maximus blandit. Curabitur in nibh urna. Maecenas tempor egestas porta.',3,2),(14,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',3,3),(15,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',3,4),(16,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',4,0),(17,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',4,1),(18,'Vivamus sollicitudin scelerisque orci, sed sollicitudin nisi lacinia placerat. Nulla molestie libero at bibendum viverra. Duis euismod mi eu nibh pharetra rutrum. Sed condimentum euismod libero, id rhoncus nisi ullamcorper et. Nulla in ultricies erat, quis sollicitudin odio. Nam convallis eros vitae tortor pharetra, vitae faucibus ligula eleifend. Morbi accumsan laoreet mollis. In congue ligula nec suscipit malesuada. Ut bibendum maximus blandit. Curabitur in nibh urna. Maecenas tempor egestas porta.',4,2),(19,'orem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',5,0),(20,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',5,1),(21,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',6,0),(22,'Vivamus sollicitudin scelerisque orci, sed sollicitudin nisi  placerat. Nulla molestie libero at bibendum viverra. Duis euismod mi eu nibh pharetra rutrum. Sed condimentum euismod libero, id rhoncus nisi ullamcorper et. Nulla in ultricies erat, quis sollicitudin odio. Nam convallis eros vitae tortor pharetra, vitae faucibus ligula eleifend. Morbi accumsan laoreet mollis. In congue ligula nec suscipit malesuada. Ut bibendum maximus blandit. Curabitur in nibh urna. Maecenas tempor egestas porta.',6,1),(23,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et finibus augue. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed suscipit facilisis iaculis. Praesent lobortis ipsum id leo lobortis, vitae ornare tellus euismod. Phasellus quis sapien id tortor tincidunt ullamcorper in at massa. Integer auctor neque vel venenatis posuere. Pellentesque pulvinar blandit vehicula. Sed eu diam quam. Suspendisse tincidunt nunc non lacinia suscipit. Morbi tempor eu dui vel sagittis.',6,2),(24,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',7,0),(25,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',7,1),(26,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',8,0),(27,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et finibus augue. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed suscipit facilisis iaculis. Praesent lobortis ipsum id leo lobortis, vitae ornare tellus euismod. Phasellus quis sapien id tortor tincidunt ullamcorper in at massa. Integer auctor neque vel venenatis posuere. Pellentesque pulvinar blandit vehicula. Sed eu diam quam. Suspendisse tincidunt nunc non lacinia suscipit. Morbi tempor eu dui vel sagittis.',8,1),(28,'Vivamus tellus nulla, porta vel eleifend at, interdum sit amet arcu. Cras aliquet mollis tortor. Suspendisse a purus at nulla placerat finibus vel dictum dolor. Phasellus posuere lorem et accumsan laoreet. Curabitur malesuada, libero non rhoncus eleifend, turpis ligula mattis ligula, ut feugiat lacus massa vitae neque. Aliquam ut commodo velit, id pharetra lorem. Maecenas vitae blandit massa. Suspendisse at consequat felis, ac ornare diam. Cras at purus tincidunt, sollicitudin justo eu, tempor eros. Vestibulum feugiat egestas elit, non placerat libero aliquet eu. In hac habitasse platea dictumst. Phasellus in vehicula risus. Aliquam dictum mattis magna, nec accumsan leo auctor quis. Donec et fringilla nunc. Mauris ac arcu tincidunt, auctor ex at, tincidunt libero.',8,2),(29,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',9,0),(30,'Vivamus tellus nulla, porta vel eleifend at, interdum sit amet arcu. Cras aliquet mollis tortor. Suspendisse a purus at nulla placerat finibus vel dictum dolor. Phasellus posuere lorem et accumsan laoreet. Curabitur malesuada, libero non rhoncus eleifend, turpis ligula mattis ligula, ut feugiat lacus massa vitae neque. Aliquam ut commodo velit, id pharetra lorem. Maecenas vitae blandit massa. Suspendisse at consequat felis, ac ornare diam. Cras at purus tincidunt, sollicitudin justo eu, tempor eros. Vestibulum feugiat egestas elit, non placerat libero aliquet eu. In hac habitasse platea dictumst. Phasellus in vehicula risus. Aliquam dictum mattis magna, nec accumsan leo auctor quis. Donec et fringilla nunc. Mauris ac arcu tincidunt, auctor ex at, tincidunt libero.',9,1),(31,'Vivamus tellus nulla, porta vel eleifend at, interdum sit amet arcu. Cras aliquet mollis tortor. Suspendisse a purus at nulla placerat finibus vel dictum dolor. Phasellus posuere lorem et accumsan laoreet. Curabitur malesuada, libero non rhoncus eleifend, turpis ligula mattis ligula, ut feugiat lacus massa vitae neque. Aliquam ut commodo velit, id pharetra lorem. Maecenas vitae blandit massa. Suspendisse at consequat felis, ac ornare diam. Cras at purus tincidunt, sollicitudin justo eu, tempor eros. Vestibulum feugiat egestas elit, non placerat libero aliquet eu. In hac habitasse platea dictumst. Phasellus in vehicula risus. Aliquam dictum mattis magna, nec accumsan leo auctor quis. Donec et fringilla nunc. Mauris ac arcu tincidunt, auctor ex at, tincidunt libero.',10,0),(32,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',10,1),(33,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',10,2),(37,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',12,0),(38,'Vivamus sollicitudin scelerisque orci, sed sollicitudin nisi lacinia placerat. Nulla molestie libero at bibendum viverra. Duis euismod mi eu nibh pharetra rutrum. Sed condimentum euismod libero, id rhoncus nisi ullamcorper et. Nulla in ultricies erat, quis sollicitudin odio. Nam convallis eros vitae tortor pharetra, vitae faucibus ligula eleifend. Morbi accumsan laoreet mollis. In congue ligula nec suscipit malesuada. Ut bibendum maximus blandit. Curabitur in nibh urna. Maecenas tempor egestas porta.',12,1),(39,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et finibus augue. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed suscipit facilisis iaculis. Praesent lobortis ipsum id leo lobortis, vitae ornare tellus euismod. Phasellus quis sapien id tortor tincidunt ullamcorper in at massa. Integer auctor neque vel venenatis posuere. Pellentesque pulvinar blandit vehicula. Sed eu diam quam. Suspendisse tincidunt nunc non lacinia suscipit. Morbi tempor eu dui vel sagittis.',12,2),(42,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis nibh lacus, placerat a tempus eu, feugiat eu massa. Duis fermentum urna eu augue ornare, nec tincidunt felis porta. Vestibulum tellus dolor, semper sit amet luctus sollicitudin, volutpat sed felis. Quisque ornare massa quis ex tristique, eleifend iaculis leo feugiat. Nulla eu facilisis quam. Maecenas mattis tempus urna id tristique. In sed diam vehicula, commodo odio eget, sodales purus. Fusce porttitor dui sit amet nulla euismod fermentum. Integer porta mi quis urna rutrum placerat. Maecenas venenatis ex ex, eu tempor nisl tempor vitae. Nullam tortor metus, bibendum a lacinia vel, sollicitudin ac nisl. Duis nunc sapien, dignissim et quam vel, iaculis elementum libero. Donec et dui nibh',15,0),(43,'Donec volutpat augue a lobortis auctor. Vestibulum mattis aliquam elit et pretium. Integer auctor laoreet magna, quis semper turpis gravida ac. Proin malesuada ullamcorper tincidunt. Curabitur sollicitudin mattis sapien et vulputate. Phasellus a ullamcorper ligula. Proin dapibus ex id ex tristique malesuada. Sed egestas, lorem imperdiet suscipit rhoncus, arcu ligula consequat lacus, sed malesuada neque nibh vitae nibh. Morbi libero metus, efficitur ut velit congue, gravida iaculis odio. Nunc pellentesque lobortis dolor, at elementum dolor condimentum eget. Vestibulum non erat vel velit elementum tristique. Nulla accumsan magna libero. Integer id scelerisque ipsum. Integer gravida, augue eu dignissim tristique, felis ante venenatis orci, ornare fermentum eros nisl sit amet risus. Praesent maximus rhoncus urna ultrices pellentesque.',15,1);
/*!40000 ALTER TABLE `paragraphs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `task_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `description` varchar(500) NOT NULL,
  `message` varchar(500) DEFAULT NULL,
  `assigner` varchar(50) NOT NULL,
  `assignee` varchar(50) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `completed` timestamp NULL DEFAULT NULL,
  `complete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`task_id`),
  KEY `assigner_fk` (`assigner`),
  KEY `assignee_fk` (`assignee`),
  CONSTRAINT `assignee_fk` FOREIGN KEY (`assignee`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assigner_fk` FOREIGN KEY (`assigner`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(125) NOT NULL,
  `email` varchar(50) NOT NULL,
  `details` varchar(250) DEFAULT NULL,
  `imagePath` varchar(250) DEFAULT NULL,
  `createdOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (10,'username2','$2a$12$BwNFR6MMYhqSHe7SGXPDbucG/cGVjj/aXxsQFhxdeLr0Y1PUuzx4y','email@bgl.com','Fusce non accumsan massa. Ut molestie elementum dapibus. Nulla facilisi.','helmet1.jpg','2016-09-30 11:22:10',1),(11,'admin','$2a$12$L7UFGbRZAqCTiWaVolD9letZV2/Dv4G2QrgnlxGK7jwpixI7caefu','admin@blackhole.org','Admin user','helmet1.jpg','2016-09-30 11:22:10',1),(12,'username4','$2a$12$dW.yToJV/oMZbNfYtTi8NeNMW0F.cGNen7eWucJgXnGefhJwKxNBW','email@amil.com','Maecenas eget vestibulum dolor. Nullam in mollis nulla, at pellentesque orci. Quisque id eros efficitur, rhoncus sapien quis, placerat sem.','helmet1.jpg','2016-09-30 11:22:10',1),(20,'username1','$2a$12$BwNFR6MMYhqSHe7SGXPDbucG/cGVjj/aXxsQFhxdeLr0Y1PUuzx4y','email@emailunique.com','Sed vulputate purus ac tempus feugiat. Morbi malesuada ante risus, ut placerat velit convallis vitae. Ut aliquam tortor in tortor feugiat, vel congue massa pretium. ','helmet1.jpg','2016-09-30 11:22:10',1),(22,'username3','$2a$12$BwNFR6MMYhqSHe7SGXPDbucG/cGVjj/aXxsQFhxdeLr0Y1PUuzx4y','email@invalida.com','Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;','helmet1.jpg','2016-09-30 11:22:10',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-30 16:40:15
