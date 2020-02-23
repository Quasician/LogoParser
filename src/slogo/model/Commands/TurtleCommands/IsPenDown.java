package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class IsPenDown extends TurtleCommand {

  public IsPenDown(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    String returnValue = "0";
    if (turtle.isPenDown())
      returnValue = "1";
    commandNode.setData(returnValue);
  }
}

