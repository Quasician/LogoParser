package slogo.model.Commands.VCUCommands;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.TreeNode;
import slogo.model.VariableHashMap;

public class If extends Command {
    public If(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        //System.out.println("Do this many times: " + getParamList().get(0).trim());
        String finalValue = "";
        if(Double.parseDouble(getParamList().get(0)) != 0)
        {
            CommandParser miniparser = new CommandParser(turtle, language);
            finalValue = miniparser.parseText(getParamList().get(1));
            commandNode.setResult(finalValue);
        }
        else
        {
            commandNode.setResult("0");
        }
    }
}
