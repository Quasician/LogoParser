##SLOGO

1. When does parsing need to take place and what does it need 
to start properly?

When the user presses run - clicking the button would start
the parsing.

Needs error checking to see if there is actually a command
being input. 

2. What is the result of parsing and who receives it?
The string gets passed to the back end. The back end will convert 
the string to a command, meanwhile checking for errors (if it's
a valid command).


3. When are errors detected and how are they reported?
We need to have an error checking system to see if an
inputted command is valid and allowed.
The back end should check if the input command is valid.
Errors will tell the user that their command is invalid
and will prompt them to enter a new command.

4. What do commands know, when do they know it, and how do they get it?
One idea is that commands can be their own type of object.
Commands know what they need to know. 

Information about the turtle's location should probably be within
a turtle class.

Commands know the information when the command is entered on the
command line and run, being sent to the back end from the front end.

Commands get this information from the front end.

5. How is the GUI updated after a command has completed execution?

The turtle will be moved. The command is put from the command line
to the history of commands.
