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

# Install java
echo "installing java"
if [ ! -d /usr/java/jdk1.7.0_75 ]; then
  sudo wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/7u75-b13/jdk-7u75-linux-i586.tar.gz" >/dev/null 2>&1
  sudo tar -xvf jdk-7u75-linux-i586.tar.gz > /dev/null 2>&1
  sudo mkdir /usr/java/
  sudo mv jdk1.7.0_75 /usr/java/
  sudo rm -f jdk-7u75-linux-i586.tar.gz
  echo "java installed"
else
  echo "java already installed"
fi

echo "configuring java..."
cp /vagrant/etc/scripts/.bash_profile /home/vagrant/.bash_profile
source /home/vagrant/.bash_profile > /dev/null 2>&1
echo "finished configuring java."

# Install tomcat7
echo "installing tomcat"
if [ ! -d /usr/local/tomcat7 ]; then
  sudo wget http://supergsego.com/apache/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.tar.gz >/dev/null 2>&1
  sudo tar -xvf apache-tomcat-7.0.59.tar.gz > /dev/null 2>&1
  sudo mkdir /usr/local/tomcat7
  sudo mv apache-tomcat-7.0.59/* /usr/local/tomcat7/
  sudo rm -f apache-tomcat-7.0.59.tar.gz
else
    echo "tomcat already installed"
fi

echo "configuring tomcat"
yes | sudo yum install glibc.i686 > /dev/null 2>&1

echo "installing libs and deploying webapp"
sudo cp /vagrant/lib/* /usr/local/tomcat7/lib/ > /dev/null 2>&1
sudo cp /vagrant/webapp/CS183WebApplication.war /usr/local/tomcat7/webapps/

echo "restarting tomcat"
sudo -E /usr/local/tomcat7/bin/shutdown.sh > /dev/null 2>&1
sudo -E /usr/local/tomcat7/bin/startup.sh > /dev/null 2>&1

echo "setting local time"
rm -f /etc/localtime
sudo ln -s /usr/share/zoneinfo/America/Los_Angeles /etc/localtime > /dev/null 2>&1
echo "Today is:"
date
