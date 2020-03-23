package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * Right command
 */
public class Right extends TurtleCommand {

  private double degrees;

  public Right(String name) {
    super(name);
  }

  /**
   * turns the turtle right by the input degrees
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = Double.parseDouble(getParamList().get(0));
    rotate(1, degrees);
    commandNode.setResult("" + degrees);
  }

}

