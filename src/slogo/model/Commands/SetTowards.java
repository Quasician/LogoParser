package slogo.model.Commands;

/**
 * Assumption: turtle will always move clockwise to the new angle
 * unfinished
 */
public class SetTowards extends TurtleCommand {

  private static final int NUM_PARAMS = 2;
  private int x;
  private int y;

  public SetTowards(String name) {
    super(NUM_PARAMS, name);
  }

  @Override
  public void doCommand() {
    y = values[0];
    x = values[1];

    double angle = Math.toDegrees(Math.atan2(x - turtle.getX(), y - turtle.getY()));

    if (angle < 0)
      angle += 360;

    commandStack.pushOntoValueStack(Math.abs(turtle.getDegree() - (int)angle));
    turtle.setDegree((int)angle);
  }

}
