#!/bin/bash

# Install httpd
if yum list installed httpd > /dev/null 2>&1; then
  echo "httpd already installed"
else
  echo "installing httpd"
  sudo yum -y install httpd > /dev/null 2>&1
  echo "httpd installed"
fi

echo "configuring httpd"
hostname=`hostname -s`
sudo sed -i "/#ServerName www.example.com:80/a ServerName $hostname:80" /etc/httpd/conf/httpd.conf
sudo sed -i 's/FollowSymLinks//g' /etc/httpd/conf/httpd.conf
echo "httpd configured"

echo "starting httpd"
sudo service httpd start > /dev/null 2>&1
if [ $? -eq 0 ]; then
  echo "httpd service started without error"
  sudo service httpd stop > /dev/null 2>&1
else
  echo "httpd failed to start"
  exit 5;
fi

chkconfig httpd on > /dev/null 2>&1
chkconfig --list httpd

#installing haproxy
if yum list installed haproxy > /dev/null 2>&1; then
  echo "haproxy already installed"
else
  echo "installing haproxy"
  sudo yum -y install haproxy > /dev/null 2>&1
  echo "haproxy installed"
fi

echo "configuring haproxy configuration"
sudo cp /vagrant/etc/haproxy/haproxy.cfg /etc/haproxy/haproxy.cfg
sudo cp /vagrant/etc/rsyslog.conf /etc/rsyslog.conf
sudo cp /vagrant/etc/rsyslog.d/haproxy.conf /etc/rsyslog.d/haproxy.conf

echo "starting haproxy"
service haproxy restart
chkconfig haproxy on
chkconfig haproxy list

echo "linking real time"
sudo rm -f /etc/localtime
sudo ln -s /usr/share/zoneinfo/America/Los_Angeles /etc/localtime
echo "Today is:"
date
