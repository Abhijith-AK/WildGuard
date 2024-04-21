/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - wild_animal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wild_animal` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `wild_animal`;

/*Table structure for table `alert` */

DROP TABLE IF EXISTS `alert`;

CREATE TABLE `alert` (
  `alert_id` int(11) NOT NULL AUTO_INCREMENT,
  `camera_id` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `image` varchar(600) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`alert_id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Data for the table `alert` */

insert  into `alert`(`alert_id`,`camera_id`,`description`,`date`,`image`,`time`) values 
(1,1,'lion','2023-12-12','static/image_lion.png',NULL),
(2,1,'lion','2023-12-12','static/image_lion.png',NULL),
(3,1,'lion','2023-12-12','static/image_lion.png',NULL),
(4,1,'lion','2023-12-12','static/image_lion.png',NULL),
(5,1,'lion','2023-12-12','static/image_lion.png',NULL),
(6,1,'lion','2023-12-12','static/image_lion.png',NULL),
(7,1,'lion','2023-12-12','static/image_lion.png',NULL),
(8,1,'lion','2023-12-12','static/image_lion.png',NULL),
(9,1,'lion','2023-12-12','static/image_lion.png',NULL),
(10,1,'lion','2023-12-12','static/image_lion.png',NULL),
(11,1,'lion','2023-12-12','static/image_lion.png',NULL),
(12,1,'lion','2023-12-12','static/image_lion.png',NULL),
(13,1,'lion','2023-12-12','static/image_lion.png',NULL),
(14,1,'cow','2023-12-12','static/image_cow.png',NULL),
(15,1,'cow','2023-12-12','static/image_cow.png',NULL),
(16,1,'cow','2023-12-14','static/image_cow.png','14:58:59'),
(17,1,'cow','2023-12-15','static/image_cow.png','09:41:08'),
(18,1,'lion','2023-12-15','static/image_lion.png','10:31:48'),
(19,1,'lion','2023-12-16','static/image_lion.png','15:29:11'),
(20,1,'cow','2023-12-16','static/image_cow.png','15:35:08'),
(21,1,'cow','2023-12-18','static/image_cow.png','16:41:13'),
(22,1,'cow','2023-12-19','static/image_cow.png','16:01:21'),
(23,1,'cow','2023-12-19','static/image_cow.png','16:01:28');

/*Table structure for table `allocate` */

DROP TABLE IF EXISTS `allocate`;

