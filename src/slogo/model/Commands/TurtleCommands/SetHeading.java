package slogo.model.Commands.TurtleCommands;

import slogo.model.CommandException;
import slogo.model.TreeNode;
import slogo.model.Turtle;

public class SetHeading extends TurtleCommand {

  private double degrees;

  public SetHeading(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = Double.parseDouble(getParamList().get(0));
    for(Turtle activeTurtle: activatedTurtles) {
      double returnDegrees = degrees - activeTurtle.getDegree(); //value that needs to be sent back
      activeTurtle.setDegree(degrees % 360);
      commandNode.setResult("" + returnDegrees);
    }
  }
}
