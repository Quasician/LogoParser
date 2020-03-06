package slogo.model.Commands.MultipleTurtleCommands;

import slogo.model.CommandParser;
import slogo.model.TreeNode;
import slogo.model.Turtle;

import java.util.ArrayList;
import java.util.Arrays;

public class AskWith extends MultipleTurtleCommand{


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




    public AskWith(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {

        String condition = getParamList().get(0).replaceFirst("\\[(.*?)\\]", "$1");
        String conditionResult = "";

        String[] commands = getParamList().get(1).split("\\s+");
        String allCommands = getParamList().get(1).replaceFirst("\\[(.*?)\\]", "$1");
        String finalValue = "";

        ArrayList<Boolean> currentTurtleStates = new ArrayList<>();
        for(Turtle turtle: turtles)
        {
            currentTurtleStates.add(turtle.isActivatedProperty().getValue());
        }

        CommandParser miniparser = new CommandParser(activatedTurtles,activatedTurtles, language);
        for(Turtle turtle:turtles) {
            miniparser.setTurtles(generate1ActiveTurtleList(turtle.getId()-1));
            conditionResult = miniparser.miniParse(condition);
            System.out.println("CONDITION RESULT: "+ conditionResult);
            if(conditionResult.equals("1"))
            {
                //what if they input a tell command into the condition since it can return a number
                //miniparser.setTurtles(generate1ActiveTurtleList(Integer.parseInt(activatedTurtle)));
                finalValue = miniparser.miniParse(allCommands);
            }
        }

        int turtle = 0;
        for(Boolean bool:currentTurtleStates)
        {
            turtles.get(turtle).setActivated(bool);
            //System.out.println(turtles.get(turtle).getId() + "-> Activated: " + turtles.get(turtle).isActivatedProperty().getValue());
            turtle++;
        }

        if(commands.length==0)
        {
            commandNode.setResult("0");
        }
        else
        {
            commandNode.setResult(finalValue);
        }
    }
}
