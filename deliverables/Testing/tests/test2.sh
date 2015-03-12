#!/bin/bash

echo "Test 1 time!"
time java -jar ../stresstest.jar 5 4 5 5 5 /vagrant/log/test2_1 2000
echo "Test 2 time!"
time java -jar ../stresstest.jar 5 8 5 5 5 /vagrant/log/test2_2 2000
echo "Test 3 time!"
time java -jar ../stresstest.jar 5 16 5 5 5 /vagrant/log/test2_3 2000
echo "Test 4 time!"
time java -jar ../stresstest.jar 5 32 5 5 5 /vagrant/log/test2_4 2000
echo "Test 5 time!"
time java -jar ../stresstest.jar 5 64 5 5 5 /vagrant/log/test2_5 2000
