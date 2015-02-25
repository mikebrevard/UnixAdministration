
JAVA_HOME=/usr/java/jdk1.7.0_75
export JAVA_HOME
PATH=$JAVA_HOME/bin:$PATH
export PATH

sudo -E /usr/local/tomcat7/bin/shutdown.sh
sudo -E /usr/local/tomcat7/bin/startup.sh
