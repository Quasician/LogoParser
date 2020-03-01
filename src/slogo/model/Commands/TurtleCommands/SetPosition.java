package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class SetPosition extends TurtleCommand {
  private double x;
  private double y;

  public SetPosition(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    x = Double.parseDouble(getParamList().get(0));
    y = Double.parseDouble(getParamList().get(1));

    for(Turtle activeTurtle: activatedTurtles) {
      double distance = distanceFormula(activeTurtle.getX(), activeTurtle.getY(), x, y);
      activeTurtle.updateCoordinates();
      moveTurtleTo(activeTurtle.getId(),x, y);
      commandNode.setResult("" + distance);
    }
  }
}
