

Back end (model)

Turtle
- knows whether the pen is up or down (view only has access to isPenUp to check for when need to draw lines)
	- penUp(), penDown()	
- knows its degree (where it is facing)
	- getDegree()
	- setDegree()
- knows its x and y coordinates
	- getX(), getY()
	- setX(), setY()
- knows its distance travelled
-setDistance()
- getDistanceTo(int x, int y) - gets the distance from the turtle to the point at (x,y)


Commands
- Inheritance hierarchy or interface
	- Math commands
	- Turtle commands
	- Turtle queries
	- Boolean commands
	- Variables
	- Control structures
		- knows the commands inside of it 
	- User defined command (to)
- knows commands that have been defined
- and what commands these commands store within
- doCommand() - carries out the command’s actions
- toString() - returns the name of the command as a string

Variable
We will start by assuming that the only variable type allowed is an integer (or double)
If there are multiple types of variables (strings, etc) then we will make an inheritance hierarchy for variables
Stores its String name
toString() method

Controller

Main - On its own
Start method
Create Visualizer
Launch method

Command Parser
- need to have a map of string to command object (so it knows what are valid commands)
- for TO call, puts the new command into the map
- the rest will be figured out after class

Error checking - Back End
- stores a list of valid commands
- has a method to display an error
- cases that will throw an error: unknown command, wrongly formatted command

History (of commands) - Front End
-some data structure that stores the commands
	- could be a list of command objects

Variables history - Front End
- stores all the variables being used (those that are possible to use in the future)
- stores their name and value
- some data structure that stores the variables
	- could be a list of variable objects
- getVariables() - for the variables history in the view

View

Buttons
Change properties like change color of the background or the line it traces 
These buttons will be shown/contained in the toolbar
Change the grid setting from a fixed grid size to toroidal 
Run button to execute the commands that have been typed in 
This button could be in the visual or the command line
Help button
Change language button
Sends the chosen language name to the back end
ComboBox that has all the language choices

Visualizer
Knows the view turtle / turtles (if more than one, creates a data structure of turtles)
Knows and sets up the turtle grid
Knows the command line
Knows the toolbar
Knows the history sidebars - variables and commands

Turtle grid (where the turtle can go)
The whole grid that would have the different objects on the screen
Knows a drawing class
Tells the drawing class to carry out tasks like drawing lines
Knows its dimensions (width and height)
Make it a constant that’s public static
Knows the coordinates at the edge (or what point is 0,0 - the center)
Knows whether it is normal boundaries or toroidal
Has access to the model turtle’s data through binding
updateTurtle()
Turtle always automatically updating with bindings
The front end turtle could be an image
What if there is more than one turtle?

Drawing 
Knows whether the pen is up or down
Knows the color of the line
draw(boolean isPenDown) method
If pen is down, it draws
drawLine(Coordinate a, Coordinate b) method
Draws a line from coord a to coord b
setColor(Color color) - sets the color of the pen
setBackgroundColor(Color color)

Command line
Could know the command parser
When the run button is clicked, calls the parseText (or something like this) method in the command parser
Command buttons
Run 
When pressed, the list of commands in the command line get sent to the command parser in the back end
Clear
All commands in the command line are cleared

Toolbar
Creates the buttons and sets their locations
Handles the button clicks?
Knows the color setting buttons: setPenColor, setBackgroundColor
When one of these buttons are clicked → shows color palette for user to choose color
Sets up a color palette
Handle color palette method
Knows a help button
Knows a Set Turtle Image button
Knows a change language button
Knows the Drawing class

Variable history
- shows what variables are being used
- know the variables history
- updates all the time (always checking it there’s new data)

