#!/bin/bash

VAGRANTLOG=~/vagrant.log
TMPDIR=~/.MartinVagrantInstall/
touch $VAGRANTLOG

# update !!!!!
# yum -y update

yum install -y gcc* kernel-devel epel-release

# installing    mysql
yum -y install  mysql-server

# mysql services -- [ON]
/etc/init.d/mysqld restart
chkconkconfig mysqld on

# log apache service
chkconfig --list mysqld
