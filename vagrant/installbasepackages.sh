#!/bin/bash

VAGRANTLOG=~/vagrant.log
TMPDIR=~/.MartinVagrantInstall/
touch $VAGRANTLOG


# update !!!!!
# yum -y update

# mount fix 1 ====== This sucked. ==============================================
# yum install kernel-devel-2.6.32-431.el6.x86_64  # MT: rebuild virtualbox addons
# sudo /etc/init.d/vboxadd setup                  # yum update rebuilds the kernel so we need to do this
# mount fix 2
# sudo ln -s /opt/VBoxGuestAdditions-4.3.10/lib/VBoxGuestAdditions /usr/lib/VBoxGuestAdditions
# mount fix 3 ========================================== 4 hours later... ======
yum install -y gcc* kernel-devel epel-release
# mount fix: I disabled mounting /vagrant in the VagrantFile

# installing   apache   mysql
yum -y install httpd    mysql-server

# apache services -- [ON]
#rm -rf /var/www/html                      # change webroot to vagrant folder
#ln -fs /vagrant /var/www/html             # ^
# TODO add a way to edit the line?
service httpd restart
chkconfig httpd on

# mysql services -- [ON]
/etc/init.d/mysqld restart
chkconkconfig mysqld on

# install Jmeter
mkdir $TMPDIR
wget http://www.carfab.com/apachesoftware//jmeter/binaries/apache-jmeter-2.12.tgz -O ~/.MartinVagrantInstall/apacheJmeter.tgz > /dev/null
tar -xzf ~/.MartinVagrantInstall/apacheJmeter.tgz -C ~/Desktop/
rm -rf $TMPDIR

/var/www/html/
echo "[ JMETER INSTALL ======================= ] "
ls /var/www/html
echo "[ ====================== LLATSNI RETEMJ ]"

# log apache service
echo >> $VAGRANTLOG
echo "Apache httpd" | tee $VAGRANTLOG
chkconfig --list httpd | tee $VAGRANTLOG
