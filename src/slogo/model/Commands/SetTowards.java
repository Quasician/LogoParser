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

   // double distance = distanceFormula(turtle.getX(), turtle.getY(), x, y);
    double angle = Math.atan((double)(turtle.getX() - x)/(turtle.getY() - y));
    int roundedAngle = (int)radiansToDegrees(angle);
    System.out.println("Old angle " + turtle.getDegree());
    int newAngle = turtle.getDegree() - roundedAngle;
    commandStack.pushOntoValueStack(roundedAngle);
    System.out.println("New angle " + newAngle);
  }


}
