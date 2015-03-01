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

echo "configuring haproxy configuration"
sudo cp /vagrant/etc/haproxy/haproxy.cfg /etc/haproxy/haproxy.cfg
sudo cp /vagrant/etc/rsyslog.conf /etc/rsyslog.conf
sudo cp /vagrant/etc/rsyslog.d/haproxy.conf /etc/rsyslog.d/haproxy.conf


# haproxy services -- [ON]
echo "starting haproxy"
service haproxy restart
chkconfig haproxy on


echo "deleting all the time"
sudo rm -f /etc/localtime
echo "linking real time"
sudo ln -s /usr/share/zoneinfo/America/Los_Angeles /etc/localtime
echo "Today is:"
date
