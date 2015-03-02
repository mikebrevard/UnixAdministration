#!/bin/bash

mysqlimport --local --fields-terminated-by=, --columns='column1, column2, column3, column4, column5, column6' -u unixadmin -p -h '192.168.50.3' UnixTestDB Table1.csv
