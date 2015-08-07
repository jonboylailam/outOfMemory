#!/usr/bin/env bash

javac -d classes src/TestOutOfMemory.java

sleep 1

java -Xmx10m -cp classes -XX:OnOutOfMemoryError="./restart.sh" TestOutOfMemory
#java -Xmx10m -cp classes TestOutOfMemory

