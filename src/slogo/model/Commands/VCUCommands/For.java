package slogo.model.Commands.VCUCommands;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.TreeNode;
import slogo.model.VariableHashMap;

public class For extends VCUCommand {
    public For(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        //System.out.println("Loops this many times: " + getParamList().get(0).trim());

        String[] loopGuard = getParamList().get(0).trim().split("\\s+");
        String[] commands = getParamList().get(1).trim().split("\\s+");
        String finalValue = "";
        for(double i = Double.parseDouble(loopGuard[2]); i<=Double.parseDouble(loopGuard[3]);i+=Double.parseDouble(loopGuard[4])) {
            VariableHashMap.addToMap(loopGuard[1], "" + i);
            CommandParser miniparser = new CommandParser(activatedTurtles, activatedTurtles, language);
            System.out.println("Repeated Commands: " + getParamList().get(1));
            finalValue = miniparser.miniParse(getParamList().get(1).trim().replaceFirst("\\[", ""));
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
