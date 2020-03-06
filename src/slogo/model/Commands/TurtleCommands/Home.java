package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class Home extends TurtleCommand {

  public Home(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    for(Turtle activeTurtle: activatedTurtles) {
      double distanceTravelled = distanceFormula(activeTurtle.getX(), activeTurtle.getY(), 0, 0);
      moveTurtleTo(activeTurtle.getId()-1,0, 0);
      activeTurtle.setDegree(0);
      commandNode.setResult("" + distanceTravelled);
    }
  }
}
