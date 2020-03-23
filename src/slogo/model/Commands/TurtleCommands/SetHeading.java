package slogo.model.Commands.TurtleCommands;

import slogo.model.CommandException;
import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * SetHeading command
 */
public class SetHeading extends TurtleCommand {

  private double degrees;

  public SetHeading(String name) {
    super(name);
  }

  /**
   * sets the turtle to face the input degrees
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = Double.parseDouble(getParamList().get(0));
    double returnDegrees = setHeading(degrees);
    commandNode.setResult("" + returnDegrees);
  }
}
