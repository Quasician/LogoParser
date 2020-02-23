package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class XCoordinate extends TurtleCommand {

  public XCoordinate(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setData("" + turtle.getX());
  }
}
