#!/bin/bash

echo "Start with a solid upcon to start your day.."
cd vagrant/
./upcon.sh

echo "Now deploy the newest web app"
cd ../
./deploy.sh

echo "Now finally provision the world"
cd vagrant/
./provisioncon.sh
