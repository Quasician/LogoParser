package slogo.model.Commands.TurtleCommands;

import javafx.animation.AnimationTimer;
import slogo.model.Commands.Command;
import slogo.model.Turtle;

/**
 * @author Sanna
 *
 * This abstract class has all general behavior that is applicable for
 * multiple turtle commands, including relevant calculations
 * and methods that control turtle movement. This makes the individual command
 * classes very clean and simple, and eliminates duplication across commands.
 */
public abstract class TurtleCommand extends Command {

  private static final double FACING_RIGHT = 90;
  private static final double FACING_UP = 360;
  private static final double FACING_LEFT = 270;
  private static final double FACING_DOWN = 180;
  private static final int RIGHT = 1;
  protected int forward = 1;
  protected int backward = -1;

  /**
   * Constructor
   * @param name
   */
  public TurtleCommand(String name) {
    super(name);
  }

  protected double degreesToRadians(double angle) {
    return angle * Math.PI / 180;
  }

  protected void moveTurtleTo(int id, double x, double y) {
    turtles.get(id).setCoordinate(x, y);
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

  protected void changeIsShowing(boolean showing) {
    for (Turtle active : activatedTurtles) {
      active.changeShowing(showing);
    }
  }

  protected void changePen(boolean down) {
    for (Turtle active : activatedTurtles) {
      if (down)
        active.penDown();
      else
        active.penUp();
    }
  }

  protected double setTowards(double x, double y) {
    double returnValue = 0;
    for(Turtle activeTurtle: activatedTurtles) {
      double angle = Math.toDegrees(Math.atan2(x - activeTurtle.getX(), y - activeTurtle.getY()));
      if (angle < 0)
        angle += 360;
      returnValue = Math.abs(activeTurtle.getDegree() - angle);
      activeTurtle.setDegree(angle);
    }
    return returnValue;
  }

  protected double moveTurtlesTo(double x, double y) {
    double distance = 0;
    for (Turtle active : activatedTurtles) {
      distance = distanceFormula(active.getX(), active.getY(), x, y);
      moveTurtleTo(active.getId() - 1, x, y);
    }
    return distance;
  }

  protected void moveTurtles(int directionMultiplier, double distance) {
    for (Turtle active : activatedTurtles) {
      moveTurtle(active.getId() - 1, directionMultiplier, distance);
    }
  }

  protected void rotate(int direction, double degrees) {
    for (Turtle active : activatedTurtles) {
      rotateTurtle(active.getId() - 1, direction, degrees);
    }
  }

  protected double setHeading(double degrees) {
    double heading = 0;
    for (Turtle active : activatedTurtles) {
      heading = degrees - active.getDegree();
      active.setDegree(degrees % FACING_UP);
    }
    return heading;
  }

  protected void moveTurtle(int id, int directionMultiplier, double distance) {
    double angle = getAdjustedAngle(turtles.get(id).getDegree());
    int xMultiplier = directionMultiplier * getXMultiplier(turtles.get(id).getDegree());
    int yMultiplier = directionMultiplier * getYMultiplier(turtles.get(id).getDegree());

    double angleToRadians = degreesToRadians(angle);
    double rightAngle = degreesToRadians(FACING_RIGHT);

    double newX = turtles.get(id).getX() + xMultiplier * (distance * Math.sin(angleToRadians));
    double newY = turtles.get(id).getY() + yMultiplier * (distance * Math.sin(rightAngle - angleToRadians));

    turtles.get(id).setCoordinate(newX, newY);
  }

  protected void rotateTurtle(int id, int direction, double degrees) {
    double currentDegrees = turtles.get(id).getDegree();
    turtles.get(id).setDegree(currentDegrees + direction * degrees);
  }

  protected double distanceFormula(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }

}
