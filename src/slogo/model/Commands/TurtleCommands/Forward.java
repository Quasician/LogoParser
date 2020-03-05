package slogo.model.Commands.TurtleCommands;


import slogo.model.TreeNode;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * Forward command: FORWARD pixels
 * Moves the turtle forward by "pixels" spaces in the direction it currently faces,
 * and returns "pixels"
 *
 */
public class Forward extends TurtleCommand {

  private double distance;

  public Forward(String name) {
    super(name);
  }

  @Override
  public void doCommand(TreeNode commandNode) {
    System.out.println("FORWARD DO COMMAND CALLED");
    distance = Double.parseDouble(getParamList().get(0));
    System.out.println(activatedTurtles.size());
    for(Turtle activeTurtle: activatedTurtles) {
      System.out.println("turtle y before move beg " + activeTurtle.getY());
      System.out.println("HERE, FORWARD");
      commandNode.setResult(distance + "");
      //activeTurtle.updateCoordinates();
      System.out.println("turtle y before move " + activeTurtle.getY());
      moveTurtle(activeTurtle.getId(), forward, distance);

    }
  }
}

