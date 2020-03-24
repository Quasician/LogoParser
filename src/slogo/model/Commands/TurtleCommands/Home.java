package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * Home command
 */
public class Home extends TurtleCommand {

  public Home(String name) {
    super(name);
  }

  /**
   * Moves all turtles to 0,0 the center of the screen
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    double distance = moveTurtlesTo(0,0);
    setTowards(0, 0);
    commandNode.setResult("" + distance);
  }
}