Command history sidebar
- shows the commands that have been executed 
Always checking and update if there’s new commands
- has an play button on the side of the command so that the user could click it to execute it 
Introduction
This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). Discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).
Problem: We are trying to build an IDE for writing programs in Logo that users can use for learning Logo in a friendly and easy-to-navigate environment. Our simplified Logo environment will command a turtle on a per-instruction basis, which also helps the user understand simple coding syntax. 
Design goals: Our software needs to be flexible in terms of adding new commands to the IDE and new tools for changing the styling of the IDE. Adding new error checking algorithms should also be easy for the programmer. In addition, we want to design our SLogo with the goal of flexibility and extensibility in case more than 1 turtle could possibly be on the screen, more buttons need to be added in the toolbar, support more than the current number of languages, etc. 
Architecture (closed/open): Commands will be closed but have open extension. Buttons will also be closed to modification but open to extension (different functionalities of buttons). 
Program at a high level: Program is separated into a model (internal turtle and grid state) and view (see the state of simulation, past commands, and color options). We have decided not to include a controller, instead planning to use binding to transfer values automatically between the view and model. 
Overview
This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. Describe the four APIs you intend to create (their purpose with regards to the program's functionality, and how they collaborate with each other) focusing specifically on the behavior, not the internal state. Include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). To keep these classes as flexible as possible, your team must describe two different implementations (i.e., data structures, file formats, etc.) and then design your method signatures so they do not reveal the specifics of either implementation option. Discuss specific classes, methods, and data structures, but not individual lines of code.
Variables : list vs TreeSet because one could keep order of when it’s put in while the TreeSet would sort everything alphabetically so it won’t maintain the order 
Describe 4 APIs:
We plan to create the four basic APIs: external and internal APIs for both the front end and back end.
External View: The external front-end will have visualization methods that need to be accessed from components outside of the visualization. We have decided to use binding and not have a controller, so all the necessary data to be used in the model will be directly passed/updated from the back end. Because of this, we currently do not have any methods that must be called from outside of the view package, making our external view API empty.
	Methods: 
Internal View: The internal front end will consist of methods that only need to be accessed from within the visualization-related classes. These methods will change values that are internal to the view.
	Methods:
turtleGrid.changeColorPen(Color color) (just in case in different classes) 
This method will be in the TurtleGrid class, allowing the user to change pen color (this method will be called by the Toolbar)
turtleGrid.changeColorBackground(Color color)
This method will be in the TurtleGrid class, allowing the user to change background color  (this method will be called by the Toolbar)
drawing.draw(boolean isPenDown)
Idea: the turtle knows its current and next locations?	
How else could we combine the back and front end?
How does the front end know that we’re done?
Have a boolean that is bound?
public interface ViewInternal {
public void changeColorPen(Color color);
public void changeColorBackground(Color color);
public void draw(boolean isPenDown);
}
External Model: The external model contains methods that are needed by the front end, involving values or actions that cannot be determined through binding. 
	Methods:
commandParser.parseText(List<String> commands)
The command line (in the view) knows a command parser object. When the run button in command line is pressed, the command line calls this method, sending the list of strings to be converted into Command objects in the back end.
public interface ModelExternal {
public void parseText(List<String> commands);
}


Internal Model: The internal backend would be required for error checking and parsing the command that has been entered in the command line. This command would be checked in the list of commands and the meaning of that command would be passed on so that the other components could understand that command. 
	Methods: 
turtle.setX(int x)
turtle.getX()
turtle.setY(int y)
turtle.getY() 
turtle.setDegree(int degree)
turtle.getDegree() 
turtle.penUp()
turtle.penDown()
turtle.setDistance(int distance)
turtle.getDistance()
command.doCommand()
    public void setTurtleX(int x);
    public void setTurtleY(int y);
    public void getTurtleX();
    public void getTurtleY();
    public void getDegree();
    public void setDegree(int degree);
    public void penUp();
    public void penDown();
    public void isPenUp();
    public void isPenDown();
    public void setDistance(int distance);
    public void getDistance();
    public void doCommand();


Picture of how components are related:
Describe different implementations:
We discussed different data structures that could be used in the classes CommandHistory and VariableHistory. CommandHistory will be used to store a list of the commands that have been called by the user, in order. As the commands should be stored in order, we would most likely use an ArrayList. However, we could also use a LinkedHashSet to preserve insertion order. In discussing the data structure type for the CommandHistory, we also considered the potential need to store commands both as Command objects and as their string representations (what gets typed into the command line). Because of this, it would be helpful for CommandHistory to store a map from String to Command, so it is easier to pass this information between the back end and front end to be displayed in the visual. Some method signatures to hide the implementation of CommandHistory would be: 
getStringRepresentation()
addCommand(Command comm)
VariableHistory will store a list of the variables declared and in use by a user. For this group of variables, we probably do not want to allow duplicates, so it makes sense to use a set. If we want to keep the variables in alphabetical sorted order, we can use a TreeSet; otherwise, we can use a HashSet. Some method signatures to hide the implementation of VariableHistory would be: 
getValue(String variableName)
addVariable(String variableName, E value)
User Interface
This section describes how the user will interact with your program (keep it simple to start). Describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). Include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as an example). Also describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section should go into as much detail as necessary to cover all your team wants to say.
Appearance: The program would be designed like the other IDEs like Rstudio and the demo given on the page of CS 308. It would have a toolbar, the grid where the turtle would move around, the command line  underneath. Next to the command line will be the “Run” button, which runs the command(s) currently in the command line box, and a “Clear” button, which clears the command line. To the right of the grid will be a stack of panels: one would display the history of the commands, one would display the variables declared, and one would display the user-made commands. At the top, the user would be able to click on the different buttons on the toolbar to change some of the properties of the IDE, such as the color of the background, the color of the pen, the image view of the turtle, the language SLogo is displayed in, and a help button. The colors for the pen and the background can be chosen using a color palette picker, and the aspect of the IDE to which the color will be applied can be chosen using a dropdown menu. In short, when a user clicks the “Color Change” dropdown menu, the menu will show options of changing the pen color and changing the background color, a color picker will appear, and the user will choose whether to change the background or pen color and choose a color. 
Some erroneous situations that are reported to the user are bad input data, such running something that is not a command or doesn’t follow the command syntax. This will be reported to the user directly, much like how the actual Logo program displays a red message to the user and doesn’t run anything. We considered whether or not an “empty” command line that is run should count as an error or not, but we decided that it shouldn’t and instead just not do anything. We could easily add an error message if needed, but seeing that the actual Logo doesn’t respond to empty command input, we chose to model our design and decision after that too. In addition, if the user inputs a command in a different language than is currently selected, it will be treated as an invalid command and the user will get an alert.
Pictures:


