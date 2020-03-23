package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.TurtleCommands.TurtleCommand;
import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Class for Heading command
 */
public class Heading extends TurtleQuery {

  public Heading(String name) {
    super(name);
  }

  /**
   * Returns the degree the turtle is facing
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    commandNode.setResult("" + turtles.get(0).getDegree());
  }
}

