package slogo.model.Commands.VCUCommands;

import slogo.model.CommandParamNumberHashMap;
import slogo.model.CommandParser;
import slogo.model.Commands.Command;
import slogo.model.CustomCommandMap;
import slogo.model.TreeNode;

import java.util.Arrays;

public class CustomCommand extends Command {
    public CustomCommand(String name)
    {
        super(name);
    }

    @Override
    public void doCommand(TreeNode commandNode) {
        CommandParser miniParser = new CommandParser(turtle, language);
        String[] variables = CustomCommandMap.getVariables(commandNode.getName());
        String commands = CustomCommandMap.getCommands(commandNode.getName());
        //System.out.println("VARIABLE LENGTH: "  +variables[0]);
        for(int i = 1;i<variables.length;i++)
        {
            System.out.println(Arrays.toString(variables));
//            if(variables.length == 0 || variables[0].trim().equals(""))
//            {
//                continue;
//            }
            System.out.println("VARIABLE: " + i + " " + variables[i]);
            commands = commands.replaceAll(variables[i],getParamList().get(0));
        }
        miniParser.parseText(commands);
        //.replaceFirst("\\[","")
    }
}
