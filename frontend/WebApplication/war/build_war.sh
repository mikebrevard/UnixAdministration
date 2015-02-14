#!/bin/bash
#!/usr/bin/expect

clear
echo "Build War: CS 183 Web Application: user: $1"

echo 'Remove old war if exists'
rm -f CS183WebApplication.zip

echo 'Compress files'
zip -r CS183WebApplication.zip favicon.ico webapplication/ WebApplication.html WebApplication.css WEB-INF/ -x 'logs/*.log'

echo 'Move .zip'
rm -rf ../../../vagrant/webapp/*
cp CS183WebApplication.zip ../../../vagrant/webapp/
cd ../../../vagrant/webapp/
unzip CS183WebApplication.zip
