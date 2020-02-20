


## Use cases
1. The command line in the visualization will send the command 'fd 50' as a String
to the command parser in the controller. 

The command parser first checks 
if the command is valid, and then translates the string into a Command object. 

The command then is put into the command history. 

After this, the command is run by the command parser. The command 
will update the location of the turtle, moving it forward 50 pixels.

2. The user clicks a button in the UI called change color, which 
shows a dropdown menu of which component they want to change.
The user selects the pen option. Then, a color palette comes up, showing
a grid of the possible color choices. The user selects a color. This color
gets passed to the drawing class, updating the current color of the pen line.



