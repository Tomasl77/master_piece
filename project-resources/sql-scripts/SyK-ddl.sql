/* 
DDL script for teamsharing database
Script can be used on mysql database.

Last update : 2020-12-19

*/

/******************************
****** DATABASE CLEANING ******
******************************/

CREATE SCHEMA IF NOT EXISTS `teamsharingdev` ;
USE `teamsharingdev` ;

/*************************
********* USERS **********
**************************/

CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `account_non_expired` VARCHAR(1) NOT NULL,
  `account_non_locked` VARCHAR(1) NOT NULL,
  `credentials_non_expired` VARCHAR(1) NOT NULL,
  `enabled` VARCHAR(1) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_username` (`username`),
  UNIQUE INDEX `UK_email` (`email`)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


/*************************
****** CATEGORIES ********
**************************/
CREATE TABLE IF NOT EXISTS `categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_name` (`name` ))
ENGINE = InnoDB;


/*************************
******* SUBJECTS *********
**************************/
CREATE TABLE IF NOT EXISTS `subjects` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `title` VARCHAR(30) NOT NULL,
  `request_date` DATETIME NOT NULL,
  `schedule` VARCHAR(1) NOT NULL,
  `requester_id` BIGINT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_subject_user` (`requester_id` ),
  INDEX `FK_subject_category` (`category_id` ),
  UNIQUE INDEX `UK_requestdate_requesterid` (`request_date` , `requester_id` ),
  CONSTRAINT `FK_subject_user`
    FOREIGN KEY (`requester_id`)
    REFERENCES `users` (`id`),
  CONSTRAINT `FK_subject_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`)
    )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


/*************************
**** SHARING SESSIONS ****
**************************/
CREATE TABLE IF NOT EXISTS `sharing_sessions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `subject_id` BIGINT NOT NULL,
  `lecturer_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_sharingsession_subject` (`subject_id` ),
  INDEX `FK_sharingsession_user` (`lecturer_id` ),
  UNIQUE INDEX `UK_starttime` (`start_time` ),
  CONSTRAINT `FK_sharingsession_subject`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subjects` (`id`),
  CONSTRAINT `FK_sharingsession_user`
    FOREIGN KEY (`lecturer_id`)
    REFERENCES `users` (`id`)
    )
ENGINE = InnoDB;


/*************************
********* ROLES **********
**************************/
CREATE TABLE IF NOT EXISTS `roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NOT NULL,
  `default_role` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_code` (`code` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- Join table
/*************************
******* USER_ROLE ********
**************************/
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FK_role_user` (`role_id` ),
  INDEX `FK_user_role` (`user_id` ),
  CONSTRAINT `FK_role_user`
    FOREIGN KEY (`role_id`)
    REFERENCES `roles` (`id`),
  CONSTRAINT `FK_user_role`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- join table
/******************************
****** USER_VOTE_SUBJECT ******
******************************/
CREATE TABLE IF NOT EXISTS `user_vote_subject` (
  `subject_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`subject_id`, `user_id`),
  INDEX `FK_user_has_voted` (`user_id`),
  INDEX `FK_subject_has_votes` (`subject_id`),
  CONSTRAINT `FK_subject_has_votes`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subjects` (`id`),
  CONSTRAINT `FK_user_has_voted`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
