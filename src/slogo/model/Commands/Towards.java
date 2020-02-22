package slogo.model.Commands;

/**
 * Assumption: turtle will always move clockwise to the new angle
 * unfinished
 */
public class Towards extends TurtleCommand {

  private static final int NUM_PARAMS = 2;
  private int x;
  private int y;

  public Towards(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    x = values[0];
    y = values[1];

   // double distance = distanceFormula(turtle.getX(), turtle.getY(), x, y);


  }


}
