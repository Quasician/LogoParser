package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * SetPosition command
 * Assumption: if turtle is moved to a location out of bounds, it will appear on the edge of the
 * grid. Its coordinates, however, will be modified as if it is actually at the input location.
 */
public class SetPosition extends TurtleCommand {
  private double x;
  private double y;

  public SetPosition(String name) {
    super(name);
  }

  /**
   * Sets the position to the input coordinates
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    x = Double.parseDouble(getParamList().get(0));
    y = Double.parseDouble(getParamList().get(1));

    double distance = moveTurtlesTo(x, y);
    commandNode.setResult("" + distance);
  }
}
