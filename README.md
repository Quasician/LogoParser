parser
====

This project implements a development environment that helps users write SLogo programs.

Names: Thomas Chemmanoor(tpc14), Michelle Tai(mrt36), Himanshu Jain(hkj4), Sanna Symer(sas182)


### Timeline

Start Date: 02/19/2020

Finish Date: 07/03/2020

Hours Spent: 280 hours (about 70 per person)

### Primary Roles
 
*  Thomas - Backend 
    *  Worked on parsing the commands and binding information 
*  Sanna - Backend 
    *  Worked on exception checking, XML files, commands parsing and binding information for the frontend
*  Michelle - Frontend
    *  Worked on setting up different views, Turtlegrid and adding different listeners 
*  Himanshu - Frontend
    *  Worked on making the toolbar, adding different listeners, and setting up the different views 

### Resources Used

*  Stack Overflow
*  TutorialsPoint
*  StackExchange
*  Java Code Geeks
*  Geeks for Geeks
*  Design Checkup Dev - refactoring
*  STUPID to SOLID

### Running the Program

Main class: Main.java

Data files needed: 

*  Property files for the different languages
*  Property files used by different classes 

Features implemented: 
*  Frontend

    *  Different Views for viewing the custom commands, variables, commads run, properties of the program, output 
    *  Undo the commands run, ability to select or deselect a turtle, moving the turtle with arrow keys, ability to change the image of turtle and ability to change the language
    *  Changing the colors and the background color
    *  New workspace 
    *  Help button that directs to the course page 
* Backend 

    *  All the commands
    *  Error checking for different exceptions 
    *  XML reading and the ability to write to a new XML file 
    *  Nested to commands and loop commands 
    *  Reading the different language files and changing the commands based on that 

* Binding every property that was being shared by the frontend and the backend 
* Listeners for different properties 


### Notes/Assumptions

Assumptions or Simplifications: 
*  Assuming the user is not writing comments
*  NO errors in the property files and they exist 
*  The user changes direction when the end of the canvas has been reached  

Interesting data files:
*  saveState.xml creates a pattern on the screen of the user 
* Property files for HistoryView 

Known Bugs:
*  Comments cannot be added 

Extra credit:
*  Clicking the turtle to make it actived and deactived
*  Showing more properties of the turtle
*  Arrow keys to move the turtle on the grid 
*  Undo the commands that have been run 
*  GIF that comes up if there is no internet connection and the Help button is clicked 
*  Terminal shows errors 

### Impressions
*  The project was really interesting but it took a lot of time to make the different commands and parsing them 
*  Binding the frontend and the backend was tough but it helped a lot 

