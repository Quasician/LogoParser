package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * ClearScreen command
 */
public class ClearScreen extends TurtleCommand {

  public ClearScreen(String name) {
    super(name);
  }

  /**
   * Moves all turtles back to center and clears the screen of any lines
   * @param commandNode
   */
  @Override
  public void doCommand(TreeNode commandNode) {
    for(Turtle turtle: turtles) {
      double distanceTravelled = distanceFormula(turtle.getX(), turtle.getY(), 0, 0);
      moveTurtleTo(turtle.getId()-1,0, 0);
      commandNode.setResult("" + distanceTravelled);
      turtle.setDegree(0);

      turtle.clearScreenProperty().set(true);
      turtle.clearScreenProperty().set(false);
    }
  }
}
