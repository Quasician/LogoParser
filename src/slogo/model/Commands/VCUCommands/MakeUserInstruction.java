package slogo.model.Commands.VCUCommands;
import slogo.model.TreeNode;

public class MakeUserInstruction extends VCUCommand {
    public MakeUserInstruction(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        System.out.println("RAN TO COMMAND");
        String finalValue = "";
        String[] variables = getParamList().get(1).replaceFirst("\\[", "").replaceFirst("\\]", "").split("\\s+");
        customCommandStorage.addCustomCommandParamNumber(getParamList().get(0).trim(),variables.length);
        String allCommands = getParamList().get(2).trim();
        allCommands = allCommands.replaceFirst("\\[","");
        customCommandStorage.addCustomCommand(getParamList().get(0), variables, allCommands);
        // also need to error check and make the result 0 if it does not work
        commandNode.setResult("1");
    }
}
