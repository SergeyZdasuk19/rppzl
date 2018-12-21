CREATE DATABASE  IF NOT EXISTS `mydbtest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydbtest`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mydbtest
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `credit`
--

DROP TABLE IF EXISTS `credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `credit` (
  `FIOCredit` varchar(45) NOT NULL,
  `credit` varchar(200) DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `score` varchar(45) DEFAULT NULL,
  `suma` varchar(45) DEFAULT NULL,
  `percent` varchar(45) DEFAULT NULL,
  `number_score` varchar(45) DEFAULT NULL,
  `code_score` varchar(45) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`FIOCredit`),
  CONSTRAINT `credit_users_Surname_fk` FOREIGN KEY (`FIOCredit`) REFERENCES `users` (`surname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit`
--

LOCK TABLES `credit` WRITE;
/*!40000 ALTER TABLE `credit` DISABLE KEYS */;
INSERT INTO `credit` VALUES ('Здасюк','Кредит с ежемесячным погашением долга аннуитетным платежом.','BYR','13','10000','13','1423568974123','1423',9334.111243372881);
/*!40000 ALTER TABLE `credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposit`
--

DROP TABLE IF EXISTS `deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `deposit` (
  `FIO` varchar(45) NOT NULL,
  `deposit` varchar(45) NOT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `score` varchar(45) DEFAULT NULL,
  `suma` varchar(45) DEFAULT NULL,
  `percent` varchar(45) DEFAULT NULL,
  `number_score` varchar(45) DEFAULT NULL,
  `code_score` varchar(45) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`FIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit`
--

LOCK TABLES `deposit` WRITE;
/*!40000 ALTER TABLE `deposit` DISABLE KEYS */;
INSERT INTO `deposit` VALUES ('Здасюк','Отзывной','BYR','12','-2000','5','1425789653125','1212',-2098.6301369863013);
/*!40000 ALTER TABLE `deposit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `Name` varchar(45) DEFAULT NULL,
  `Surname` varchar(45) NOT NULL,
  `Patronymic` varchar(45) DEFAULT NULL,
  `DateOfBurn` varchar(45) DEFAULT NULL,
  `Passport` varchar(45) DEFAULT NULL,
  `PassportOut` varchar(45) DEFAULT NULL,
  `DateOfOut` varchar(45) DEFAULT NULL,
  `Mesto` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `Adress` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Surname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Сергей','Здасюк','Андреевич','05-04-1999','MR764565','РУВД','16-10-2006','','Минск','Изменил','7715198'),('Костя','Шатихин','Андреевич','08-07-2001','MR453446','РУВД','08-07-2005','','Минск','Колодищи','7715198');
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

-- Dump completed on 2018-12-21 13:52:13
