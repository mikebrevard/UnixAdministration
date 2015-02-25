#!/bin/bash

VAGRANTLOG=~/vagrant.log
TMPDIR=~/.MartinVagrantInstall/
#WEBAPP=/vagrant/webapp/
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

#if [ -d $WEBAPP ]; then
#  cp -r /vagrant/webapp/ /var/www/html/
#  echo "$WEBAPP successfully deployed" | tee $VAGRANTLOG
#else
#  echo "$WEBAPP not found" >> $VAGRANTLOG
#  >&2 echo "$WEBAPP not found"
#fi


# Install java
echo "=================================================================="

echo "installing java......................"
echo "wget java install"
if [ ! -d /usr/java/jdk1.7.0_75 ]; then
  cd /tmp/
  wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/7u75-b13/jdk-7u75-linux-i586.tar.gz" >/dev/null 2>&1
  tar -xvf jdk-7u75-linux-i586.tar.gz > /dev/null
  sudo mkdir /usr/java/
  sudo mv jdk1.7.0_75 /usr/java/
else
  echo "JAVA ALREADY INSTALLED"
fi
# sudo echo "export JAVA_HOME=/usr/java/jdk1.7.0_75/" > /usr/local/tomcat7/bin/setenv.sh
# sudo chmod +x /usr/local/tomcat7/bin/setenv.sh
echo "configuring java..."

if cat ~/.bash_profile | grep -q java; then
  echo "JAVA CONFIGURED ALREADY"
else
  cat /vagrant/etc/scripts/javabashconfig >> ~/.bash_profile
fi

# Install tomcat7
echo "=================================================================="
echo "installing tomcat7......................"
if [ ! -d /usr/local/tomcat7 ]; then
  cd /tmp/
  echo "wget tomcat install"
  wget http://supergsego.com/apache/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.tar.gz >/dev/null 2>&1
  tar -xvf apache-tomcat-7.0.59.tar.gz > /dev/null
  sudo mkdir /usr/local/tomcat7
  sudo mv /tmp/apache-tomcat-7.0.59/* /usr/local/tomcat7/
else
    echo "TOMCAT ALREADY INSTALLED"
fi

# IDK
echo "=================================================================="
echo "installing glibc for good reasons........."
yes | sudo yum install glibc.i686 > /dev/null

# Start tomcat
echo "=================================================================="
sudo /usr/local/tomcat7/bin/startup.sh

#move webapp
if [ -f "/vagrant/webapp/CS183WebApplication.war" ]; then
  # first remove the webapp if it's there
  if [ -d "/usr/local/tomcat7/webapps/CS183WebApplication/" ]; then
    echo "removing cs183webaapp dir"
    rm -rf /usr/local/tomcat7/webapps/CS183WebApplication/
  fi
  # remove the old war file
  echo "removing old war"
  sudo rm -f /usr/local/tomcat7/webapps/CS183WebApplication.war

  #copy the new war to the webapps directory
  echo "copying new war"
  sudo cp /vagrant/webapp/CS183WebApplication.war /usr/local/tomcat7/webapps/
fi
