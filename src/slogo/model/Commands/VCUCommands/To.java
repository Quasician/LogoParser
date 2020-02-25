package slogo.model.Commands.VCUCommands;
import slogo.model.CommandParamNumberHashMap;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.TreeNode;

public class To extends Command {
    public To(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        //System.out.println("Do this many times: " + getParamList().get(0).trim());
        String finalValue = "";
        String[] variables = getParamList().get(1).trim().split(" ");
        CommandParamNumberHashMap.addCommandParamNumber(getParamList().get(0),variables.length);
        CommandParser miniparser = new CommandParser(turtle, language);
        // also need to error check and make the result 0 if it does not work
        commandNode.setResult("1");
    }
}
