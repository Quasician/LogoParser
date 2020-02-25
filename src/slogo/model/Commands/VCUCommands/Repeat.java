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
        for(int i = 1; i<=Double.parseDouble(getParamList().get(0));i++) {
            VariableHashMap.addToMap(":repcount", "" + i);
            CommandParser miniparser = new CommandParser(turtle, language);
            System.out.println("Repeated Commands: " + getParamList().get(1));
            finalValue = miniparser.parseText(getParamList().get(1));
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
