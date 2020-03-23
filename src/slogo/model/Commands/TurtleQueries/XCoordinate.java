package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Class for XCoordinate command
 */
public class XCoordinate extends TurtleQuery {

  public XCoordinate(String name) {
    super(name);
  }

  /**
   * Return the x coordinate of the turtle
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setResult("" + turtles.get(0).getX());
  }
}
