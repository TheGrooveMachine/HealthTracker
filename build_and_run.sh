#!/usr/bin/env bash

# To run: type "./build_and_run.sh" in your terminal (you might need to make it executable first)
# This script will first change to the right directory, compile with the right classpath, and then run
cd src
javac -classpath ".:skeleton/opencsv-5.7.1.jar:skeleton/commons-lang3-3.12.0.jar" skeleton/Main.java
java -classpath ".:skeleton/opencsv-5.7.1.jar:skeleton/commons-lang3-3.12.0.jar" skeleton.Main