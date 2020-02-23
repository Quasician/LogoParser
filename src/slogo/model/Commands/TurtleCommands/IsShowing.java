package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class IsShowing extends TurtleCommand {

  private static final String IS_SHOWING = "1";
  private static final String NOT_SHOWING = "0";

  public IsShowing(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    if (turtle.isVisible()) {
      commandNode.setData(IS_SHOWING);
    } else {
      commandNode.setData(NOT_SHOWING);
    }



  }
}
