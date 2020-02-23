package slogo.model.Commands.TurtleCommands;


import slogo.model.TreeNode;

/**
 * @author Sanna
 *
 * Forward command: FORWARD pixels
 * Moves the turtle forward by "pixels" spaces in the direction it currently faces,
 * and returns "pixels"
 *
 */
public class Forward extends TurtleCommand {

  private int distance;

  public Forward(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    distance = (int)Double.parseDouble(getParamList().get(0));
    commandNode.setData(distance + "");
    moveTurtle(forward, distance);
    //System.out.println("Result of Forward: "+commandNode.getData());
  }
}

