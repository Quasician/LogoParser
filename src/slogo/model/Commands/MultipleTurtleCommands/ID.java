package slogo.model.Commands.MultipleTurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class ID extends MultipleTurtleCommand {
    public ID(String name) { super(name);}

    @Override
    public void doCommand(TreeNode commandNode) {
        if(activatedTurtles.size()==0)
        {
            commandNode.setResult(0+"");
            return;
        }
        commandNode.setResult(activatedTurtles.size()+"");
    }
}
