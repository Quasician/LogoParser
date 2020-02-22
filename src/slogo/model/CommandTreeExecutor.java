package slogo.model;

import slogo.model.Commands.Command;
import slogo.model.Commands.CommandFactoryInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommandTreeExecutor {
    private Pattern constantPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
    private Pattern commandPattern = Pattern.compile("[a-zA-Z_]+(\\?)?");
    private CommandFactoryInterface commandFactory;
    private Turtle turtle;

    public CommandTreeExecutor(CommandFactoryInterface factory, Turtle turtle)
    {
        this.turtle = turtle;
        commandFactory = factory;
    }

    public void executeTrees(List<TreeNode> elementNodes) {
        for(TreeNode element: elementNodes){
            executeSubTree(element);
        }
    }

    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }

    private void executeSubTree(TreeNode element){
        if(match(element.getData(),commandPattern)){
            ArrayList<TreeNode> children = element.getChildren();
            ArrayList<String> parameters = new ArrayList<>();
            for(TreeNode child: children) {
                executeSubTree(child);
                parameters.add(child.getData());
            }
            String commandClass = "slogo.model.Commands."+CommandTypeHashMap.getCommandType(element.getData())+"."+element.getData();
            System.out.println(commandClass);
            Command commandObject = commandFactory.createCommand(commandClass);
            for(String s : parameters)
            {
                System.out.println("Param of "+ element.getData() + ": "+ s);
            }
            commandObject.setParams(parameters);
            commandObject.setTurtle(turtle);
            commandObject.doCommand(element);
            //nd.setData(replacementValue);
        }
        // for variables later on
//        if(type.equals(VARIABLE_KEY)){
//            nd.setData(myVars.getVariable(nd.getData()));
//        }
    }
}
