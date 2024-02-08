:: To run: In cmd, type "build_and_run.bat"
:: This script will first change to the right directory, compile with the right classpath, and then run
cd src
set CLASSPATH=.;skeleton/opencsv-5.7.1.jar;skeleton/commons-lang3-3.12.0.jar
javac skeleton/Main.java
java skeleton.Main