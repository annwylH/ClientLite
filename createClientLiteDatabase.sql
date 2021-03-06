-- MySQL Script generated by MySQL Workbench
-- 04/09/17 15:28:49
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ClientLite
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ClientLite` ;

-- -----------------------------------------------------
-- Schema ClientLite
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ClientLite` DEFAULT CHARACTER SET utf8 ;
USE `ClientLite` ;

-- -----------------------------------------------------
-- Table `ClientLite`.`clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ClientLite`.`clients` ;

CREATE TABLE IF NOT EXISTS `ClientLite`.`clients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `telnum` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idclients_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ClientLite`.`treatments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ClientLite`.`treatments` ;

CREATE TABLE IF NOT EXISTS `ClientLite`.`treatments` (
  `location` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `clients_id` INT NOT NULL,
  PRIMARY KEY (`date`, `clients_id`),
  INDEX `fk_treatments_clients_idx` (`clients_id` ASC),
  CONSTRAINT `fk_treatments_clients`
    FOREIGN KEY (`clients_id`)
    REFERENCES `ClientLite`.`clients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `ClientLite`.`clients`
-- -----------------------------------------------------
START TRANSACTION;
USE `ClientLite`;
INSERT INTO `ClientLite`.`clients` (`id`, `fname`, `lname`, `telnum`) VALUES (1, 'Anna', 'Airaksinen', '0401357911');
INSERT INTO `ClientLite`.`clients` (`id`, `fname`, `lname`, `telnum`) VALUES (2, 'Björn', 'Bure', '0442468101');
INSERT INTO `ClientLite`.`clients` (`id`, `fname`, `lname`, `telnum`) VALUES (3, 'Cecilia', 'Collanius', '0501315171');
INSERT INTO `ClientLite`.`clients` (`id`, `fname`, `lname`, `telnum`) VALUES (4, 'Daniel', 'Dixon', '0402141618');
INSERT INTO `ClientLite`.`clients` (`id`, `fname`, `lname`, `telnum`) VALUES (5, 'Eeva', 'Eskolin', '0442022242');
INSERT INTO `ClientLite`.`clients` (`id`, `fname`, `lname`, `telnum`) VALUES (6, 'Fred', 'Flintstone', '0506283032');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ClientLite`.`treatments`
-- -----------------------------------------------------
START TRANSACTION;
USE `ClientLite`;
INSERT INTO `ClientLite`.`treatments` (`location`, `date`, `clients_id`) VALUES ('Business Park', '2017-01-13', 3);
INSERT INTO `ClientLite`.`treatments` (`location`, `date`, `clients_id`) VALUES ('Salon Beauty ', '2017-01-27', 1);
INSERT INTO `ClientLite`.`treatments` (`location`, `date`, `clients_id`) VALUES ('Massage Monsters', '2017-02-08', 2);
INSERT INTO `ClientLite`.`treatments` (`location`, `date`, `clients_id`) VALUES ('Business Park', '2017-02-15', 3);
INSERT INTO `ClientLite`.`treatments` (`location`, `date`, `clients_id`) VALUES ('Business Park', '2017-02-15', 4);
INSERT INTO `ClientLite`.`treatments` (`location`, `date`, `clients_id`) VALUES ('Salon Beauty', '2017-02-28', 5);
INSERT INTO `ClientLite`.`treatments` (`location`, `date`, `clients_id`) VALUES ('Salon Beauty', '2017-02-28', 6);
INSERT INTO `ClientLite`.`treatments` (`location`, `date`, `clients_id`) VALUES ('Massage Monsters', '2017-03-05', 4);

COMMIT;

