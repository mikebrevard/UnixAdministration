#!/bin/bash

VAGRANTLOG=~/vagrant.log
TMPDIR=~/.MartinVagrantInstall/
touch $VAGRANTLOG

# update !!!!!
# yum -y update

yum install -y gcc* kernel-devel epel-release

# installing    mysql
yum -y install mysql-server

# mysql services -- [ON]
/etc/init.d/mysqld restart
chkconfig mysqld on

# log apache service
chkconfig --list mysqld

# set up sql
echo "Init database and table. . ."
sudo mysql < /vagrant/etc/sql/basicTableInit.sql
echo "Init completed."
echo "Populating database"
sudo mysql < /vagrant/etc/sql/basicTablePopulate.sql
echo "Populate completed."
