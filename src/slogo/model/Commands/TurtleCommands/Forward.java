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
      moveTurtle(activeTurtle.getId(), forward, distance);

    }
  }
}

