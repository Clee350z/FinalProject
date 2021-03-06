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
-- Table `difficulty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `difficulty` ;

CREATE TABLE IF NOT EXISTS `difficulty` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail` ;

CREATE TABLE IF NOT EXISTS `trail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `location` VARCHAR(200) NOT NULL,
  `length_miles` DECIMAL(4,2) NOT NULL,
  `picture_url` VARCHAR(2000) NULL,
  `trail_open` TINYINT NULL,
  `latitude` DOUBLE NULL,
  `longitude` DOUBLE NULL,
  `difficulty_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trail_difficulty1_idx` (`difficulty_id` ASC),
  CONSTRAINT `fk_trail_difficulty1`
    FOREIGN KEY (`difficulty_id`)
    REFERENCES `difficulty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_address` ;

CREATE TABLE IF NOT EXISTS `user_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zipcode` VARCHAR(45) NULL,
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
  `profile_picture` VARCHAR(2000) NULL,
  `role` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `address_id` INT NULL,
  `biography` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `user_address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trail_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trail_comment` ;

CREATE TABLE IF NOT EXISTS `trail_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_body` TEXT NOT NULL,
  `time_posted` DATETIME NULL,
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
-- Table `group_hike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_hike` ;

CREATE TABLE IF NOT EXISTS `group_hike` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_name` VARCHAR(200) NOT NULL,
  `meetup_date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  `trail_id` INT NOT NULL,
  `meetup_time` TIME NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(1500) NULL,
  `hidden` TINYINT NOT NULL,
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


-- -----------------------------------------------------
-- Table `condition_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `condition_type` ;

CREATE TABLE IF NOT EXISTS `condition_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(150) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hike_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hike_report` ;

CREATE TABLE IF NOT EXISTS `hike_report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hike_title` VARCHAR(45) NULL,
  `trail_id` INT NOT NULL,
  `condition_type_id` INT NOT NULL,
  `report` TEXT NULL,
  `date_created` DATETIME NULL,
  `hiked_date` DATE NULL,
  `user_id` INT NOT NULL,
  `rating` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hike_report_trail1_idx` (`trail_id` ASC),
  INDEX `fk_hike_report_condition_type1_idx` (`condition_type_id` ASC),
  INDEX `fk_hike_report_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_hike_report_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hike_report_condition_type1`
    FOREIGN KEY (`condition_type_id`)
    REFERENCES `condition_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hike_report_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hike_photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hike_photo` ;

CREATE TABLE IF NOT EXISTS `hike_photo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hike_report_id` INT NOT NULL,
  `image_url` VARCHAR(1500) NULL,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hike_photo_hike_report1_idx` (`hike_report_id` ASC),
  CONSTRAINT `fk_hike_photo_hike_report1`
    FOREIGN KEY (`hike_report_id`)
    REFERENCES `hike_report` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_hike_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_hike_has_user` ;

CREATE TABLE IF NOT EXISTS `group_hike_has_user` (
  `group_hike_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`group_hike_id`, `user_id`),
  INDEX `fk_group_hike_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_group_hike_has_user_group_hike1_idx` (`group_hike_id` ASC),
  CONSTRAINT `fk_group_hike_has_user_group_hike1`
    FOREIGN KEY (`group_hike_id`)
    REFERENCES `group_hike` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_hike_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hike_report_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hike_report_comment` ;

CREATE TABLE IF NOT EXISTS `hike_report_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_box` TEXT NULL,
  `hike_report_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_hike_comments_hike_report1_idx` (`hike_report_id` ASC),
  INDEX `fk_group_hike_comments_user1_idx` (`user_id` ASC),
  INDEX `fk_group_hike_comments_group_hike_comments1_idx` (`reply_to_id` ASC),
  CONSTRAINT `fk_group_hike_comments_hike_report1`
    FOREIGN KEY (`hike_report_id`)
    REFERENCES `hike_report` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_hike_comments_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_hike_comments_group_hike_comments1`
    FOREIGN KEY (`reply_to_id`)
    REFERENCES `hike_report_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_trail` ;

