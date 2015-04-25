-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: slime_soccer_db
-- ------------------------------------------------------
-- Server version	5.6.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_data`
--

DROP TABLE IF EXISTS `account_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_data` (
  `userID` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `player_firstName` varchar(45) NOT NULL,
  `player_lastName` varchar(45) NOT NULL,
  `player_description` longtext,
  `player_avatar` int(11) NOT NULL DEFAULT '0',
  `player_ratio` float NOT NULL DEFAULT '0',
  `player_games` int(11) NOT NULL,
  `player_won` int(11) NOT NULL,
  `player_loss` int(11) NOT NULL,
  `player_achievements` varchar(45) DEFAULT NULL,
  `player_days` int(11) NOT NULL DEFAULT '0',
  `player_messages` int(11) DEFAULT NULL,
  `player_volume` int(11) NOT NULL DEFAULT '50',
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_data`
--

LOCK TABLES `account_data` WRITE;
/*!40000 ALTER TABLE `account_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achievement_data`
--

DROP TABLE IF EXISTS `achievement_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achievement_data` (
  `achievementID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `logo_filepath` varchar(45) NOT NULL,
  PRIMARY KEY (`achievementID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement_data`
--

LOCK TABLES `achievement_data` WRITE;
/*!40000 ALTER TABLE `achievement_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievement_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ball_data`
--

DROP TABLE IF EXISTS `ball_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ball_data` (
  `ballID` int(11) NOT NULL,
  `positionX` int(11) DEFAULT NULL,
  `positionY` int(11) DEFAULT NULL,
  `velocityX` int(11) DEFAULT NULL,
  `velocityY` int(11) DEFAULT NULL,
  `radius` int(11) DEFAULT '12',
  PRIMARY KEY (`ballID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ball_data`
--

LOCK TABLES `ball_data` WRITE;
/*!40000 ALTER TABLE `ball_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `ball_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `friendshipID` int(11) NOT NULL,
  `friend1_ID` int(11) NOT NULL,
  `friend2_ID` int(11) NOT NULL,
  PRIMARY KEY (`friendshipID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_data`
--

DROP TABLE IF EXISTS `game_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_data` (
  `game_ID` int(11) NOT NULL,
  `background` int(11) NOT NULL,
  `timer` double NOT NULL,
  `player1_slimeType` int(11) NOT NULL,
  `player1_username` varchar(45) NOT NULL,
  `player1_manaCurrent` int(11) NOT NULL,
  `player1_score` int(11) NOT NULL,
  `player2_slimeType` int(11) NOT NULL,
  `player2_username` varchar(45) NOT NULL,
  `player2_manaCurrent` int(11) NOT NULL,
  `player2_score` int(11) NOT NULL,
  `manaRegenerationRate` int(11) NOT NULL,
  `specialMode` int(11) DEFAULT NULL,
  PRIMARY KEY (`game_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_data`
--

LOCK TABLES `game_data` WRITE;
/*!40000 ALTER TABLE `game_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slime_data`
--

DROP TABLE IF EXISTS `slime_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slime_data` (
  `slimeID` int(11) NOT NULL,
  `accessory_head` int(11) DEFAULT NULL,
  `accessory_body` int(11) DEFAULT NULL,
  `accessory_eye` int(11) DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  `radius` int(11) NOT NULL DEFAULT '12',
  PRIMARY KEY (`slimeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slime_data`
--

LOCK TABLES `slime_data` WRITE;
/*!40000 ALTER TABLE `slime_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `slime_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'slime_soccer_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-24 17:48:58
