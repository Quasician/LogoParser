package slogo.model.Commands.VCUCommands;

import slogo.model.CommandParamNumberHashMap;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.CustomCommandMap;
import slogo.model.TreeNode;

public class CustomCommand extends Command {
    public CustomCommand(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        CommandParser miniParser = new CommandParser(turtle, language);
        String[] variables = CustomCommandMap.getVariables(name);
        String commands = CustomCommandMap.getCommands(name);
        for(int i = 0;i<variables.length;i++)
        {
            commands = commands.replaceAll(variables[i],getParamList().get(i));
        }
        miniParser.parseText(commands);
    }
}
