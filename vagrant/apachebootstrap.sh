#!/bin/bash

# update !!!!!
# yum -y update
echo "=================================================================="
echo "insalling gcc and other basic stuff. (may take a moment)"
if yum list installed kernel-devel; then
  echo "skipping these installs"
else
  yum install -y gcc* kernel-devel epel-release > /dev/null
fi

# installing   apache
echo "=================================================================="
echo "installing httpd"
if yum list installed httpd; then
  echo "skipping these installs"
else
  yum -y install httpd > /dev/null
fi
# apache services -- [ON]
service httpd restart
chkconfig httpd on
echo "Apache httpd"
chkconfig --list httpd

#installing haproxy
echo "=================================================================="
sudo yum -y install haproxy
if [ -f "/vagrant/etc/haproxy.cfg" ] then;
  echo "deleting old haproxy configuration"
  sudo rm -f /vagrant/etc/haproxy.cfg
fi
echo "replacing haproxy configuration"
sudo cp /vagrant/etc/haproxy/haproxy.cfg /etc/haproxy/

# haproxy services -- [ON]
echo "starting haproxy"
service haproxy restart
chkconfig haproxy on
