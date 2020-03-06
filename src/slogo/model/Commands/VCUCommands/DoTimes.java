package slogo.model.Commands.VCUCommands;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.TreeNode;
import slogo.model.Turtle;

import java.util.Arrays;

public class DoTimes extends VCUCommand {
    public DoTimes(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        //System.out.println("Do this many times: " + getParamList().get(0).trim());
        String[] loopGuard = getParamList().get(0).trim().replaceFirst("\\[","").split("\\s+");
        System.out.println(loopGuard[0]);
        String[] commands = getParamList().get(1).trim().split("\\s+");
        String finalValue = "";
        for (int i = 1; i <= Double.parseDouble(loopGuard[2]); i++) {
            variables.put(loopGuard[1], "" + i);
            CommandParser miniparser = new CommandParser(turtles, variables, language);
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
        variables.remove(loopGuard[1]);
    }
}
