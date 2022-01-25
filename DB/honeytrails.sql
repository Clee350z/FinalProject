-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema honeytrailsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `honeytrailsdb` ;

-- -----------------------------------------------------
-- Schema honeytrailsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `honeytrailsdb` DEFAULT CHARACTER SET utf8 ;
USE `honeytrailsdb` ;

-- -----------------------------------------------------
-- Table `trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail` ;

CREATE TABLE IF NOT EXISTS `trail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `location` VARCHAR(200) NOT NULL,
  `length` DECIMAL(4,2) NOT NULL,
  `pictures` VARCHAR(200) NULL,
  `rating` INT NULL,
  `difficulty` ENUM('easy', 'moderate', 'hard') NULL,
  `trail_status` TINYINT(2) NULL,
  `conditions` ENUM('muddy', 'icy', 'dry', 'flooded', 'mostly dry') NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(150) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `profile_picture` VARCHAR(300) NULL,
  `completed_hikes` VARCHAR(200) NULL,
  `favorite_trails` VARCHAR(200) NULL,
  `planned_hikes` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NOT NULL,
  `comment_body` TEXT(2000) NOT NULL,
  `photos` VARCHAR(2000) NULL,
  `time_posted` DATE NULL,
  `status` TINYINT(2) NOT NULL,
  `user_id` INT NOT NULL,
  `trail_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user_idx` (`user_id` ASC),
  INDEX `fk_comment_trail1_idx` (`trail_id` ASC),
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `list_users` VARCHAR(200) NOT NULL,
  `trail_name` VARCHAR(150) NOT NULL,
  `meetup_date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  `trail_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_user1_idx` (`user_id` ASC),
  INDEX `fk_event_trail1_idx` (`trail_id` ASC),
  CONSTRAINT `fk_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS honeytrails@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'honeytrails'@'localhost' IDENTIFIED BY 'honeytrails';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'honeytrails'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `user` (`id`, `username`, `password`, `profile_picture`, `completed_hikes`, `favorite_trails`, `planned_hikes`, `role`, `enabled`) VALUES (1, 'admin', '$2a$10$KJh/cJ4YO.f8zwcjN7HLHOzRC/Rn/Xf3BJwGjdqLlVR7ZYNdoWV/y', NULL, NULL, NULL, NULL, 'ROLE_ADMIN', 1);

COMMIT;

