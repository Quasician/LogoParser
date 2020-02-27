package slogo.model.Commands.VCUCommands;
import slogo.model.CommandParamNumberHashMap;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.CustomCommandMap;
import slogo.model.TreeNode;

public class MakeUserInstruction extends Command {
    public MakeUserInstruction(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        System.out.println("RAN TO COMMAND");
        String finalValue = "";
        String[] variables = getParamList().get(1).replaceAll("\\[(.*?)\\]", "$1").split("\\s+");
        CommandParamNumberHashMap.addCommandParamNumber(getParamList().get(0),variables.length);
        String allCommands = getParamList().get(2).trim();
        allCommands = allCommands.substring(allCommands.indexOf("[")+1,allCommands.lastIndexOf("]"));
        CustomCommandMap.addCustomCommand(getParamList().get(0), variables, allCommands);
        // also need to error check and make the result 0 if it does not work
        commandNode.setResult("1");
    }
}
