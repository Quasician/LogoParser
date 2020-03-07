package slogo.model.Commands.VCUCommands;

import slogo.model.CommandParser;
import slogo.model.TreeNode;

public class Repeat extends VCUCommand {
    public Repeat(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        System.out.println("Repeat this many times: " + getParamList().get(0));
        String[] commands = getParamList().get(1).split("\\s+");
        String finalValue = "";
        //String allCommands = getParamList().get(1).replaceFirst("\\[(.*?)\\]", "$1");
        String allCommands = getParamList().get(1).replaceFirst("\\[","").replace("\\]","");
        System.out.println("Repeat resut " + allCommands);
        CommandParser miniparser = new CommandParser(turtles,variables, language, customCommandStorage);
        Double limit = Double.parseDouble(miniparser.miniParse(getParamList().get(0).trim()));
        for(double i = 1; i<= limit;i++) {
            variables.put(":repcount", "" + i);
            finalValue = miniparser.miniParse(allCommands);
        }
        if(commands.length==0)
        {
            commandNode.setResult("0");
        }
        else
        {
            commandNode.setResult(finalValue);
        }
        variables.remove(":repcount");
    }
}
