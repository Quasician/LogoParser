package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class YCoordinate extends TurtleCommand {

  public YCoordinate(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setData("" + turtle.getY());
  }
}