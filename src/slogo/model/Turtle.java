package slogo.model;

public class Turtle {

  private int x, y, distanceTravelled, degree;
  private boolean isPenDown;

  public Turtle() {
    isPenDown = true;
  }

  protected int getX() {
    return x;
  }

  protected int getY() {
    return y;
  }

  // returns an int from 0 to 359
  protected int getDegree() {
    return degree;
  }

  protected int getDistanceTravelled() {
    return distanceTravelled;
  }

  protected int setX(int x) {
    return this.x = x;
  }

  protected int setY(int y) {
    return this.y = y;
  }

  protected int setDegree(int degree) {
    return this.degree = degree;
  }

  protected int setDistance(int distance) {
    return this.distanceTravelled = distance;
  }

  protected boolean penUp() {
    return isPenDown = false;
  }

  protected boolean penDown() {
    return isPenDown = true;
  }

}
