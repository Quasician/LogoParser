## API

Thomas Chemmanoor tpc14
Himanshu Jain hkj4
Sanna Symer sas182
Michelle Tai mrt36

Should not be part of the API: private methods

Should be part of the API:

1. External - helps people in other modules
- methods in the visualization and configuration
- reader
- getSim
- FileInputException
- Splash and the getSplashScene() method

2. Internal
- Many of the methods within configuration

### Our API

External:
- method to get the simulation that is running
- update the properties
    - return them back to the internal API
- reading the XML file 
- visualization, front end
- setting up the simulation with inputted parameters

Internal:
- parse the XML file
- configuration
- carrying out rules of simulations
- updating the grid and cells

### How we would use it

External: 
Gets values from the internal API. The internal API does any necessary calculations 
and setting up of the new simulation type.
The grid gets updated within the internal API.
The internal API then passes this data on to the external API, which updates the visualization.

In terms of methods that will be used:
- read the XML file (using the Reader class)
- getSimulation method
- Splash

Internal:
Only the internal API should be modified. External should be able to handle any changes
and additions of new simulations.

Methods/classes that will be used:
- make a new class that extends Configuration (a special configuration class for the new
simulation type)
- make a new type of cell that extends Cell, for the new simulation
- will need to implement all of the abstract methods in this new cell class
    - including getType, updateCell, and others
    