#!/bin/bash

cat haproxyconfig > haproxy.cfg
cat haproxy.cfg

serverno = 0

for ip in "$@"
do
    echo "  server webapp$serverno $ip:8080 check" >> haproxy.cfg
    $serverno = serverno + 1
done

cat haproxy.cfg

#uncomment this when you're ready...
#mv haproxy.cfg /etc/haproxy/haproxy.cfg

rm haproxy.cfg



