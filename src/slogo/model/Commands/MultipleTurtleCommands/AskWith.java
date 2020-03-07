package slogo.model.Commands.MultipleTurtleCommands;

import java.util.List;
import slogo.model.CommandParser;
import slogo.model.TreeNode;
import slogo.model.Turtle;

import java.util.ArrayList;
import java.util.Arrays;

public class AskWith extends MultipleTurtleCommand {

  //Maybe don't have to do this
  // use condition to generate new list then essentially run the ask command.

        
    /*For the askWith command and all turtle commands

    Get only the activated turtles through a function that has to be written in treeExecutor

    Set the current turtle for the command from the command object

    Edit the Command class to only store the current turtle

    Each active turtle will change the result of the same command node, thus the last value will be stored
    as the result in the end.

    Turtles command -> set the result( result + 1) for each turtle
       Test this works.

    Tell -> check to see if in turtle string
        if not && inside -> deactivate
        if inside -> check if smaller than current turtle list
                            if inside current turtle list -> activate
                            if outside current turtle list -> create new turtles -> deactivate all of them
                                                              create last turtle -> activate the last one

    Ask -> do the command if the turtle is contained inside the turtle string
        DO NOT CHANGE values to


    */


  public AskWith(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    String condition = getCondition();
    String conditionResult = "";
    String[] commands = getCommandsArray();
    String allCommands = getAllCommands();
    String finalValue = "";

    List<Boolean> currentTurtleStates = getCurrentTurtleStates();

    CommandParser miniparser = new CommandParser(activatedTurtles, variables, language);
    for (Turtle turtle : turtles) {
      miniparser.setTurtles(generate1ActiveTurtleList(turtle.getId() - 1));
      conditionResult = miniparser.miniParse(condition);
      if (conditionResult.equals("1")) {
        finalValue = miniparser.miniParse(allCommands);
      }
    }

    setResult(currentTurtleStates, commandNode, commands, finalValue);
  }
}
