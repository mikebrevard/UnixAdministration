#!/bin/bash

# instal mysql
echo "installing mysql-server"
if yum list installed mysql-server > /dev/null 2&>1; then
  echo "mysql-server already installed"
else
  sudo yum -y install mysql-server > /dev/null 2&>1;
  /etc/init.d/mysqld restart > /dev/null 2&>1
  chkconfig mysqld on
  chkconfig --list mysqld
fi

# set up sql user account
echo "creating user..."
if [ ! -f /var/._dbinit183 ]; then
  sudo mysql --user=root mysql < /vagrant/etc/sql/createUser.sql
  echo "User 'unixadmin' has been created with password 'unixadmin'"
  sudo touch /var/._dbinit183
else
  echo "mysql user 'unixadmin' already created with password 'unixadmin'"
fi

# set up database and tables
echo "initialize database and tables"
if [ ! -f /var/._dbpop183 ]; then
  sudo mysql < /vagrant/etc/sql/basicTableInit.sql
  sudo mysql < /vagrant/etc/sql/basicTablePopulate.sql
  sudo touch /var/._dbpop183
else
  echo "database already populated"
fi

echo "linking real time"
sudo rm -f /etc/localtime
sudo ln -s /usr/share/zoneinfo/America/Los_Angeles /etc/localtime
echo "Today is:"
date
