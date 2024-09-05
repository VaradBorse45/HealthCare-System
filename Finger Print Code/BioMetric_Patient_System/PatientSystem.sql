-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.15


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema patientsystem
--

CREATE DATABASE IF NOT EXISTS patientsystem;
USE patientsystem;

--
-- Definition of table `registration`
--

DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `month` varchar(45) NOT NULL,
  `day` varchar(45) NOT NULL,
  `year` varchar(45) NOT NULL,
  `mno` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `path` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` (`id`,`name`,`month`,`day`,`year`,`mno`,`address`,`path`) VALUES 
 ('121212121212','amol','May','6','2019','6767564534','pune','D:\\vTerm_XIII\\Java_Internship_Batch\\Eclipse_Workspace\\BioMetric Patient Indentification System\\xercesImpl-2.6.2.jar'),
 ('121212121213','Snehal','April','1','2015','6756453423','mubami','D:\\vTerm_XIII\\Java_Internship_Batch\\Eclipse_Workspace\\BioMetric Patient Indentification System\\CgtFpAccess-CSD200-Java.jar');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
