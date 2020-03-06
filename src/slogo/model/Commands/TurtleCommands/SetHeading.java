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
    double returnDegrees = setHeading(degrees);
    commandNode.setResult("" + returnDegrees);
  }
}
