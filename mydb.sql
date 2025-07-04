-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`monitoreo_climatico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`monitoreo_climatico` ;

CREATE TABLE IF NOT EXISTS `mydb`.`monitoreo_climatico` (
  `idMonitoreo` INT NOT NULL AUTO_INCREMENT,
  `ciudad` VARCHAR(100) NOT NULL,
  `fecha` DATE NOT NULL,
  `temperaturaPromedioDia` DECIMAL(5,3) NOT NULL,
  `condicionFrecuente` VARCHAR(100) NOT NULL,
  `temperaturaMax` DECIMAL(5,3) NOT NULL,
  `temperaturaMin` DECIMAL(5,3) NOT NULL,
  PRIMARY KEY (`idMonitoreo`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
