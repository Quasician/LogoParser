package slogo.model.Commands.TurtleCommands;

import slogo.model.CommandException;
import slogo.model.TreeNode;

public class SetHeading extends TurtleCommand {

  private double degrees;

  public SetHeading(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = Double.parseDouble(getParamList().get(0));
    double returnDegrees = degrees - turtle.getDegree(); //value that needs to be sent back
    turtle.setDegree(degrees % 360);
    commandNode.setResult("" + returnDegrees);
  }
}
