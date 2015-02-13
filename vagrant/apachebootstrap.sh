#!/bin/bash

VAGRANTLOG=~/vagrant.log
TMPDIR=~/.MartinVagrantInstall/
touch $VAGRANTLOG


# update !!!!!
# yum -y update

yum install -y gcc* kernel-devel epel-release

# installing   apache
yum -y install httpd

# apache services -- [ON]
#rm -rf /var/www/html                      # change webroot to vagrant folder
#ln -fs /vagrant /var/www/html             # ^
# TODO add a way to edit the line?
service httpd restart
chkconfig httpd on

# log apache service
echo >> $VAGRANTLOG
echo "Apache httpd" | tee $VAGRANTLOG
chkconfig --list httpd | tee $VAGRANTLOG
