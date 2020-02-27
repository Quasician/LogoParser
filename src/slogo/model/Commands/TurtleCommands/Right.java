package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class Right extends TurtleCommand {

  private double degrees;

  public Right(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = Double.parseDouble(getParamList().get(0));
    rotateTurtle(1, degrees);
    commandNode.setResult("" + degrees);
  }
}
