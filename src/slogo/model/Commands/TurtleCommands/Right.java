package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;
import slogo.model.Turtle;

public class Right extends TurtleCommand {

  private double degrees;

  public Right(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = Double.parseDouble(getParamList().get(0));
    for(Turtle activeTurtle: activatedTurtles) {
      rotateTurtle(activeTurtle.getId(),1, degrees);
      commandNode.setResult("" + degrees);
    }
  }
}
