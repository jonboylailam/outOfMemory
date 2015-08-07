#!/usr/bin/env bash


#populate the PID variable
PIDFILE=app.pid
if [ ! -f $PIDFILE ] ; then
    echo "$PIDFILE not found"
    exit 1
fi

PID=`cat $PIDFILE`

#check if the process is still running
ps cax | grep $PID > /dev/null
if [ "$status" == "0" ]; then
  echo "The Process is running, going to stop it"
  pkill -P $PID

  echo "Going to restart the test in a sec.."
  sleep 1
  java -Xmx10m -cp classes -XX:OnOutOfMemoryError="./restart.sh" TestOutOfMemory
else
    echo "The process is not running, looks like it died when the JVM ran out of memory."
  java -Xmx10m -cp classes -XX:OnOutOfMemoryError="./restart.sh" TestOutOfMemory
fi
