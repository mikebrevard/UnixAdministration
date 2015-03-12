#!/bin/bash

echo "Test 1 time!"
time java -jar ../stresstest.jar 5 4 10 10 10 /vagrant/log/test5/test5_1 2000
echo "Test 2 time!"
time java -jar ../stresstest.jar 5 8 10 10 10 /vagrant/log/test5/test5_2 2000
echo "Test 3 time!"
time java -jar ../stresstest.jar 5 16 10 10 10 /vagrant/log/test5/test5_3 2000
echo "Test 4 time!"
time java -jar ../stresstest.jar 5 32 10 10 10 /vagrant/log/test5/test5_4 2000
echo "Test 5 time!"
time java -jar ../stresstest.jar 5 64 10 10 10 /vagrant/log/test5/test5_5 2000
