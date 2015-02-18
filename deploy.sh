#!/bin/bash

echo "Deploy Web Application"

echo "Clear webapp folder"
rm -rf vagrant/webapp/*

echo "Build war"
cd frontend/WebApplication/war
./build_war.sh

echo "Move project to webapp folder"
cd ../../../
mv frontend/WebApplication/war/CS183WebApplication.zip vagrant/webapp/

echo "Unzip project"
cd vagrant/webapp/
unzip CS183WebApplication.zip
rm -f CS183WebApplication.zip

# echo "Reload provision"
# cd ../
# vagrant reload --provision

