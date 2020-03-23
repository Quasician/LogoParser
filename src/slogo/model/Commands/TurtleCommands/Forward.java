package slogo.model.Commands.TurtleCommands;


import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * Forward command: FORWARD pixels
 * Moves the turtle forward by "pixels" spaces in the direction it currently faces,
 * and returns "pixels"
 *
 */
public class Forward extends TurtleCommand {

  private double distance;

  public Forward(String name) {
    super(name);
  }

  /**
   * Moves the turtle forward by the input distance parameter
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    distance = Double.parseDouble(getParamList().get(0));
    commandNode.setResult(distance + "");
    moveTurtles(forward, distance);
  }
}