CREATE TABLE IF NOT EXISTS `favorite_trail` (
  `user_id` INT NOT NULL,
  `trail_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `trail_id`),
  INDEX `fk_user_has_trail_trail1_idx` (`trail_id` ASC),
  INDEX `fk_user_has_trail_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_trail_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_trail_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `planned_hikes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `planned_hikes` ;

CREATE TABLE IF NOT EXISTS `planned_hikes` (
  `user_id` INT NOT NULL,
  `trail_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `trail_id`),
  INDEX `fk_user_has_trail_trail2_idx` (`trail_id` ASC),
  INDEX `fk_user_has_trail_user2_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_trail_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_trail_trail2`
    FOREIGN KEY (`trail_id`)
    REFERENCES `trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_hike_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_hike_comment` ;

CREATE TABLE IF NOT EXISTS `group_hike_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_box` TEXT NULL,
  `user_id` INT NOT NULL,
  `create_date` DATETIME NULL,
  `reply_to_id` INT NULL,
  `group_hike_id` INT NOT NULL,
  `hidden` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_hike_comments_user1_idx` (`user_id` ASC),
  INDEX `fk_group_hike_comments_group_hike_comments1_idx` (`reply_to_id` ASC),
  INDEX `fk_group_hike_comment_group_hike1_idx` (`group_hike_id` ASC),
  CONSTRAINT `fk_group_hike_comments_user10`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_hike_comments_group_hike_comments10`
    FOREIGN KEY (`reply_to_id`)
    REFERENCES `group_hike_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_hike_comment_group_hike1`
    FOREIGN KEY (`group_hike_id`)
    REFERENCES `group_hike` (`id`)
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
-- Data for table `difficulty`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (1, 'Easy', 'A hike that is generally suitable for anyone who enjoys walking. Mostly level or with a slight incline. Generally less than 3 miles.');
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (2, 'Moderate', 'A moderate hike is generally suitable for novice hikers who want a bit of a challenge. The terrain will involve a moderate incline and may have some steeper sections. Generally 3 to 5 miles.');
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (3, 'Moderately Strenuous', 'Moderately Strenuous hikes will generally be challenging for an unconditioned person. The terrain will involve a steady and often steep incline. Generally 5 to 8 miles.');
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (4, 'Strenuous', 'Strenuous hikes will challenge most hikers. The hike will generally be longer and steeper, but may be deemed \"Strenuous\" because of the elevation gain. Generally 7 to 10 miles.');
INSERT INTO `difficulty` (`id`, `name`, `description`) VALUES (5, 'Very Strenuous', 'Only well-conditioned and well-prepared hikers should attempt very strenuous hikes. The hike will generally be long and steep, and may include rock scrambling, stream crossings, and other challenging terrain. Generally 8 miles and over.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (1, 'Bear Peak Summit Hike', 'Colorado', 8.4, 'https://i.imgur.com/Poi8O4y.jpeg', 1, 38.191, -105.327, 4);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (2, 'Eldorado Canyon State Park Trails', 'Colorado', 7, 'https://i.imgur.com/ie48I7u.jpeg', 1, 39.929, -105.331, 4);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (3, 'Lost Lake via Hessie Trail', 'Colorado', 4.0, 'https://i.imgur.com/11CjyuT.jpeg', 1, 39.955, -105.608, 2);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (4, 'Iwakuni Castle Trail', 'Japan', 4.2, 'https://i.imgur.com/03E8yof.jpeg', 1, 34.103, 132.102, 2);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (5, 'Redwood Creek Trail', 'Caalifornia', 15.6, 'https://i0.wp.com/www.thehikinglife.com/the-hiking-life-2/wp-content/uploads/2021/09/PHOTO-2021-09-14-15-10-36.jpg?ssl=1', 1, 41.299, 124.033, 5);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (6, 'Taquari', 'Brazil', 20.5, 'https://i.imgur.com/LbpABdP.png', 1, 19.242, 57.225, 5);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (7, 'Angels Landing', 'Utah', 4.4, 'https://photos.alltrails.com/eyJidWNrZXQiOiJhc3NldHMuYWxsdHJhaWxzLmNvbSIsImtleSI6InVwbG9hZHMvcGhvdG8vaW1hZ2UvMTEwNzgxMDYvMWI5Mzk5YjNhMWU4NWI2MjNhNWNiMDhmNTk0MWM1MTcuanBnIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJqcGVnIiwicmVzaXplIjp7IndpZHRoIjoyMDQ4LCJoZWlnaHQiOjIwNDgsImZpdCI6Imluc2lkZSJ9LCJyb3RhdGUiOm51bGwsImpwZWciOnsidHJlbGxpc1F1YW50aXNhdGlvbiI6dHJ1ZSwib3ZlcnNob290RGVyaW5naW5nIjp0cnVlLCJvcHRpbWlzZVNjYW5zIjp0cnVlLCJxdWFudGlzYXRpb25UYWJsZSI6M319fQ==', 1, 37.259, -112.951, 2);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (8, 'Four Pass Loop', 'Colorado', 25.7, 'https://photos.alltrails.com/eyJidWNrZXQiOiJhc3NldHMuYWxsdHJhaWxzLmNvbSIsImtleSI6InVwbG9hZHMvcGhvdG8vaW1hZ2UvMjExOTQyODQvYmQyYWJiNDE2YTQ4Nzk1YTJkMmY1MmY0YzU1ZWZmZGQuanBnIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJqcGVnIiwicmVzaXplIjp7IndpZHRoIjoyMDQ4LCJoZWlnaHQiOjIwNDgsImZpdCI6Imluc2lkZSJ9LCJyb3RhdGUiOm51bGwsImpwZWciOnsidHJlbGxpc1F1YW50aXNhdGlvbiI6dHJ1ZSwib3ZlcnNob290RGVyaW5naW5nIjp0cnVlLCJvcHRpbWlzZVNjYW5zIjp0cnVlLCJxdWFudGlzYXRpb25UYWJsZSI6M319fQ==', 2, 39.564, -112.058, 5);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (9, 'Avalanche Lake via the Trail of the Cedars', 'Montana', 5.9, 'https://photos.alltrails.com/eyJidWNrZXQiOiJhc3NldHMuYWxsdHJhaWxzLmNvbSIsImtleSI6InVwbG9hZHMvcGhvdG8vaW1hZ2UvMjMxNTU3MDUvYTc3MzY5ZDFhMDI2NDJiOTA0Yjg4NGIyMmY5NzdmYjguanBnIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJqcGVnIiwicmVzaXplIjp7IndpZHRoIjoyMDQ4LCJoZWlnaHQiOjIwNDgsImZpdCI6Imluc2lkZSJ9LCJyb3RhdGUiOm51bGwsImpwZWciOnsidHJlbGxpc1F1YW50aXNhdGlvbiI6dHJ1ZSwib3ZlcnNob290RGVyaW5naW5nIjp0cnVlLCJvcHRpbWlzZVNjYW5zIjp0cnVlLCJxdWFudGlzYXRpb25UYWJsZSI6M319fQ==', 2, 48.679, -113.819, 3);
INSERT INTO `trail` (`id`, `name`, `location`, `length_miles`, `picture_url`, `trail_open`, `latitude`, `longitude`, `difficulty_id`) VALUES (10, 'Skyline Trail Loop', 'Washington', 6.2, 'https://photos.alltrails.com/eyJidWNrZXQiOiJhc3NldHMuYWxsdHJhaWxzLmNvbSIsImtleSI6InVwbG9hZHMvcGhvdG8vaW1hZ2UvMjg3MjgxOTkvMTY2ZDY5MTBhN2Q1MjIwMjk2NzFlY2EwODQ2MTJjZTYuanBnIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJqcGVnIiwicmVzaXplIjp7IndpZHRoIjoyMDQ4LCJoZWlnaHQiOjIwNDgsImZpdCI6Imluc2lkZSJ9LCJyb3RhdGUiOm51bGwsImpwZWciOnsidHJlbGxpc1F1YW50aXNhdGlvbiI6dHJ1ZSwib3ZlcnNob290RGVyaW5naW5nIjp0cnVlLCJvcHRpbWlzZVNjYW5zIjp0cnVlLCJxdWFudGlzYXRpb25UYWJsZSI6M319fQ==', 1, 46.786, -121.736, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_address`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `user_address` (`id`, `street`, `city`, `state`, `zipcode`) VALUES (1, 'International', 'Denver', 'Colorado', '29906');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `user` (`id`, `username`, `password`, `profile_picture`, `role`, `enabled`, `first_name`, `last_name`, `address_id`, `biography`) VALUES (1, 'admin', '$2a$10$KJh/cJ4YO.f8zwcjN7HLHOzRC/Rn/Xf3BJwGjdqLlVR7ZYNdoWV/y', NULL, 'ROLE_ADMIN', 1, 'Honeycomb', 'Hearts', 1, 'We are ze creators of Honey Trails!');
INSERT INTO `user` (`id`, `username`, `password`, `profile_picture`, `role`, `enabled`, `first_name`, `last_name`, `address_id`, `biography`) VALUES (2, 'burgerBob', '$2a$10$t0JMDym48MB5hZSMVfk74eMYxUOa/VTb2/ULVpZxUZOHiT0YNk4nm', NULL, 'ROLE_TEST', 1, 'Bob', 'Belcher', NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `profile_picture`, `role`, `enabled`, `first_name`, `last_name`, `address_id`, `biography`) VALUES (3, 'jenny12', '$2a$10$pim7BCTCtoZ1yOVFHuCSXerXhfjfYUOFQJjbpcoDEJAeslUHNg1qu', NULL, 'ROLE_TEST', 1, 'Jenny', 'Zimmersum', NULL, 'BOOM');
INSERT INTO `user` (`id`, `username`, `password`, `profile_picture`, `role`, `enabled`, `first_name`, `last_name`, `address_id`, `biography`) VALUES (4, 'unfairDictator', '$2a$10$t0JMDym48MB5hZSMVfk74eMYxUOa/VTb2/ULVpZxUZOHiT0YNk4nm', NULL, 'ROLE_TEST', 1, 'Tina', 'Belcher', NULL, 'Burn the World DOWN! One hike at a time...');

COMMIT;


-- -----------------------------------------------------
-- Data for table `trail_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `trail_comment` (`id`, `comment_body`, `time_posted`, `user_id`, `trail_id`) VALUES (1, 'The trail was so beautiful!', '2021-01-09 13:15:15', 2, 1);
INSERT INTO `trail_comment` (`id`, `comment_body`, `time_posted`, `user_id`, `trail_id`) VALUES (2, 'I love Colorado trails!', '2022-01-02 23:23:25', 3, 1);
INSERT INTO `trail_comment` (`id`, `comment_body`, `time_posted`, `user_id`, `trail_id`) VALUES (3, 'I hiked this trail when I visited Colorado in 2012!', '2022-01-15 15:19:24', 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_hike`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `group_hike` (`id`, `event_name`, `meetup_date`, `user_id`, `trail_id`, `meetup_time`, `description`, `image_url`, `hidden`) VALUES (1, 'Trail Fun Time', '2021-02-28', 2, 4, '15:04:04', 'Let\'s All Hike Together & Enjoy Nature While Being Healthy!!', 'https://files.slack.com/files-pri/T052X7BAZ-F02V6GUQ00N/image_from_ios.jpg', 0);
INSERT INTO `group_hike` (`id`, `event_name`, `meetup_date`, `user_id`, `trail_id`, `meetup_time`, `description`, `image_url`, `hidden`) VALUES (2, 'Hike Forever', '2022-01-01', 2, 3, '13:15:15', 'Hike this trail again', NULL, 0);
INSERT INTO `group_hike` (`id`, `event_name`, `meetup_date`, `user_id`, `trail_id`, `meetup_time`, `description`, `image_url`, `hidden`) VALUES (3, 'Tiffany\'s Birthday hike', '2022-01-05', 3, 2, '12:12:12', 'Hiking for Tiffany\'s 18th birthday!', NULL, 0);
INSERT INTO `group_hike` (`id`, `event_name`, `meetup_date`, `user_id`, `trail_id`, `meetup_time`, `description`, `image_url`, `hidden`) VALUES (4, 'Hiking For Cardio', '2022-03-05', 4, 5, '06:00:00', 'Let\'s get those heart rates up & pumping!', NULL, 0);
INSERT INTO `group_hike` (`id`, `event_name`, `meetup_date`, `user_id`, `trail_id`, `meetup_time`, `description`, `image_url`, `hidden`) VALUES (5, 'Snowy Hike', '2022-01-31', 3, 1, '10:15:00', 'Time to enjoy the snow while it last!', NULL, 0);
INSERT INTO `group_hike` (`id`, `event_name`, `meetup_date`, `user_id`, `trail_id`, `meetup_time`, `description`, `image_url`, `hidden`) VALUES (6, 'Hiking For Cancer', '2022-05-06', 4, 6, '11:00:00', 'Let\'s hike to raise money for cancer research', NULL, 0);
INSERT INTO `group_hike` (`id`, `event_name`, `meetup_date`, `user_id`, `trail_id`, `meetup_time`, `description`, `image_url`, `hidden`) VALUES (7, 'Jones\'s Anniversary', '2022-05-01', 2, 8, '12:15:00', 'Taking the wife out for a group hik with friends', NULL, 0);
INSERT INTO `group_hike` (`id`, `event_name`, `meetup_date`, `user_id`, `trail_id`, `meetup_time`, `description`, `image_url`, `hidden`) VALUES (8, 'Skill Distillery Team Building', '2022-02-04', 2, 2, '15:00:00', 'SD 31 graduates! Let\'s Celebrate with a Hike!!', NULL, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `condition_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `condition_type` (`id`, `name`, `description`) VALUES (1, 'dry', 'The path is dry with no adverse weather affects.');
INSERT INTO `condition_type` (`id`, `name`, `description`) VALUES (2, 'near dry', 'The path has recently been wet, but safe enough to hike.');
INSERT INTO `condition_type` (`id`, `name`, `description`) VALUES (3, 'muddy', 'The path has experienced heavy rainfall & will be more dangerous to traverse.');
INSERT INTO `condition_type` (`id`, `name`, `description`) VALUES (4, 'icy', 'The path has experienced freezing rainfall and will be slippery.');
INSERT INTO `condition_type` (`id`, `name`, `description`) VALUES (5, 'snow', 'The path has large amounts of snow bloacking path. Danger!');

COMMIT;


-- -----------------------------------------------------
-- Data for table `hike_report`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `hike_report` (`id`, `hike_title`, `trail_id`, `condition_type_id`, `report`, `date_created`, `hiked_date`, `user_id`, `rating`) VALUES (1, 'Slightly damp trail', 2, 2, 'The ground was slightly wet, a bit slippery. Exercise ccaution during hikes within the next 24 hours withstanding no more rain.', '2021-01-01', '2021-12-15', 2, 3);
INSERT INTO `hike_report` (`id`, `hike_title`, `trail_id`, `condition_type_id`, `report`, `date_created`, `hiked_date`, `user_id`, `rating`) VALUES (2, 'All clear', 2, 1, 'No issues', '2022-01-01', '2021-12-30', 2, 3);
INSERT INTO `hike_report` (`id`, `hike_title`, `trail_id`, `condition_type_id`, `report`, `date_created`, `hiked_date`, `user_id`, `rating`) VALUES (3, 'All clear', 3, 1, 'No issues', '2022-01-10', '2022-01-01', 2, 3);
INSERT INTO `hike_report` (`id`, `hike_title`, `trail_id`, `condition_type_id`, `report`, `date_created`, `hiked_date`, `user_id`, `rating`) VALUES (4, 'Snowy', 10, 4, 'Snowed in', '2021-12-15', '2021-12-10', 3, 5);
INSERT INTO `hike_report` (`id`, `hike_title`, `trail_id`, `condition_type_id`, `report`, `date_created`, `hiked_date`, `user_id`, `rating`) VALUES (5, 'Great Day for SD cohort 31', 1, 1, 'The Hike was Beautiful!', '2022-02-04', '2022-02-04', 4, 5);
INSERT INTO `hike_report` (`id`, `hike_title`, `trail_id`, `condition_type_id`, `report`, `date_created`, `hiked_date`, `user_id`, `rating`) VALUES (6, 'What was that?', 5, 2, 'That trail was soooo long!', '2022-02-09', '2022-01-31', 4, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hike_photo`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `hike_photo` (`id`, `hike_report_id`, `image_url`, `title`, `description`) VALUES (1, 1, 'https://i.imgur.com/LQN6YhG.jpeg', 'Small Waterfall', 'Small waterfall about 10 mins to the east of the trail.');
INSERT INTO `hike_photo` (`id`, `hike_report_id`, `image_url`, `title`, `description`) VALUES (2, 1, 'https://i.imgur.com/ncSYhS1.jpeg', 'This View', 'This view is crazy! Having the best day!');
INSERT INTO `hike_photo` (`id`, `hike_report_id`, `image_url`, `title`, `description`) VALUES (3, 4, 'https://photos.alltrails.com/eyJidWNrZXQiOiJhc3NldHMuYWxsdHJhaWxzLmNvbSIsImtleSI6InVwbG9hZHMvcGhvdG8vaW1hZ2UvNDQyMjA5NTEvZmQyMDcwYWUwN2Y4YWQ2OWE1NDMzNGYzYTJhOTBhMTEuanBnIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJqcGVnIiwicmVzaXplIjp7IndpZHRoIjoyMDQ4LCJoZWlnaHQiOjIwNDgsImZpdCI6Imluc2lkZSJ9LCJyb3RhdGUiOm51bGwsImpwZWciOnsidHJlbGxpc1F1YW50aXNhdGlvbiI6dHJ1ZSwib3ZlcnNob290RGVyaW5naW5nIjp0cnVlLCJvcHRpbWlzZVNjYW5zIjp0cnVlLCJxdWFudGlzYXRpb25UYWJsZSI6M319fQ==', 'Snowy!', 'This hike was so cold, but the view was amazing!');

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_hike_has_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `group_hike_has_user` (`group_hike_id`, `user_id`) VALUES (1, 1);
INSERT INTO `group_hike_has_user` (`group_hike_id`, `user_id`) VALUES (2, 1);
INSERT INTO `group_hike_has_user` (`group_hike_id`, `user_id`) VALUES (1, 2);
INSERT INTO `group_hike_has_user` (`group_hike_id`, `user_id`) VALUES (2, 2);
INSERT INTO `group_hike_has_user` (`group_hike_id`, `user_id`) VALUES (1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hike_report_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `hike_report_comment` (`id`, `comment_box`, `hike_report_id`, `user_id`, `create_date`, `reply_to_id`) VALUES (1, 'This was such a great group hike!', 1, 2, '2021-01-15 15:15:15', NULL);
INSERT INTO `hike_report_comment` (`id`, `comment_box`, `hike_report_id`, `user_id`, `create_date`, `reply_to_id`) VALUES (2, 'I love hiking', 2, 3, '2022-01-15 12:12:12', NULL);
INSERT INTO `hike_report_comment` (`id`, `comment_box`, `hike_report_id`, `user_id`, `create_date`, `reply_to_id`) VALUES (3, 'When can I go hiking again?!?!?', 2, 3, '2022-01-16 13:15:28', NULL);
INSERT INTO `hike_report_comment` (`id`, `comment_box`, `hike_report_id`, `user_id`, `create_date`, `reply_to_id`) VALUES (4, 'I\'m so tired', 3, 4, '2022-01-15 15:15:15', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_trail`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `favorite_trail` (`user_id`, `trail_id`) VALUES (2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `planned_hikes`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `planned_hikes` (`user_id`, `trail_id`) VALUES (2, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_hike_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `honeytrailsdb`;
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (1, 'This was a beautiful hike!', 2, '2021-01-01 15:15:15', NULL, 1, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (2, 'I love hiking', 3, '2022-01-10 13:13:13', NULL, 2, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (3, 'Hiking FOREVER!!', 2, '2021-01-15 15:15:15', NULL, 2, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (4, 'I missed hiking so much & for such a great cause!', 3, '2022-04-05 21:21:04', NULL, 4, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (5, 'What a great cause to take a stroll around the mountain! ', 4, '2022-05-07 16:15:15', NULL, 6, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (6, 'I love hiking so much!', 3, '2021-01-25 16:15:30', NULL, 1, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (7, 'Ugh. This was such a rough hike. :(', 4, '2021-01-01 16:30:15', NULL, 1, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (8, 'I can\'t wait to go again!', 2, '2021-01-01 16:45:45', NULL, 1, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (9, 'Why is hiking so fun?!?!', 2, '2022-01-11 16:15:45', NULL, 2, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (10, 'I can\'t believe that I survived that hike. It was so exhausting!', 3, '2022-01-12 22:15:45', NULL, 2, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (11, 'Hey, did anyone see Bob', 4, '2022-01-13 15:15:45', NULL, 2, 0);
INSERT INTO `group_hike_comment` (`id`, `comment_box`, `user_id`, `create_date`, `reply_to_id`, `group_hike_id`, `hidden`) VALUES (12, 'Where am I even at? I can barely think after that hike', 2, '2022-01-05 20:25:15', NULL, 3, 0);

COMMIT;

