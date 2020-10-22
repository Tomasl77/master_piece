/* 
DDL script for teamsharing database
Script can be used on mysql database.

Last update : 2020-10-21

*/

/******************************
****** DATABASE CLEANING ******
******************************/

-- SCHEMA

CREATE SCHEMA IF NOT EXISTS teamsharingdev;
USE teamsharingdev;


-- TABLE
DROP TABLE IF EXISTS `subjects`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `user_profiles`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `user_credentials`;

/******************************
******** USER  TABLE ********
******************************/

CREATE TABLE `user_credentials` (
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
******* USERINFO  TABLE *******
******************************/

CREATE TABLE `user_profiles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `user_credentials_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_email` (`email`),
  CONSTRAINT `FK_userprofile_usercredentials` FOREIGN KEY (`user_credentials_id`) REFERENCES `user_credentials` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



/******************************
********* ROLE  TABLE *********
******************************/

CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `default_role` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


/******************************
****** USER_ROLE  TABLE******
******************************/

CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_role_user` (`role_id`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`user_id`) REFERENCES `user_credentials` (`id`),
  CONSTRAINT `FK_role_user` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



/******************************
******** SUBJECT TABLE ********
******************************/

CREATE TABLE `subjects` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(20) NOT NULL,
  `description` text,
  `title` varchar(30) NOT NULL,
  `total_vote` int NOT NULL,
  `requester_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_subject_topic` (`id`),
  KEY `FK_subject_user` (`requester_id`),
  CONSTRAINT `FK_subject_userprofile` FOREIGN KEY (`requester_id`) REFERENCES `user_profiles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/******************************
**** SHARING_SESSION TABLE ****
******************************/

CREATE TABLE `sharing_sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `subjects_id` bigint NOT NULL,
  `user_profiles_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_starttime` (`start_time`),
  CONSTRAINT `FK_sharingsession_subject` FOREIGN KEY (`subjects_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `FK_sharingsession_userprofile` FOREIGN KEY (`user_profiles_id`) REFERENCES `user_profiles` (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;