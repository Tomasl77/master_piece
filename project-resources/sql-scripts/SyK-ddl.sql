/* 
DDL script for teamsharing database
Script can be used on mysql database.

Last update : 2020-08-12

*/

/******************************
****** DATABASE CLEANING ******
******************************/

-- SCHEMA

DROP SCHEMA IF EXISTS teamsharingdev;
CREATE SCHEMA teamsharingdev;
USE teamsharingdev;


-- TABLE

DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `member_role`;
DROP TABLE IF EXISTS `subject`;


/******************************
******** MEMBER  TABLE ********
******************************/

CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_non_expired` varchar(1) NOT NULL,
  `account_non_locked` varchar(1) NOT NULL,
  `credentials_non_expired` varchar(1) NOT NULL,
  `enabled` varchar(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/******************************
********* ROLE  TABLE *********
******************************/

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `default_role` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


/******************************
****** MEMBER_ROLE  TABLE******
******************************/

CREATE TABLE `member_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_role_member` (`role_id`),
  CONSTRAINT `FK_member_role` FOREIGN KEY (`user_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_role_member` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/******************************
******** SUBJECT TABLE ********
******************************/

CREATE TABLE `subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(20) NOT NULL,
  `description` text,
  `title` varchar(30) NOT NULL,
  `total_vote` int NOT NULL,
  `requester_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_subject_topic` (`id`),
  KEY `FK_subject_user` (`requester_id`),
  CONSTRAINT `FK_subject_user` FOREIGN KEY (`requester_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

