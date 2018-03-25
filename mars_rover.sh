#!/bin/bash
echo -e "Hi, please provide the file path: \c "
read file
if [ -e $file ] && [ -s $file ]; then
	mvn -q clean test exec:java -Dexec.args=$file
	STATUS=$?
	if [ $STATUS -eq 0 ]; then
		echo "Successful"
	else
		echo "Failed"
	fi
else
	echo "Invalid file input"
fi