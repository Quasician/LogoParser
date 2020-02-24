package slogo.model.Commands.TurtleCommands;

import slogo.model.Commands.Command;

public abstract class TurtleCommand extends Command {

  private static final double FACING_RIGHT = 90;
  private static final double FACING_UP = 360;
  private static final double FACING_LEFT = 270;
  private static final double FACING_DOWN = 180;
  protected int forward = 1;
  protected int backward = -1;

  public TurtleCommand(String name) {
    super(name);
  }

  protected double degreesToRadians(double angle) {
    return angle * Math.PI / 180;
  }

  protected void moveTurtleTo(double x, double y) {
//    turtle.setX(x);
//    turtle.setY(y);
    turtle.setCoordinate(x, y);
  }

  protected boolean facingTopRight(double angle) {
    return angle >= 0 && angle < FACING_RIGHT;
  }

  protected boolean facingTopLeft(double angle) {
    return angle >= FACING_LEFT && angle < FACING_UP;
  }

  protected boolean facingBottomRight(double angle) {
    return angle >= FACING_RIGHT && angle < FACING_DOWN;
  }

  protected boolean facingBottomLeft(double angle) {
    return angle >= FACING_DOWN && angle < FACING_LEFT;
  }

  protected double getAdjustedAngle(double angle) {
    if (facingTopRight(angle)) {
      return angle;
    } else if (facingTopLeft(angle)) {
      return FACING_UP - angle;
    } else if (facingBottomRight(angle)) {
      return FACING_DOWN - angle;
    } else {
      return angle - FACING_DOWN;
    }
  }

  protected int getXMultiplier(double angle) {
    if (angle >= 0 && angle < FACING_DOWN) {
      return 1;
    } else {
      return -1;
    }
  }

  protected int getYMultiplier(double angle) {
    if (angle >= 90 && angle < 270) {
      return -1;
    } else {
      return 1;
    }
  }

  protected void moveTurtle(int directionMultiplier, double distance) {
    double angle = getAdjustedAngle(turtle.getDegree());
    int xMultiplier = directionMultiplier * getXMultiplier(angle);
    int yMultiplier = directionMultiplier * getYMultiplier(angle);

    double angleToRadians = degreesToRadians(angle);
    double rightAngle = degreesToRadians(FACING_RIGHT);

    double newX = turtle.getX() + xMultiplier * (distance * Math.sin(angleToRadians));
    double newY = turtle.getY() + yMultiplier * (distance * Math.sin(rightAngle - angleToRadians));

//    turtle.setX(newX);
//    turtle.setY(newY);
    turtle.setCoordinate(newX, newY);
  }

  protected void rotateTurtle(int direction, double degrees) {
    double currentDegrees = turtle.getDegree();

    double newDegrees = 0;
    if (direction == 1) {
      newDegrees = (currentDegrees + degrees) % 360;
    } else {
      newDegrees = currentDegrees - degrees;
      if (newDegrees < 0) {
        newDegrees = -newDegrees;
        newDegrees = newDegrees % 360;
        newDegrees = 360 - newDegrees;
      }
    }

    if (newDegrees == 360) {
      newDegrees = 0;
    }

    turtle.setDegree(newDegrees);
  }

  protected double distanceFormula(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }

}
