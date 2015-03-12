#!/bin/bash

echo "Deploy Web Application"

echo "Clear webapp folder"
rm -rf vagrant/webapp/*

# echo "Build war"
# cd frontend/WebApplication/war
# ./build_war.sh

echo "Move project to webapp folder"
cp frontend/WebApplication/war/CS183WebApplication.war vagrant/webapp/

echo "Add important jars"
cp frontend/WebApplication/tomcat-j* vagrant/lib
cp frontend/WebApplication/war/WEB-INF/lib/mysql-connector-java-5.0.8-bin.jar vagrant/lib/

# echo "Provision web apps"
# cd vagrant/
# vagrant reload webapp1 webapp2 --provision

# echo "Unzip project"
# cd vagrant/webapp/
# unzip CS183WebApplication.zip
# rm -f CS183WebApplication.zip

# echo "Reload provision"
# cd ../
# vagrant reload --provision

