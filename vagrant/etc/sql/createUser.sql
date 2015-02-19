CREATE USER 'unixadmin'@'localhost' IDENTIFIED BY 'unixadmin';
GRANT ALL PRIVILEGES ON *.* TO 'unixadmin'@'localhost' WITH GRANT OPTION;

CREATE USER 'unixadmin'@'%' IDENTIFIED BY 'unixadmin';
GRANT ALL PRIVILEGES ON *.* TO 'unixadmin'@'%' WITH GRANT OPTION;
