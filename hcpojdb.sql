/*
SQLyog Ultimate v9.20 
MySQL - 5.7.17-log : Database - hcpojdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hcpojdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hcpojdb`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `access` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`,`access`) values (9,'wy','passaaa','1'),(10,'wy1111111','1111111111','111');

/*Table structure for table `alarm` */

DROP TABLE IF EXISTS `alarm`;

CREATE TABLE `alarm` (
  `alarmId` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `realOrNot` varchar(5) DEFAULT NULL,
  `alarmTime` varchar(20) DEFAULT NULL,
  `oldManId` int(11) DEFAULT NULL,
  PRIMARY KEY (`alarmId`),
  KEY `oldManId` (`oldManId`),
  CONSTRAINT `alarm_ibfk_1` FOREIGN KEY (`oldManId`) REFERENCES `oldman` (`oldManId`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `alarm` */

insert  into `alarm`(`alarmId`,`type`,`realOrNot`,`alarmTime`,`oldManId`) values (5,'呼吸过快',NULL,'20170920091107',19),(6,'呼吸过慢','真','20170921091107',NULL),(7,'呼吸过快',NULL,'20170920091107',19),(8,'呼吸过快',NULL,'20170920091107',19),(9,'呼吸过快',NULL,'20170920091107',19),(10,'呼吸过快',NULL,'20170920091107',19),(11,'呼吸过快',NULL,'20170920091107',19),(12,'呼吸过快',NULL,'20170920091107',19),(13,'呼吸过快',NULL,'20170920091107',19),(14,'呼吸过快',NULL,'20170920091107',19),(15,'呼吸过快',NULL,'20170920091107',19),(16,'呼吸过快',NULL,'20170920091107',19),(17,'呼吸过快',NULL,'20170920091107',19),(18,'呼吸过快',NULL,'20170920091107',19),(19,'呼吸过快',NULL,'20170920091107',19),(20,'呼吸过快',NULL,'20170920091107',19),(21,'呼吸过快',NULL,'20170920091107',19),(22,'呼吸过快',NULL,'20170920091107',19),(23,'呼吸过快',NULL,'20170920091107',19),(24,'呼吸过快',NULL,'20170920091107',19),(25,'呼吸过快',NULL,'20170920091107',19),(26,'呼吸过快',NULL,'20170920091107',19),(27,'呼吸过快',NULL,'20170920091107',19),(28,'呼吸过快',NULL,'20170920091107',19),(29,'呼吸过快',NULL,'20170920091107',19),(30,'呼吸过快',NULL,'20170920091107',19),(31,'呼吸过快',NULL,'20170920091107',19),(32,'呼吸过快',NULL,'20170920091107',19),(33,'呼吸过快',NULL,'20170920091107',19),(34,'呼吸过快',NULL,'20170920091107',19),(35,'呼吸过快',NULL,'20170920091107',19),(36,'呼吸过快',NULL,'20170920091107',19),(37,'呼吸过快',NULL,'20170920091107',19),(38,'呼吸过快',NULL,'20170920091107',19),(39,'呼吸过快',NULL,'20170920091107',19),(40,'呼吸过快',NULL,'20170920091107',19),(41,'呼吸过快',NULL,'20170920091107',19),(42,'呼吸过快',NULL,'20170920091107',19),(43,'呼吸过快',NULL,'20170920091107',19),(44,'呼吸过快',NULL,'20170920091107',19),(45,'呼吸过快',NULL,'20170920091107',19),(46,'呼吸过快',NULL,'20170920091107',19),(47,'呼吸过快',NULL,'20170920091107',19),(48,'呼吸过快',NULL,'20170920091107',19),(49,'呼吸过快',NULL,'20170920091107',19),(50,'呼吸过快',NULL,'20170920091107',19),(51,'呼吸过快',NULL,'20170920091107',19),(52,'呼吸过快',NULL,'20170920091107',19),(53,'呼吸过快',NULL,'20170920091107',19),(54,'呼吸过快',NULL,'20170920091107',19),(55,'呼吸过快',NULL,'20170920091107',19),(56,'呼吸过快',NULL,'20170920091107',19);

/*Table structure for table `breath` */

DROP TABLE IF EXISTS `breath`;

CREATE TABLE `breath` (
  `BreathId` bigint(11) NOT NULL AUTO_INCREMENT,
  `breathDateTime` varchar(20) NOT NULL,
  `oldManIdfk` int(11) DEFAULT NULL,
  PRIMARY KEY (`BreathId`),
  KEY `oldManIdfk` (`oldManIdfk`),
  CONSTRAINT `breath_ibfk_1` FOREIGN KEY (`oldManIdfk`) REFERENCES `oldman` (`oldManId`)
) ENGINE=InnoDB AUTO_INCREMENT=1224 DEFAULT CHARSET=utf8;

/*Data for the table `breath` */

insert  into `breath`(`BreathId`,`breathDateTime`,`oldManIdfk`) values (1171,'20170920091101088',19),(1172,'20170920091103900',19),(1173,'20170920091107772',19),(1174,'20170920091023768',19),(1175,'20170920091026672',19),(1176,'20170920091029076',19),(1177,'20170920091032684',19),(1178,'20170920091035588',19),(1179,'20170920091039304',19),(1180,'20170920091041852',19),(1181,'20170920091044504',19),(1182,'20170920091048284',19),(1183,'20170920091051736',19),(1184,'20170920091056044',19),(1185,'20170920091101088',19),(1186,'20170920091103900',19),(1187,'20170920091107772',19),(1188,'20170920091107772',19),(1189,'20170920091107772',19),(1190,'20170920091107772',19),(1191,'20170920091107772',19),(1192,'20170920091107772',19),(1193,'20170920091107772',19),(1194,'20170920091107772',19),(1195,'20170920091107772',19),(1196,'20170920091107772',19),(1197,'20170920091107772',19),(1198,'20170920091107772',19),(1199,'20170920091107772',19),(1200,'20170920091107772',19),(1201,'20170920091107772',19),(1202,'20170920091107772',19),(1203,'20170920091107772',19),(1204,'20170920091107772',19),(1205,'20170920091107772',19),(1206,'20170920091107772',19),(1207,'20170920091107772',19),(1208,'20170920091107772',19),(1209,'20170920091107772',19),(1210,'20170920091107772',19),(1211,'20170920091107772',19),(1212,'20170920091107772',19),(1213,'20170920091107772',19),(1214,'20170920091107772',19),(1215,'20170920091107772',19),(1216,'20170920091107772',19),(1217,'20170920091107772',19),(1218,'20170920091107772',19),(1219,'20170920091107772',19),(1220,'20170920091107772',19),(1221,'20170920091107772',19),(1222,'20170920091107772',19),(1223,'20170920091107772',19);

/*Table structure for table `child` */

DROP TABLE IF EXISTS `child`;

CREATE TABLE `child` (
  `childId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `useraccount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`childId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `child` */

insert  into `child`(`childId`,`username`,`password`,`address`,`telephone`,`useraccount`) values (3,'test','1234',NULL,NULL,'child1'),(4,NULL,'1234',NULL,NULL,'rest'),(5,NULL,'1234',NULL,NULL,'邵自强');

/*Table structure for table `heart` */

DROP TABLE IF EXISTS `heart`;

CREATE TABLE `heart` (
  `heartId` int(11) NOT NULL AUTO_INCREMENT,
  `heartDateTime` varchar(20) DEFAULT NULL,
  `oldManIdfk` int(11) DEFAULT NULL,
  PRIMARY KEY (`heartId`),
  KEY `oldManIdfk` (`oldManIdfk`),
  CONSTRAINT `heart_ibfk_1` FOREIGN KEY (`oldManIdfk`) REFERENCES `oldman` (`oldManId`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `heart` */

insert  into `heart`(`heartId`,`heartDateTime`,`oldManIdfk`) values (9,'20170920091025236',19),(10,'20170920091025816',19),(11,'20170920091026624',19),(12,'20170920091027580',19),(13,'20170920091028092',19),(14,'20170920091029140',19),(15,'20170920091030140',19),(16,'20170920091031016',19),(17,'20170920091032312',19),(18,'20170920091033136',19),(19,'20170920091033760',19),(20,'20170920091034388',19),(21,'20170920091035088',19),(22,'20170920091035668',19),(23,'20170920091036560',19),(24,'20170920091037120',19),(25,'20170920091038180',19),(26,'20170920091038728',19),(27,'20170920091039588',19),(28,'20170920091044036',19),(29,'20170920091044832',19),(30,'20170920091045676',19),(31,'20170920091046472',19),(32,'20170920091047096',19),(33,'20170920091047708',19),(34,'20170920091048360',19),(35,'20170920091048940',19),(36,'20170920091049660',19),(37,'20170920091050268',19),(38,'20170920091050848',19),(39,'20170920091051580',19),(40,'20170920091052140',19),(41,'20170920091053016',19),(42,'20170920091053812',19),(43,'20170920091054468',19),(44,'20170920091055436',19),(45,'20170920091056468',19),(46,'20170920091057576',19),(47,'20170920091058136',19),(48,'20170920091059136',19),(49,'20170920091059668',19),(50,'20170920091100544',19),(51,'20170920091101684',19),(52,'20170920091102808',19),(53,'20170920091103664',19),(54,'20170920091117344',19),(55,'20170920091121200',19),(56,'20170920091131192',19),(57,'20170920091132064',19),(58,'20170920091134860',19),(59,'20170920091137892',19),(60,'20170920091149744',19),(61,'20170920091154080',19),(62,'20170920091158256',19),(63,'20170920091202372',19),(64,'20170920091214248',19),(65,'20170920091216220',19),(66,'20170920091217344',19),(67,'20170920091218312',19),(68,'20170920091218860',19),(69,'20170920091228384',19),(70,'20170920091245180',19),(71,'20170920091246384',19);

/*Table structure for table `messagepush` */

DROP TABLE IF EXISTS `messagepush`;

CREATE TABLE `messagepush` (
  `messagePushId` int(11) NOT NULL AUTO_INCREMENT,
  `pushTime` varchar(0) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `oldManIdfk` int(11) NOT NULL,
  PRIMARY KEY (`messagePushId`),
  KEY `oldManIdfk` (`oldManIdfk`),
  CONSTRAINT `messagepush_ibfk_1` FOREIGN KEY (`oldManIdfk`) REFERENCES `oldman` (`oldManId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `messagepush` */

/*Table structure for table `movement` */

DROP TABLE IF EXISTS `movement`;

CREATE TABLE `movement` (
  `movementId` int(11) NOT NULL AUTO_INCREMENT,
  `movementDateTime` varchar(20) NOT NULL,
  `Start` varchar(20) DEFAULT NULL,
  `Stop` varchar(20) DEFAULT NULL,
  `oldManIdfk` int(11) DEFAULT NULL,
  PRIMARY KEY (`movementId`),
  KEY `oldManIdfk` (`oldManIdfk`),
  CONSTRAINT `movement_ibfk_1` FOREIGN KEY (`oldManIdfk`) REFERENCES `oldman` (`oldManId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `movement` */

insert  into `movement`(`movementId`,`movementDateTime`,`Start`,`Stop`,`oldManIdfk`) values (6,'20170920091027736',NULL,NULL,19),(7,'20170920091056732',NULL,NULL,19);

/*Table structure for table `nurse` */

DROP TABLE IF EXISTS `nurse`;

CREATE TABLE `nurse` (
  `nurseId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`nurseId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `nurse` */

insert  into `nurse`(`nurseId`,`username`,`password`,`telephone`) values (1,'护工1号','111','18710839295'),(2,'护工2号','222','110'),(3,'护工三号','333','新的手机号');

/*Table structure for table `oldman` */

DROP TABLE IF EXISTS `oldman`;

CREATE TABLE `oldman` (
  `oldManId` int(11) NOT NULL AUTO_INCREMENT,
  `useraccount` varchar(50) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `illness` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `pillowId` int(11) DEFAULT NULL,
  `wifiId` int(11) DEFAULT NULL,
  `nurseId` int(11) DEFAULT NULL,
  PRIMARY KEY (`oldManId`),
  KEY `FKC38AF1D3CE0FC931` (`roleId`),
  KEY `FKC38AF1D3D642FAAF` (`wifiId`),
  KEY `FKC38AF1D378C220C7` (`pillowId`),
  KEY `FKC38AF1D32AB2D4AB` (`nurseId`),
  CONSTRAINT `FKC38AF1D32AB2D4AB` FOREIGN KEY (`nurseId`) REFERENCES `nurse` (`nurseId`),
  CONSTRAINT `FKC38AF1D378C220C7` FOREIGN KEY (`pillowId`) REFERENCES `pillow` (`PillowId`),
  CONSTRAINT `FKC38AF1D3CE0FC931` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `FKC38AF1D3D642FAAF` FOREIGN KEY (`wifiId`) REFERENCES `wifi` (`wifiId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `oldman` */

insert  into `oldman`(`oldManId`,`useraccount`,`username`,`password`,`gender`,`height`,`weight`,`age`,`illness`,`address`,`telephone`,`roleId`,`pillowId`,`wifiId`,`nurseId`) values (19,'test123','王五','1234','男',175,55,75,NULL,'西安碑林区','18789564569',1,15,NULL,NULL),(21,NULL,'张三',NULL,NULL,NULL,NULL,56,NULL,NULL,NULL,1,NULL,NULL,NULL),(24,NULL,'旺旺',NULL,NULL,NULL,NULL,78,NULL,NULL,NULL,1,NULL,NULL,NULL);

/*Table structure for table `pillow` */

DROP TABLE IF EXISTS `pillow`;

CREATE TABLE `pillow` (
  `pillowId` int(11) NOT NULL AUTO_INCREMENT,
  `pillowNumber` varchar(20) DEFAULT NULL,
  `statement` varchar(20) DEFAULT NULL,
  `configurationTime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pillowId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `pillow` */

insert  into `pillow`(`pillowId`,`pillowNumber`,`statement`,`configurationTime`) values (3,NULL,'正常','20171104'),(6,'123','正常','2017-07-27 08:38:05'),(7,'sheb','正常','2017-07-27 11:27:43'),(9,'sss123','正常','2017-07-28 04:48:21'),(15,'Whatname','正常','2017-07-30 02:10:25'),(16,'shebei123','正常','2017-08-02 10:38:11'),(17,'shebei123','正常','2017-08-02 10:38:13'),(18,'pillow123','正常','2017-08-04 02:04:36'),(19,'pillow456','正常','2017-08-04 02:18:24'),(20,'','正常','2017-08-31 02:44:52'),(21,'','正常','2017-08-31 02:44:58'),(22,'sb91','正常','2017-09-01 03:40:06'),(23,'sb92','正常','2017-09-02 12:17:04'),(24,'sb94','正常','2017-09-04 02:47:38');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

/*Table structure for table `relation` */

DROP TABLE IF EXISTS `relation`;

CREATE TABLE `relation` (
  `relationId` int(11) NOT NULL AUTO_INCREMENT,
  `oldManIdfk` int(11) DEFAULT NULL,
  `childIdfk` int(11) DEFAULT NULL,
  `relationship` varchar(10) DEFAULT NULL,
  `flag` int(1) unsigned zerofill DEFAULT '0',
  PRIMARY KEY (`relationId`),
  KEY `oldManIdfk` (`oldManIdfk`),
  KEY `childIdfk` (`childIdfk`),
  CONSTRAINT `relation_ibfk_1` FOREIGN KEY (`oldManIdfk`) REFERENCES `oldman` (`oldManId`),
  CONSTRAINT `relation_ibfk_2` FOREIGN KEY (`childIdfk`) REFERENCES `child` (`childId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `relation` */

insert  into `relation`(`relationId`,`oldManIdfk`,`childIdfk`,`relationship`,`flag`) values (14,19,3,'母亲',2),(15,19,5,'父亲',2),(17,19,4,'其它',2);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`roleId`,`roleName`) values (1,'old'),(2,'child'),(3,'nurse'),(4,'安琦'),(5,'qqq');

/*Table structure for table `sleepquality` */

DROP TABLE IF EXISTS `sleepquality`;

CREATE TABLE `sleepquality` (
  `sleepQualityId` int(11) NOT NULL AUTO_INCREMENT,
  `sleepDate` varchar(20) NOT NULL,
  `goToBed` varchar(20) DEFAULT NULL,
  `getUp` varchar(20) DEFAULT NULL,
  `Socre` int(11) DEFAULT NULL,
  `oldManIdfk` int(11) NOT NULL,
  PRIMARY KEY (`sleepQualityId`),
  KEY `oldManIdfk` (`oldManIdfk`),
  CONSTRAINT `sleepquality_ibfk_1` FOREIGN KEY (`oldManIdfk`) REFERENCES `oldman` (`oldManId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sleepquality` */

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`adminName`,`pwd`) values (1,'wy','111'),(2,'wy','1'),(3,'wy','222'),(4,'123','123'),(5,'123','456'),(6,'bbb','bbb'),(7,'z','z'),(8,'q','q'),(9,'g','g'),(10,'v','v');

/*Table structure for table `t_dept` */

DROP TABLE IF EXISTS `t_dept`;

CREATE TABLE `t_dept` (
  `deptId` int(11) NOT NULL AUTO_INCREMENT,
  `deptName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `t_dept` */

insert  into `t_dept`(`deptId`,`deptName`) values (1,'开发部'),(2,'食堂部');

/*Table structure for table `t_employee` */

DROP TABLE IF EXISTS `t_employee`;

CREATE TABLE `t_employee` (
  `empId` int(11) NOT NULL AUTO_INCREMENT,
  `empName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`empId`),
  KEY `FKFDCF5A1961DB1044` (`dept_id`),
  CONSTRAINT `FKFDCF5A1961DB1044` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`deptId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `t_employee` */

insert  into `t_employee`(`empId`,`empName`,`salary`,`dept_id`) values (1,'wangying',2000,2),(2,'aaa',200,1);

/*Table structure for table `wifi` */

DROP TABLE IF EXISTS `wifi`;

CREATE TABLE `wifi` (
  `wifiId` int(11) NOT NULL AUTO_INCREMENT,
  `wifiNumber` varchar(20) DEFAULT NULL,
  `statement` varchar(20) DEFAULT NULL,
  `configurationTime` varchar(20) DEFAULT NULL,
  `oldManIdfk` int(11) DEFAULT NULL,
  PRIMARY KEY (`wifiId`),
  KEY `oldManIdfk` (`oldManIdfk`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `wifi` */

insert  into `wifi`(`wifiId`,`wifiNumber`,`statement`,`configurationTime`,`oldManIdfk`) values (1,NULL,'wifi状态1','1111',19),(2,NULL,'wifi状态3333','22223333',2),(3,NULL,'ttt','ttttt',NULL),(11,NULL,'bb','bb',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