CREATE TABLE `allocate` (
  `allocate_id` int(11) NOT NULL AUTO_INCREMENT,
  `officer_id` int(11) DEFAULT NULL,
  `forest_station_id` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`allocate_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `allocate` */

insert  into `allocate`(`allocate_id`,`officer_id`,`forest_station_id`,`status`) values 
(2,6,1,'allocate'),
(3,5,1,'pending');

/*Table structure for table `animal` */

DROP TABLE IF EXISTS `animal`;

CREATE TABLE `animal` (
  `animal_id` int(11) NOT NULL AUTO_INCREMENT,
  `forest_division_id` int(11) DEFAULT NULL,
  `animal_name` varchar(100) DEFAULT NULL,
  `animal_image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`animal_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `animal` */

insert  into `animal`(`animal_id`,`forest_division_id`,`animal_name`,`animal_image`) values 
(7,3,'jjjj','static/a61394c0-b7d2-4f3e-ae2c-9c75d6ef006ftreeq.png'),
(2,3,'aaaa','static/d2480fe2-9d68-47c1-b686-8432cd321363instagram-font-logo-3.png'),
(6,3,'lion','static/6a03eaa8-79b4-44bd-b4e9-b52854406242lionlogo.jpg');

/*Table structure for table `camera` */

DROP TABLE IF EXISTS `camera`;

CREATE TABLE `camera` (
  `camera_id` int(11) NOT NULL AUTO_INCREMENT,
  `forest_station_id` int(11) DEFAULT NULL,
  `camera_name` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`camera_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `camera` */

insert  into `camera`(`camera_id`,`forest_station_id`,`camera_name`,`latitude`,`longitude`) values 
(1,1,'aaa','9.969585499989867','76.28487009853662'),
(2,1,'','9.96634499026836','76.27843279690087');

/*Table structure for table `complaints` */

DROP TABLE IF EXISTS `complaints`;

CREATE TABLE `complaints` (
  `complaints_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`complaints_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `complaints` */

insert  into `complaints`(`complaints_id`,`user_id`,`title`,`description`,`reply`,`date`) values 
(1,5,'aaa','aaaa','aa','2023-12-08'),
(2,5,'','','checked','2023-12-11'),
(3,5,'','','checked','2023-12-18'),
(4,5,'hhhh','uuuu','checked','2023-12-18'),
(5,5,'hhhh','uuuu','pending','2023-12-18');

/*Table structure for table `contact_details` */

DROP TABLE IF EXISTS `contact_details`;

CREATE TABLE `contact_details` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `contact` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `contact_details` */

insert  into `contact_details`(`contact_id`,`name`,`contact`) values 
(2,'bbb','bbb'),
(3,'ccc','cccc');

/*Table structure for table `forest_division` */

DROP TABLE IF EXISTS `forest_division`;

CREATE TABLE `forest_division` (
  `forest_division_id` int(11) NOT NULL AUTO_INCREMENT,
  `division_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`forest_division_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `forest_division` */

insert  into `forest_division`(`forest_division_id`,`division_name`) values 
(3,'aaaaa'),
(4,'aaa'),
(5,'aaa');

/*Table structure for table `forest_officer` */

DROP TABLE IF EXISTS `forest_officer`;

CREATE TABLE `forest_officer` (
  `officer_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  KEY `officer_id` (`officer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `forest_officer` */

insert  into `forest_officer`(`officer_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`,`designation`) values 
(6,13,'aa','aa','aa','aa','aaaa@gmail.com','aa'),
(4,11,'qqqq','qq','qqq','qqq','qq@gmail.com','qq'),
(5,12,'ww','ww','ww','ww','ww@gmail.com','ww');

/*Table structure for table `forest_station` */

DROP TABLE IF EXISTS `forest_station`;

CREATE TABLE `forest_station` (
  `forest_station_id` int(11) NOT NULL AUTO_INCREMENT,
  `forest_division_id` int(11) DEFAULT NULL,
  `station_name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `contact_number` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`forest_station_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `forest_station` */

insert  into `forest_station`(`forest_station_id`,`forest_division_id`,`station_name`,`place`,`contact_number`) values 
(1,3,'aaa','aa','bb'),
(3,3,'aaa','aa','aaa');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'aaa','aa','officer'),
(3,'bbbb','bbbb','officer'),
(4,'ff@gmail.com','ff@gmail.com','user'),
(5,'ff@gmail.com','ff@gmail.com','user'),
(6,'','','user'),
(7,'ccc','ccc','user'),
(8,'aaa','aaa','user'),
(9,'','','user'),
(10,'','','officer'),
(11,'qq','qq','officer'),
(12,'ww','ww','officer'),
(13,'qqq','qqq','officer'),
(14,'vhhh','vhhh','user');

/*Table structure for table `notifications` */

DROP TABLE IF EXISTS `notifications`;

CREATE TABLE `notifications` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `notifications` */

insert  into `notifications`(`notification_id`,`title`,`description`,`date`) values 
(1,'aa','aa','2023-12-08'),
(2,'','','2023-12-15');

/*Table structure for table `preserved_animals` */

DROP TABLE IF EXISTS `preserved_animals`;

CREATE TABLE `preserved_animals` (
  `preserved_animal_id` int(11) NOT NULL AUTO_INCREMENT,
  `animal_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`preserved_animal_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `preserved_animals` */

insert  into `preserved_animals`(`preserved_animal_id`,`animal_id`) values 
(4,2),
(5,2),
(7,3),
(8,3),
(9,3),
(10,3),
(11,2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `forest_station_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`forest_station_id`,`fname`,`lname`,`place`,`phone`,`email`) values 
(1,4,1,'ff@gmail.com','ff@gmail.com','ff@gmail.com','ff@gmail.com',''),
(2,5,1,'ff@gmail.com','ff@gmail.com','ff@gmail.com','ff@gmail.com',''),
(3,6,1,'','','','',''),
(4,7,1,'ccc','ccc','ccc','ccx',''),
(5,8,1,'aa','aa','aaa','aaaa','aaa@gma.com'),
(6,3,1,'aa','aa','aa','aa','aa'),
(7,14,3,'hhh','vgh','fdf','vvvh','vvgg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
