package slogo.model.Commands.TurtleCommands;

import slogo.model.TreeNode;

public class SetHeading extends TurtleCommand {

  private int degrees;

  public SetHeading(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    degrees = (int)Double.parseDouble(getParamList().get(0));
    int returnDegrees = degrees - turtle.getDegree(); //value that needs to be sent back
    turtle.setDegree(degrees % 360);
    commandNode.setData("" + returnDegrees);
  }
}
