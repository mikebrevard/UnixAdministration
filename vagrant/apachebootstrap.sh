#!/bin/bash

VAGRANTLOG=~/vagrant.log
TMPDIR=~/.MartinVagrantInstall/
touch $VAGRANTLOG


# update !!!!!
# yum -y update
echo "insalling gcc and other basic stuff. (may take a moment)"
yum install -y gcc* kernel-devel epel-release > /dev/null

# installing   apache
echo "installing httpd"
yum -y install httpd > /dev/null

# apache services -- [ON]
#rm -rf /var/www/html                      # change webroot to vagrant folder
#ln -fs /vagrant /var/www/html             # ^
# TODO add a way to edit the line?
service httpd restart
chkconfig httpd on

# log apache service
echo "Apache httpd"
chkconfig --list httpd

#installing haproxy
sudo yum -y install haproxy

# haproxy services -- [ON]
service haproxy restart
chkconfig haproxy on
