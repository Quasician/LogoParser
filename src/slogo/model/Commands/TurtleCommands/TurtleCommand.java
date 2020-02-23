package slogo.model.Commands.TurtleCommands;

import slogo.model.Commands.Command;

public abstract class TurtleCommand extends Command {
  private static final int FACING_RIGHT = 90;
  private static final int FACING_UP = 360;
  private static final int FACING_LEFT = 270;
  private static final int FACING_DOWN = 180;
  protected int forward = 1;
  protected int backward = -1;

  public TurtleCommand(String name) {
    super(name);
  }

  protected double degreesToRadians(int angle) {
    return angle * Math.PI / 180;
  }

  protected void moveTurtleTo(int x, int y) {
    turtle.setX(x);
    turtle.setY(y);
  }

  protected boolean facingTopRight(int angle) {
    return angle >= 0 && angle < FACING_RIGHT;
  }

  protected boolean facingTopLeft(int angle) {
    return angle >= FACING_LEFT && angle < FACING_UP;
  }

  protected boolean facingBottomRight(int angle) {
    return angle >= FACING_RIGHT && angle < FACING_DOWN;
  }

  protected boolean facingBottomLeft(int angle) {
    return angle >= FACING_DOWN && angle < FACING_LEFT;
  }

  protected int getAdjustedAngle(int angle) {
    if (facingTopRight(angle))
      return angle;
    else if (facingTopLeft(angle))
      return FACING_UP - angle;
    else if (facingBottomRight(angle))
      return FACING_DOWN - angle;
    else
      return angle - FACING_DOWN;
  }

  protected int getXMultiplier(int angle) {
    if (angle >= 0 && angle < FACING_DOWN)
      return 1;
    else
      return -1;
  }

  protected int getYMultiplier(int angle) {
    if (angle >= 90 && angle < 270)
      return -1;
    else
      return 1;
  }

  protected void moveTurtle(int directionMultiplier, int distance) {
    int angle = getAdjustedAngle(turtle.getDegree());
    int xMultiplier = directionMultiplier * getXMultiplier(angle);
    int yMultiplier = directionMultiplier * getYMultiplier(angle);

    double angleToRadians = degreesToRadians(angle);
    double rightAngle = degreesToRadians(FACING_RIGHT);

    int newX = turtle.getX() + xMultiplier * (int) (distance * Math.sin(angleToRadians));
    int newY = turtle.getY() + yMultiplier * (int) (distance * Math.sin(rightAngle - angleToRadians));

    turtle.setX(newX);
    turtle.setY(newY);
  }

  protected void rotateTurtle(int direction, int degrees) {
    int currentDegrees = turtle.getDegree();

    int newDegrees = 0;
    if (direction == 1)
      newDegrees = (currentDegrees + degrees) % 360;
    else {
      newDegrees = currentDegrees - degrees;
      if (newDegrees < 0) {
        newDegrees = -newDegrees;
        newDegrees = newDegrees % 360;
        newDegrees = 360 - newDegrees;
      }
    }

    if (newDegrees == 360)
      newDegrees = 0;

    turtle.setDegree(newDegrees);
  }

  protected double distanceFormula(int x1, int y1, int x2, int y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }

}
