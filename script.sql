DROP DATABASE IF EXISTS springboot;
CREATE DATABASE springboot;
USE springboot;

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int unsigned NOT NULL auto_increment,
  `Lastname` varchar(50) default NULL,
  `Firstname` varchar(50) default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

INSERT INTO `person` (`lastName`,`firstName`)
VALUES
  ("Hyde","Jemima"),
  ("Thornton","Adele"),
  ("Ramirez","Shellie"),
  ("Pennington","Kelly"),
  ("Forbes","Bruno"),
  ("Britt","Quinn"),
  ("Glass","Jescie"),
  ("Booker","Thane"),
  ("Riddle","Lance"),
  ("Kline","Rylee"),
  ("Neal","Sonia"),
  ("Mitchell","Neil"),
  ("Saunders","Alexis"),
  ("Watkins","Branden"),
  ("Vaughan","Emi"),
    ("Hancock","Palmer"),
  ("Holloway","Isabella"),
  ("Cotton","Silas"),
  ("Wong","Ryder"),
  ("Cooper","Nero"),
	("Delacruz","Tallulah"),
  ("Sullivan","Imelda"),
  ("Stewart","Orlando"),
  ("Drake","Thor"),
  ("Strickland","Adrienne");