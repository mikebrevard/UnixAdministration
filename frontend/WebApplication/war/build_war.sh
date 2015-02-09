#!/bin/bash
#!/usr/bin/expect

clear
echo "Build War: CS 183 Web Application: user: $1"

echo 'Remove old war if exists'
rm -f CS183WebApplication.war

echo 'Compress files'
zip -r CS183WebApplication.war favicon.ico webapplication/ WebApplication.html WebApplication.css WEB-INF/ -x 'logs/*.log'
