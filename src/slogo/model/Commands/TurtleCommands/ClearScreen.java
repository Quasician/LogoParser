package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class ClearScreen extends TurtleCommand {

  public ClearScreen(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    //have to erase the turtle lines

    for(Turtle activeTurtle: turtles) {
      double distanceTravelled = distanceFormula(activeTurtle.getX(), activeTurtle.getY(), 0, 0);
      moveTurtleTo(activeTurtle.getId(),0, 0);
      commandNode.setResult("" + distanceTravelled);
      activeTurtle.setDegree(0);

      activeTurtle.clearScreenProperty().set(true);
      activeTurtle.clearScreenProperty().set(false);
    }
  }
}
