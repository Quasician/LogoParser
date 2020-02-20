## SLOGO APIS

Thomas Chemmanoor tpc14
Himanshu Jain hkj4
Sanna Symer sas182
Michelle Tai mrt36

Here are our plans for components:

Front end
* Need to have a class that handles the view/visualization,
knows all the components 
    * Command box, takes in user input (commands)
    * Need to have the turtle image
    * Displays errors when necessary
    * See past commands
* Needs to have user input abilities
    * Set the color of the background and the pen  
    * Have a drop down for the language the user wants to use
* All the text will have to change to other language
* A help page - tells the user commands and how to use them?
    
Interpreter
* A class that connects and interprets information between the front
end and back end
* Turns strings into commands that can be used by the back end
* Will handle exceptions

Back end
* Handling commands
* Knows where the turtle is and what direction it's facing
* Knows the history of commands

## Classes we need to have:

### Back end (model)
Turtle
- knows whether the pen is up or down
- knows its degree (where it is facing)
- knows its x and y coordinates
- should the turtle know its image?

Commands 
- maybe use inheritance hierarchy 
- how should we deal with commands
- need to have a map of string to command object
- have a method that carries out the command

Variable
- are they all treated as strings?
    - how to know what type the variable is?
    - should we restrict the types to strings, ints, and doubles?
- variables need to be stored

### Controller
Main

Command Parser

Error checking
- stores a list of valid commands
- has a method to display an error 

Value transfer class

History (of commands)

Variables 
- stores all the variables being used 
- stores their name and value

### View
Buttons in the visualization

Visualizer

Turtle grid (where the turtle can go)

Command line

Tool bar

Color palette 
- behavior: change color of line or background

Turtle image
- knows its image
- can set to a new image

Variable history
- shows what variables are being used

Command history sidebar

### Use Cases

1. The command line in the visualization will send the command 'fd 50' as a String
to the command parser in the controller. 

The command parser first checks 
if the command is valid, and then translates the string into a Command object. 

The command then is put into the command history. 

After this, the command is run by the command parser. The command 
will update the location of the turtle, moving it forward 50 pixels.

2. '50 fd' is passed as a string to the command parser. The command
parser checks for errors and sees that this is not a valid command.

The error checking class will then display an error showing that
the input command is invalid.

3. Split up the line into an array using space as a delimiter. 

4. Click a button on the view - a color palette will come up. The user
selects a color, and this color is passed to the turtle grid, which
will update its background color. 