-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jennifer_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jennifer_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jennifer_db` DEFAULT CHARACTER SET utf8 ;
USE `jennifer_db` ;

-- -----------------------------------------------------
-- Table `jennifer_db`.`user_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`user_info` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`user_info` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `FULLNAME` VARCHAR(100) NULL,
  `EMAIL` VARCHAR(100) NULL,
  `PASSWORD` VARCHAR(100) NULL,
  `ROLE` VARCHAR(60) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `jennifer_db`.`category_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`category_info` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`category_info` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(60) NULL,
  `CATEGORY_ID` INT NULL,
  `PLACE_ORDER` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_category_info_category_info1_idx` (`CATEGORY_ID` ASC),
  CONSTRAINT `fk_category_info_category_info1`
    FOREIGN KEY (`CATEGORY_ID`)
    REFERENCES `jennifer_db`.`category_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`product_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`product_info` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`product_info` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NULL,
  `UNIT_PRICE` DECIMAL(13,2) NULL,
  `DISCOUNT` INT NULL,
  `IMAGE` TEXT NULL,
  `QUANTITY` INT NULL,
  `DESCRIPTION` TEXT NULL,
  `DETAIL` TEXT NULL,
  `STATUS` VARCHAR(45) NULL,
  `CATEGORY_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_product_info_category_info1_idx` (`CATEGORY_ID` ASC),
  CONSTRAINT `fk_product_info_category_info1`
    FOREIGN KEY (`CATEGORY_ID`)
    REFERENCES `jennifer_db`.`category_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `jennifer_db`.`shipping_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`shipping_address` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`shipping_address` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `RECIPIENT` VARCHAR(100) NULL,
  `ADDRESS` VARCHAR(120) NULL,
  `CITY` VARCHAR(100) NULL,
  `POSTAL_CODE` VARCHAR(10) NULL,
  `USER_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_shipping_address_user_info_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_shipping_address_user_info`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `jennifer_db`.`user_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`shopping_bag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`shopping_bag` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`shopping_bag` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `USER_ID` INT NOT NULL,
  INDEX `fk_shopping_bag_user_info1_idx` (`USER_ID` ASC),
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_shopping_bag_user_info1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `jennifer_db`.`user_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`delivery_method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`delivery_method` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`delivery_method` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NULL,
  `COST` DECIMAL(13,2) NULL,
  `START_DATE` DATE NULL,
  `END_DATE` DATE NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`order_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`order_info` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`order_info` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ORDER_DATE` DATETIME NULL,
  `TOTAL_PRICE` DECIMAL(13,2) NULL,
  `USER_ID` INT NOT NULL,
  `SHIPPING_ADDRESS_ID` INT NOT NULL,
  `DELIVERY_ID` INT NOT NULL,
  `STATUS` VARCHAR(45) NULL,
  INDEX `fk_order_user_info1_idx` (`USER_ID` ASC),
  PRIMARY KEY (`ID`),
  INDEX `fk_order_info_shipping_address1_idx` (`SHIPPING_ADDRESS_ID` ASC),
  INDEX `fk_order_info_delivery_method1_idx` (`DELIVERY_ID` ASC),
  CONSTRAINT `fk_order_user_info1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `jennifer_db`.`user_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_info_shipping_address1`
    FOREIGN KEY (`SHIPPING_ADDRESS_ID`)
    REFERENCES `jennifer_db`.`shipping_address` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_info_delivery_method1`
    FOREIGN KEY (`DELIVERY_ID`)
    REFERENCES `jennifer_db`.`delivery_method` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`order_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`order_detail` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`order_detail` (
  `ORDER_ID` INT NOT NULL,
  `PRODUCT_ID` INT NOT NULL,
  `UNIT_PRICE` DECIMAL(13,2) NULL,
  `QUANTITY` INT NULL,
  `APPLIED_DISCOUNT` INT NULL,
  PRIMARY KEY (`ORDER_ID`, `PRODUCT_ID`),
  INDEX `fk_order_detail_product_info1_idx` (`PRODUCT_ID` ASC),
  CONSTRAINT `fk_order_detail_order1`
    FOREIGN KEY (`ORDER_ID`)
    REFERENCES `jennifer_db`.`order_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_detail_product_info1`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `jennifer_db`.`product_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`marketing_campaign`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`marketing_campaign` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`marketing_campaign` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `EVENT` VARCHAR(200) NULL,
  `BANNER` TEXT NULL,
  `START_DATE` DATE NULL,
  `END_DATE` DATE NULL,
  `STATUS` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`campaign_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`campaign_product` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`campaign_product` (
  `PRODUCT_ID` INT NOT NULL,
  `CAMPAIGN_ID` INT NOT NULL,
  `DISCOUNT` INT NULL,
  PRIMARY KEY (`PRODUCT_ID`, `CAMPAIGN_ID`),
  INDEX `fk_table1_campaign1_idx` (`CAMPAIGN_ID` ASC),
  CONSTRAINT `fk_table1_product_info1`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `jennifer_db`.`product_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_campaign1`
    FOREIGN KEY (`CAMPAIGN_ID`)
    REFERENCES `jennifer_db`.`marketing_campaign` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`payment_invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`payment_invoice` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`payment_invoice` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PAYMENT_DATE` DATE NULL,
  `TOTAL_PRICE` DECIMAL(13,2) NULL,
  `ORDER_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_invoice_order1_idx` (`ORDER_ID` ASC),
  CONSTRAINT `fk_invoice_order1`
    FOREIGN KEY (`ORDER_ID`)
    REFERENCES `jennifer_db`.`order_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`webstore_setting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`webstore_setting` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`webstore_setting` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NULL,
  `CONTENT` TEXT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`shopping_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`shopping_product` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`shopping_product` (
  `SHOPPING_PRODUCT_ID` INT NOT NULL,
  `PRODUCT_ID` INT NOT NULL,
  `UNIT_PRICE` DECIMAL(13,2) NULL,
  `QUANTITY` INT NULL,
  `APPLIED_DISCOUNT` INT NULL,
  PRIMARY KEY (`SHOPPING_PRODUCT_ID`, `PRODUCT_ID`),
  INDEX `fk_shopping_product_product_info1_idx` (`PRODUCT_ID` ASC),
  CONSTRAINT `fk_shopping_product_shopping_bag1`
    FOREIGN KEY (`SHOPPING_PRODUCT_ID`)
    REFERENCES `jennifer_db`.`shopping_bag` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shopping_product_product_info1`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `jennifer_db`.`product_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`favorite_bag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`favorite_bag` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`favorite_bag` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `USER_ID` INT NOT NULL,
  INDEX `fk_shopping_bag_user_info1_idx` (`USER_ID` ASC),
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_shopping_bag_user_info10`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `jennifer_db`.`user_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`favorite_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`favorite_product` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`favorite_product` (
  `FAVORITE_PRODUCT_ID` INT NOT NULL,
  `PRODUCT_ID` INT NOT NULL,
  PRIMARY KEY (`FAVORITE_PRODUCT_ID`, `PRODUCT_ID`),
  INDEX `fk_shopping_product_product_info1_idx` (`PRODUCT_ID` ASC),
  CONSTRAINT `fk_shopping_product_shopping_bag10`
    FOREIGN KEY (`FAVORITE_PRODUCT_ID`)
    REFERENCES `jennifer_db`.`favorite_bag` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shopping_product_product_info10`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `jennifer_db`.`product_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jennifer_db`.`viewed_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jennifer_db`.`viewed_product` ;

CREATE TABLE IF NOT EXISTS `jennifer_db`.`viewed_product` (
  `USER_ID` INT NOT NULL,
  `PRODUCT_ID` INT NOT NULL,
  `NO_OF_TIMES` INT NULL,
  PRIMARY KEY (`USER_ID`, `PRODUCT_ID`),
  INDEX `fk_viewed_product_product_info1_idx` (`PRODUCT_ID` ASC),
  CONSTRAINT `fk_viewed_product_user_info1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `jennifer_db`.`user_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viewed_product_product_info1`
    FOREIGN KEY (`PRODUCT_ID`)
    REFERENCES `jennifer_db`.`product_info` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
