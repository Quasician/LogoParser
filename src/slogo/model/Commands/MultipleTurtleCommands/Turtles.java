package slogo.model.Commands.MultipleTurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;
import slogo.model.TurtleList;

public class Turtles extends MultipleTurtleCommand{
    public Turtles(String name)
    {
        super(name);
    }
    @Override
    public void doCommand(TreeNode commandNode) {
        commandNode.setResult(turtles.size()+"");
    }
}