Design Details
This section describes each API introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). Describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.
Each API in detail (features, resources, intended use, how it could be extended):
External front end: The external front end API will contain all aspects of the visualization that need to be accessed by other packages (back end and if we have it, a controller). As part of the view, we plan to have a CommandLine class that handles user inputs from the command line dialog box. It is necessary to pass the commands from the view to the model, so that the command’s action can be executed, so the command line will store a commandParser object and call the parseText method, passing in a list of strings.
Internal front end: The internal front end API will contain methods that only need to be accessed by classes within the view. These include a lot of methods related to the display, particularly methods that will be found in our Toolbar class. The Toolbar class will create and handle several buttons that allow the user to change certain aspects of the view, such as setting the pen and background color, choosing the language (which will update the language of all the text in the display, and also allow the user to type commands in a different language), and seeing the available commands (this would be a help button). These behaviors will probably each have their own method, which will actually update these values.
In addition to the toolbar, the front end also deals with the command line. Whenever the user presses the clear button, the command line needs to get cleared. This would likely involve a call from the Visualizer class to the CommandLine class, using a clear() method.
External back end: The external backend would be used for passing the values that would be required by the other components like the Front-end. The command parsing and error checking would have to be done before presenting it on the screen so to hide those functionalities from the user, we would be using the backend. This external API would be required to pass the things that we believe would not compromise the functioning of the program and would allow data privacy. 
We have the Command class that contains the doCommand method as it is the command that has been entered by the user and has to be presented on the screen. This information has to be passed on to the front-end. 
The turtle class would be required to move around the turtle image on the screen but the properties of the turtle are set by the backend so we need to pass the updated values to the front-end so that the required changes could be made. 
Internal back end: The internal backend would be made to protect the information that the user might not require to know for running his/her programs. The information would just be required for the updating the IDE or for setting the initial configuration of the IDE. These properties would be passed around in the back-end component only and would not be passed around to the other components. These are mostly setter methods as the user does not need to directly interact with the setter methods as this could be done by the backend itself when the command has been processed. 
The turtle methods that would be a part of this have been listed below as these are methods that set the properties of the turtle and could only be changed by entering a command. 
API as Code
Your APIs should be written as Java interfaces, types that cannot contain instance variables or private methods, in appropriate packages. These should be Java code files that compile and contain extensive comments to explain the purpose of each interface and each method within the interface (note this code can be generated directly from a UML diagram). Include any Exceptions you plan to throw because of errors that might occur within your methods. Note, this does not require that all of these types will remain as interfaces in the final implementation, just that the goal is for you to focus on each type's behavior and purpose. Also include the steps needed to complete the Use Cases below to help make your ideas more concrete.
Design Considerations
This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that the group discussed at length and describe at least one alternative in detail (including pros and cons from all sides of the discussion). Describe any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.
Issues:
How do we parse user-entered strings from the command line? This was somewhat discussed in class today so it was mostly resolved already.
How do we keep track of variables of varying types? Which types do we need to support -- Strings, doubles, ints: any others? Should we create different type hashmaps to store the different variables?
Should we have a controller, or not? We have decided (somewhat tentatively) to not have a controller for now.
Should we have two different turtles--a view turtle and a model turtle--or just the model turtle? If we have two, which one should store whether the pen is up or down? Should the pen up/down information instead be stored only in the view? If this info is stored in the view, the pen up/down command could affect only the front end and not go into the back end. In response to these questions, we decided, for now, to just have a model turtle, which will store a boolean for penDown. Then, the turtle grid class will store an image representation of the turtle, using binding to constantly update the location of the turtle.
How do we deal with TO commands (user defined commands)? Should they be a whole different type of object from our normal Command inheritance hierarchy? We are not sure about how to deal with this and plan to ask a TA.
Design decisions:
Our team has spent a lot of time thinking about how the turtle should be implemented, trying to answer questions such as: Should there be one back-end (model) turtle, which stores all of its location information as well as its image? Should we have two separate objects, a model turtle and a view turtle, both of which know their location, but only the view turtle knows the image?
Here is our design idea from when we considered including a MVC model: We believed that having two versions of the turtle, one in view and one in model could greatly separate our MVC design components. Since we assumed the model turtle does not need access to its own image and that the View should not have any contact from the model (they should both use controller to communicate), having two different turtles that are dependent on their respective packages makes the most sense.
As an alternative--the option we ended up going with--we could have one turtle within a model that keeps track of the image. 
Pros:
Easier to implement
Simpler
Cons:
MVC is less apparent
Our team started with making 3 different components that included the controller component, and we are still in the process of deciding whether or not to include the controller. Our reasoning for including the controller is that it would pass information between the view and the model, creating a separation between the front and back ends. This is because we believed it was poor design to pass certain components directly between the view and model, like the turtle object. However, after a discussion as a team, we started to reconsider this choice. We realized that just having a view and a model would enable us to take out some classes, including ViewTurtle and ValueTransfer. If the view already had access to a turtle object, we wouldn’t need to constantly send values to the view via the controller. Removing the controller could make it more readable and easy to transfer the data from one component to the other. In addition, removing the controller would enable us to try the new design concept called binding, which was introduced a bit today in class. We have decided to not have a controller, opting for using binding.
While trying to make this decision, we made a pros and cons list for both sides:
Yes controller: 
Pros:
Divides the code into clear components and would be easy to transfer values between the different components like Front-end and Back-end
Would make it much easier to divide the code as the methods that should be common for Back-end and Front-end could be put in the controller 
Cons:
More APIs to build as the controller component would have 2 different APIs as well
Data would have to be passed between more components 
No controller:
	Pros: 
