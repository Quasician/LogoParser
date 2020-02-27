package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Back command: BACK pixels
 * Moves the turtle backward by "pixels" spaces in the direction it currently faces,
 * and returns "pixels"
 *
 */
public class Backward extends TurtleCommand {

  private double distance;

  public Backward(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    distance = Double.parseDouble(getParamList().get(0));
    commandNode.setResult(distance + "");
    moveTurtle(backward, distance);
  }
}
