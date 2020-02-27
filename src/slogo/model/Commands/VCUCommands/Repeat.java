package slogo.model.Commands.VCUCommands;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.TreeNode;
import slogo.model.VariableHashMap;

public class Repeat extends Command {
    public Repeat(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        System.out.println("Repeat this many times: " + getParamList().get(0));
        String[] commands = getParamList().get(1).split("\\s+");
        String finalValue = "";
        String allCommands = getParamList().get(1).replaceFirst("\\[(.*?)\\]", "$1");
        CommandParser miniparser = new CommandParser(turtle, language);
        for(double i = 1; i<=Double.parseDouble(getParamList().get(0).trim());i++) {
            System.out.println("COUNT FOR REPEAT" + allCommands);
            VariableHashMap.addToMap(":repcount", "" + i);
            System.out.println("Repeated Commands: " + getParamList().get(1));
            finalValue = miniparser.parseText(allCommands);
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
