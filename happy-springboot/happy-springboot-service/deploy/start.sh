#!/bin/sh
 
APP_NAME=happy-springboot-api.jar
nohup /usr/local/java/bin/java -jar $APP_NAME >>logs/start.log 2>>logs/startError.log &

if test $(pgrep -f $APP_NAME|wc -l) -eq 0
then
   echo "start failed"
else
   echo "start successed"
fi
 
