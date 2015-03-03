#!/bin/bash

echo "Start with a solid upcon to start your day.."
./vagrant/upcon.sh

echo "Now deploy the newest web app"
./deploy.sh

echo "Now finally provision the world"
./vagrant/provisioncon.sh
