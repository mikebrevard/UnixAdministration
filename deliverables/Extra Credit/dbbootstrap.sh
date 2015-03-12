#!/bin/bash

# instal mysql
echo "installing mysql-server"
if yum list installed mysql-server > /dev/null 2>&1; then
  echo "mysql-server already installed"
else
  sudo yum -y install mysql-server > /dev/null 2>&1;
  /etc/init.d/mysqld restart > /dev/null 2>&1
  chkconfig mysqld on
  chkconfig --list mysqld
fi

# set up sql user account
echo "creating user..."
if sudo mysql --user=root mysql < /vagrant/etc/data/createUser.sql > /dev/null 2>&1; then
  echo "mysql user 'unixadmin' has been created with password 'unixadmin'"
else
  echo "mysql user 'unixadmin' already created with password 'unixadmin'"
fi

# set up database and tables
echo "initialize database and tables"
if sudo mysql < /vagrant/etc/data/createTable.sql > /dev/null 2>&1; then
  sudo chmod 700 /vagrant/etc/data/importDataMySQL.sh
  /vagrant/etc/data/importDataMySql.sh
  echo "Populate completed."
else
  echo "database already populated"
fi

echo "linking real time"
sudo rm -f /etc/localtime
sudo ln -s /usr/share/zoneinfo/America/Los_Angeles /etc/localtime
echo "Today is:"
date
