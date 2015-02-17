#!/bin/bash

VAGRANTLOG=~/vagrant.log
TMPDIR=~/.MartinVagrantInstall/
WEBAPP=/vagrant/webapp/
touch $VAGRANTLOG


# update !!!!!
# yum -y update

yum install -y gcc* kernel-devel epel-release

# installing   apache
yum -y install httpd

# apache services -- [ON]
rm -rf /var/www/html                      # change webroot to vagrant folder
ln -fs /vagrant /var/www/html             # ^ symbolic

# TODO add a way to edit the line?
service httpd restart
chkconfig httpd on

# log apache service
echo >> $VAGRANTLOG
echo "Apache httpd" | tee $VAGRANTLOG
chkconfig --list httpd | tee $VAGRANTLOG

#if [ -d $WEBAPP ]; then
#  cp -r /vagrant/webapp/ /var/www/html/
#  echo "$WEBAPP successfully deployed" | tee $VAGRANTLOG
#else
#  echo "$WEBAPP not found" >> $VAGRANTLOG
#  >&2 echo "$WEBAPP not found"
#fi
