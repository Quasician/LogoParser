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

    double angle = Math.atan((double)(turtle.getX() - x) / (turtle.getY() - y));

    int angleInDegrees = (int)radiansToDegrees(angle);
    System.out.println("\nAngle in degrees " + angleInDegrees);
    angleInDegrees = calculateAngle(angleInDegrees, x, y);

    System.out.println("\nAngle in degrees " + angleInDegrees);

    int angleDifference = turtle.getDegree() - angleInDegrees;
    if (Math.abs(angleInDegrees) > turtle.getDegree()) {
      angleDifference *= -1;
    }

    System.out.println("Calculated angle " + angleInDegrees);

    turtle.setDegree(angleInDegrees);
    commandStack.pushOntoValueStack(angleDifference);
    System.out.println("Angle difference " + angleDifference);
  }


}
