AUTHOR: ???
DATE: 2019/03/26
VERSION: 1.0

NAME
----
FoodTruckFinder


INTRODUCTION
-------------
This program prints out a list of food trucks that are open at the current
date/time, when the program is being run. This is a console application.
This programs uses the San Francisco government’s API.  


INSTALL
-------
Requirements: Apache Maven, Java 8 or newer (JRE or JDK)

Building and gathering dependencies is handled by Apache Maven.

These steps will launch a console window containing the program. 

1. Ensure the required softwares are already installed.
2. Open a terminal in the directory where this README was found. 
3. Enter the following: "mvn clean install"
4. Navigate to the "target" directory
5. Enter the following: "java -jar FoodTruckFinder-1.jar"


INCLUDED FILES
--------------
README.txt         <- You are here.
pom.xml            <- A config file for Maven
src                <- The directory containing the source/dev files for the program.


USAGE
-----
Once the program is running you will see an initial page of open food trucks.
A list of available commands will be printed at the bottom of the console window. 
The commands are 'N' for Next Page, 'L' for Last Page or 'E' for Exit.