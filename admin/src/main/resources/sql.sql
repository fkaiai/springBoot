CREATE TABLE `sessions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jsessionid` varchar(200) DEFAULT NULL,
  `session` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8;
