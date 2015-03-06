

CREATE TABLE `basicTable` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `ColumnID` mediumint,
  `Name` varchar(255) default NULL,
  `Email` varchar(255) default NULL,
  `Ccnum` varchar(16) default NULL,
  `Address` varchar(255) default NULL,
  `LatLng` varchar(30) default NULL,
  `Rand` TEXT default NULL,
  `Company` varchar(255),
  `MathNum` varchar(100),
  `Country` varchar(100) default NULL,
  `Zip` varchar(10) default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;
