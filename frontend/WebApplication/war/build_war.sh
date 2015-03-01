#!/bin/bash
#!/usr/bin/expect

clear
echo "Build War: CS 183 Web Application"

echo 'Remove old war if exists'
rm -f CS183WebApplication.war

echo 'Remove Test Files'
rm -f TestFiles/*

echo 'Compress files'
zip -r CS183WebApplication.war favicon.ico webapplication/ WebApplication.html WebApplication.css WEB-INF/ TestFiles/ -x 'logs/*.log'

# echo 'Move .zip'
# rm -rf ../../../vagrant/webapp/*
# cp CS183WebApplication.zip ../../../vagrant/webapp/
# cd ../../../vagrant/webapp/
# unzip CS183WebApplication.zip
