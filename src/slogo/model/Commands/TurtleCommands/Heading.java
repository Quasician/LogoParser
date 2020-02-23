package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class Heading extends TurtleCommand {

  public Heading(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setData("" + turtle.getDegree());
  }
}

