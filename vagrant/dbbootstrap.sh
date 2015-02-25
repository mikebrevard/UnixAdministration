#!/bin/bash

# update !!!!!
# yum -y update
echo "=================================================================="
echo "installing gcc and other basic stuff. (may take a moment)"
if yum list installed kernel-devel; then
  echo "skipping these installs"
else
  yum install -y gcc* kernel-devel epel-release > /dev/null
fi

# installing    mysql
echo "installing mysql-server"
yum -y install mysql-server > /dev/null

# mysql services -- [ON]
/etc/init.d/mysqld restart
chkconfig mysqld on
chkconfig --list mysqld

# set up sql user account
echo "Creating user..."
if [ ! -f /var/._dbinit183 ]; then
  sudo mysql --user=root mysql < /vagrant/etc/sql/createUser.sql
  echo "User 'unixadmin' has been created with password 'unixadmin'"
  sudo touch /var/._dbinit183
else
  echo "Database has been previously initialized."
  echo "Skipping configuration"
fi

# set up database and tables
echo "Init database and table. . ."
if [ ! -f /var/._dbpop183 ]; then
  sudo mysql < /vagrant/etc/sql/basicTableInit.sql
  echo "Init completed."
  echo "Populating database"
  sudo mysql < /vagrant/etc/sql/basicTablePopulate.sql
  echo "Populate completed."

  sudo touch /var/._dbpop183
else
  echo "Database has been previously populated"
  echo "skipping populating database"
fi
