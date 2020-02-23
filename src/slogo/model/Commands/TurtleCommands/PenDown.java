package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class PenDown extends TurtleCommand {

  private static final String RETURN = "1";

  public PenDown(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    turtle.penDown();
    commandNode.setData(RETURN);
  }
}
