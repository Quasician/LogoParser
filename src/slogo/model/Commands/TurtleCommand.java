package slogo.model.Commands;

import slogo.model.Turtle;

public abstract class TurtleCommand extends Command {
  protected int facingRight = 90;
  protected int facingUp = 360;
  protected int facingLeft = 270;
  protected int facingDown = 180;
  protected int forward = 1;
  protected int backward = -1;

  public TurtleCommand(int params, String name) {
    super(params, name);
  }

  protected double angleToRadians(int angle) {
    return angle * Math.PI / 180;
  }

  protected void moveTurtleTo(int x, int y) {
    turtle.setX(x);
    turtle.setY(y);
  }

  protected boolean facingTopRight(int angle) {
    return angle >= 0 && angle < facingRight;
  }

  protected boolean facingTopLeft(int angle) {
    return angle >= facingLeft && angle < facingUp;
  }

  protected boolean facingBottomRight(int angle) {
    return angle >= facingRight && angle < facingDown;
  }

  protected boolean facingBottomLeft(int angle) {
    return angle >= facingDown && angle < facingLeft;
  }

  protected int getAdjustedAngle(int angle) {
    if (facingTopRight(angle))
      return angle;
    else if (facingTopLeft(angle))
      return facingUp - angle;
    else if (facingBottomRight(angle))
      return facingDown - angle;
    else
      return angle - facingDown;
  }

  protected int getXMultiplier(int angle) {
    if (angle >= 0 && angle < facingDown)
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

    double angleToRadians = angleToRadians(angle);
    double rightAngle = angleToRadians(facingRight);

    int newX = turtle.getX() + xMultiplier * (int) (distance * Math.sin(angleToRadians));
    int newY = turtle.getY() + yMultiplier * (int) (distance * Math.sin(rightAngle - angleToRadians));

    turtle.setX(newX);
    turtle.setY(newY);
  }

}
