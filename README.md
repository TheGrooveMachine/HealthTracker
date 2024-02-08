# TERM-PROJECT: >ROYAL_DABBLERS<

An online application to be developed in Java 8=>**11** for the purpose of...  >**_replace-with-description_**<

##  Royal Dabblers Team & Roles

- Alex Leute, Configuration Manager

- Julia Sarun

- Griffin Moose, Design Coordinator

- Erich Snell

- Edward Sullivan

## Team Additional Information

- Meeting times
     Tuesdays: 6:45PM - 7:45PM (Virtual)
     Saturdays: 1:00PM - 2:00PM (Virtual)

- Google Drive link -> 

- Trello link -> https://trello.com/b/Ycz4hmgv/2221-swen-383-01-e-royal-dabblers

## Prerequisites

- Java 8=>**11**

- VS Code - as developer platform


## How to begin

1. Clone the repository and go to the root directory on your local machine.

2. Edit this README.md file

3. Follow instructions provided to "push" and, if need be, "merge" your results so that the final version exists on the server.

4. Your team will need to coordinate with **all** other teams to agree on a standard **directory structure** and **.gitignore** both of which you will reproduce and adhere to for the project. 



## License
MIT License

See LICENSE for details.


## How to compile and run
`cd` to [src](src),  
Add `skeleton/opencsv-5.7.1.jar` and `skeleton/commons-lang3-3.12.0.jar`(dependency for opencsv) to your CLASSPATH,  
then run:
```
javac skeleton/Main.java
java skeleton.Main
```
See also [build_and_run.sh](build_and_run.sh) (Mac/Linux) or [build_and_run.bat](build_and_run.bat) (Windows)

## Known Issues
- There are menu options for DailyLog, but there is no functionality (not saved to memory or to CSV). CSV reading and writing for DailyLog does work, but it's not connected.
- Lack of documentation in some of the files due to time constraints and prioritizing fuctionality (will be fixed).
- CLI not currently implementing the UI interface (will be fixed before adding a GUI).
