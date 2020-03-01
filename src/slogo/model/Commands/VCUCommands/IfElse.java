package slogo.model.Commands.VCUCommands;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.TreeNode;
import slogo.model.VariableHashMap;

public class IfElse extends Command {
    public IfElse(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        //System.out.println("Do this many times: " + getParamList().get(0).trim());
        String finalValue = "";
        CommandParser miniparser = new CommandParser(activatedTurtles, language);
        if(Double.parseDouble(getParamList().get(0)) != 0)
        {
            finalValue = miniparser.miniParse(getParamList().get(1).trim().replaceFirst("\\[", ""));
            commandNode.setResult(finalValue);
        }
        else
        {
            finalValue = miniparser.miniParse(getParamList().get(2).trim().replaceFirst("\\[", ""));
            commandNode.setResult(finalValue);
        }
        if(finalValue.equals(""))
        {
            commandNode.setResult("0");
        }
    }
}
