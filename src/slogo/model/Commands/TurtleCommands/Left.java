package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class Left extends TurtleCommand {

  private int degrees;

  public Left(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = (int)Double.parseDouble(getParamList().get(0));
    rotateTurtle(-1, degrees);
    commandNode.setData("" + degrees);
  }
}
