#!/bin/bash

JAVA_HOME="/usr/java/jdk1.7.0_71"
JAR="./ejb-client-jar-with-dependencies.jar"

JAVA_OPTS="-XX:ThreadStackSize=256k -Djava.net.preferIPv4Stack=true"

for i in {1..200}; do
	exec $JAVA_HOME/bin/java -jar $JAR &
	echo "$i `sleep 0.5`"
done