Less classes that feel a bit redundant and would cause some duplication. 
ViewTurtle (only stores an image along with the other variables stored by the model turtle) 
ValueTransfer -  has the sole purpose of transferring values between the view and model.
We could try to use binding - could update a lot of values automatically
	Cons:
Mixing the front end and back end
Model depends on javaFX (but only beans and observable list)
Can’t have any type of controller classes?
Assumptions and dependencies:
Assumptions:
We talked a bit about how commands could be parsed from input Strings to Command objects, but in our design, we assume that this parsing “just happens.” We don’t explicitly plan for how this parsing will happen, as we anticipate learning how to do this in class soon.\
The only possible variable is an integer.
User cannot change the grid size. Could be added on as an extra feature.
The TO command must always end with END to signify that the user has completed their command declaration.
We are using binding to transfer values automatically between the back end and front end.
Dependencies:
In many cases, the view depends on values from the model in order to update what the user can see-- especially in the case of the turtle object.
Both the model and view depend on the common turtle values that will be unidirectionally bound between them (only model can change them).
The same is true for the language currently in use, but only the model can change it.
Team Responsibilities
This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.
Thomas and Sanna: backend (command parsing, making the turtle class, making the command & variable inheritance hierarchy)
Himanshu and Michelle: frontend (creating the display and components that involve user interactions)
High level plan: We will start with a most-basic implementation of the project. We’ll try to get command parsing working, as well as a basic display up and running. 


