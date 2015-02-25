#!/bin/bash

VAGRANTLOG=~/vagrant.log
TMPDIR=~/.MartinVagrantInstall/
touch $VAGRANTLOG


# update !!!!!
# yum -y update
echo "insalling gcc and other basic stuff. (may take a moment)"
if yum list installed kernel-devel; then
  echo "skipping these installs"
else
  yum install -y gcc* kernel-devel epel-release > /dev/null
fi

# installing   apache
echo "installing httpd"
if yum list installed httpd; then
  echo "skipping these installs"
else
  yum -y install httpd > /dev/null
fi
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
if [ -f "/vagrant/etc/haproxy.cfg" ] then;
  echo "deleting old haproxy configuration"
  sudo rm -f /vagrant/etc/haproxy.cfg
fi
echo "replacing haproxy configuration"
sudo cp /vagrant/etc/haproxy.cfg /etc/haproxy/

# haproxy services -- [ON]
echo "starting haproxy"
service haproxy restart
chkconfig haproxy on
