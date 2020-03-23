package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * Left command
 */
public class Left extends TurtleCommand {

  private double degrees;

  public Left(String name) {
    super(name);
  }

  /**
   * Turns turtle left by the input degrees
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = Double.parseDouble(getParamList().get(0));
    rotate(-1, degrees);
    commandNode.setResult("" + degrees);
  }
}
