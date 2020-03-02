package slogo.model.Commands.TurtleCommands;


import java.awt.Color;
import slogo.model.ColorOptions;
import slogo.model.Coordinate;
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
    for(Turtle activeTurtle: activatedTurtles) {
      commandNode.setResult(distance + "");
      //activeTurtle.updateCoordinates();
      System.out.println("Current coordinates " + activeTurtle.getX() + " " + activeTurtle.getY());
      moveTurtle(activeTurtle.getId(), forward, distance);
      System.out.println("New coordinates " + activeTurtle.getX() + " " + activeTurtle.getY());
      System.out.println("Old coordinates " + activeTurtle.getPastX() + " " + activeTurtle.getPastY());

    }
  }
}