## Use cases

1. The command line in the visualization will send the command 'fd 50' as a String to the command parser in the back end.

The command parser first checks if the command is valid, and then translates the string into a Command object.

The command is run by the command parser. After this, the command is put into the command history. The command will update the location of the turtle, moving it forward 50 pixels.

2. The user clicks a button in the UI called change color, which shows a dropdown menu of which component they want to change. The user selects the pen option. Then, a color palette comes up, showing a grid of the possible color choices. The user selects a color. This color gets passed to the drawing class, updating the current color of the pen line.

Our use cases:

Himanshu:
Change the colour of the background- The background colour will be changed by using the toolbar presented in the IDE. The user would choose the option from the toolbar and a dropdown would show up with all the different buttons related to the colour changes. A palette would be displayed and the user would choose a colour. This calls the setBackgroundColor method in the drawing class. This would change the colour of the background of the IDE. 

Make the turtle invisible/visible- The command is entered by the user to make the turtle visible/invisible on the screen so this command is entered in the CommandLine class. This class calls the CommandParser class which parses the command and returns the property that has to be changed. The turtle’s property is changed to invisible and when the user enters the command to make the turtle visible, the property of the turtle changes to display its initial image. The property helps in removing or setting the image of the turtle in the TurtleGrid class. The old image is saved in a variable in the TurtleGrid class so that the old image could be set to make the turtle visible. 

Michelle:
Adding a variable: When the user declares a new variable, it is saved in the VariableHistory object. The VariableHistory object will have a map-like data structure to hold the variable name and value. Because we plan on making the VariableHistory bound to the frontend’s view of the variable history, the variable will also be added to the view’s variable history console. 
Change language: When the user clicks the combo box for languages, there will be a dropdown of language options. The user selects a language, and that language string will be passed to the textParser (through binding), and a new property file will be “swapped in” based on a mapping between languages and files within the parser itself. Now, the user can enter commands in the selected language, and if they enter something that is not in the command dictionary in the selected language, an exception is thrown and the user will be shown that “The command entered does not exist in the selected language.”
Sanna:
clearScreen command: The location of the turtle will be moved to 0,0. The current distance of the turtle will be returned using the turtle.getDistance() method, and then its distance will be set to 0 using turtle.setDistance(0).

In terms of how the front-end will be updated, the user needs to see all the current on-screen lines disappear. The turtle will keep track of whether or not the clearScreen command has been called as a boolean value. If clearScreen is true, all of the lines in the turtle grid will be taken out of the group.

The error checking class, while parsing a command, realizes that the command is invalid. Either it is not a command, or it is a command but has incorrect syntax or ordering.
	
Here is an example: fd 50 50. The command fd only has one value after it, so the error checking will find one too many arguments. Finding this, the error checking class will create and display an alert with an error message. So far, we will have two types of errors. One of these will be “invalid command,”

Thomas:
For command. A “for” Command object is created and the doCommands method will have a for loop inside it. The for Command object will have a list of commands to run for every step. So, each step of the for loop will just run the list of commands using the .doCommands method. 
If command. An “if” Command object is created along with an integer value and a list of commands. The doCommand would check if the integer value is not 0 and if it is nonzero, it would run the list of command objects using their respective doCommands. 


