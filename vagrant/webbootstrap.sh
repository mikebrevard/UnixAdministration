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
#rm -rf /var/www/html                      # change webroot to vagrant folder
#ln -fs /vagrant /var/www/html             # ^
# TODO add a way to edit the line?
service httpd restart
chkconfig httpd on

# log apache service
echo >> $VAGRANTLOG
echo "Apache httpd" | tee $VAGRANTLOG
chkconfig --list httpd | tee $VAGRANTLOG

if [ -d $WEBAPP ]; then
  cp -r /vagrant/webapp/ /var/www/html/
  echo "$WEBAPP successfully deployed" | tee $VAGRANTLOG
else
  echo "$WEBAPP not found" >> $VAGRANTLOG
  >&2 echo "$WEBAPP not found"
fi


# Install java
cd /tmp/
wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/7u75-b13/jdk-7u75-linux-i586.tar.gz"
tar -xvf jdk-7u75-linux-i586.tar.gz
sudo mkdir /usr/java/
sudo mv jdk1.7.0_75 /usr/java/

sudo echo "export JAVA_HOME=/usr/java/jdk1.7.0_75/" > /usr/local/tomcat7/bin/setenv.sh
sudo chmod +x /usr/local/tomcat7/bin/setenv.sh

# Install tomcat7
cd /tmp/
wget http://supergsego.com/apache/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.tar.gz
tar -xvf apache-tomcat-7.0.59.tar.gz
sudo mkdir /usr/local/tomcat7
sudo mv /tmp/apache-tomcat-7.0.59/* /usr/local/tomcat7/

# IDK
yes | sudo yum install glibc.i686

# Start tomcat
sudo /usr/local/tomcat7/bin/startup.sh
