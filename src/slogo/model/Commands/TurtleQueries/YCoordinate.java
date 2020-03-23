package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Class for YCoordinate command
 */
public class YCoordinate extends TurtleQuery {

  public YCoordinate(String name) {
    super(name);
  }

  /**
   * Return the y coordinate of the turtle
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setResult("" + turtles.get(0).getY());
  }
}