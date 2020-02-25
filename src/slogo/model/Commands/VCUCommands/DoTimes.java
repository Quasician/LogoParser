package slogo.model.Commands.VCUCommands;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.TreeNode;
import slogo.model.VariableHashMap;

public class DoTimes extends Command {
    public DoTimes(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        System.out.println("Do this many times: " + getParamList().get(0).trim());
        String[] loopGuard = getParamList().get(0).trim().split("\\s+");
        String[] commands = getParamList().get(1).trim().split("\\s+");
        String finalValue = "";
        for(int i = 1; i<=Double.parseDouble(loopGuard[1]);i++) {
            VariableHashMap.addToMap(loopGuard[0], "" + i);
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
