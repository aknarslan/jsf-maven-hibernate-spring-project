CREATE TABLE `order_db`.`customer` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NULL,
  `SURNAME` VARCHAR(45) NULL,
  `ADDRESS` VARCHAR(255) NULL,
  `PHONE_NUMBER` VARCHAR(20) NULL,
  `EMAIL` VARCHAR(50) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Müsteri Bilgilerinin tutuldugu tablodur.';

--------------------------------------------------------------------



CREATE TABLE `order_db`.`order_type` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TYPE_NAME` VARCHAR(50) NULL,
  PRIMARY KEY (`ID`))
COMMENT = 'siparis tiplerinin bulundugu tablodur';

---------------------------------------------------------------------------


CREATE TABLE `order_db`.`order` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` INT NULL,
  `ORDER_TYPE` VARCHAR(45) NULL,
  `SHIPPING_ADDRESS` VARCHAR(255) NULL,
  `EXPLANATION` VARCHAR(255) NULL,
  `STATUS` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
COMMENT = 'siparis bilgisinin tutuldugu tablodur';



-----------------------------------------------------------------------------------------------


CREATE TABLE `order_db`.`order_details` (
  `ID` INT NOT NULL,
  `ORDER_ID` INT NULL,
  `PRODUCT` VARCHAR(255) NULL,
  `QUANTITY` INT NULL,
  `PRICE` DECIMAL(10,2) NULL,
  PRIMARY KEY (`ID`))
COMMENT = 'siparis detaylarinin tutuldugu tablodur';

-----------------------------------------------------------------------------------------------------