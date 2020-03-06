package slogo.model.Commands.MultipleTurtleCommands;

import slogo.model.CommandParser;
import slogo.model.TreeNode;
import slogo.model.Turtle;

import java.util.ArrayList;
import java.util.Arrays;

public class Ask extends MultipleTurtleCommand{
    public Ask(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        String list = getParamList().get(0).replaceFirst("\\[","").replaceFirst("\\]","");
        list = list.trim();
        String[] turtlesToRun = list.split("\\s+");
        System.out.println("TURTLES TO RUN: " +Arrays.toString(turtlesToRun));
        String[] commands = getParamList().get(1).split("\\s+");
        String allCommands = getParamList().get(1).replaceFirst("\\[(.*?)\\]", "$1");
        String finalValue = "";

        ArrayList<Boolean> currentTurtleStates = new ArrayList<>();
        for(Turtle turtle: turtles)
        {
            currentTurtleStates.add(turtle.isActivatedProperty().getValue());
        }
        for(String activatedTurtle:turtlesToRun) {
            CommandParser miniparser = new CommandParser(turtles, variables, language);
            miniparser.setTurtles(generate1ActiveTurtleList(Integer.parseInt(activatedTurtle)));
            finalValue = miniparser.miniParse(allCommands);
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
