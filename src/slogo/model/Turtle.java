package slogo.model;

public class Turtle {

  private int x, y, distanceTravelled, degree;
  private boolean isPenDown;

  public Turtle() {
    isPenDown = true;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  // returns an int from 0 to 359
  public int getDegree() {
    return degree;
  }

  protected int getDistanceTravelled() {
    return distanceTravelled;
  }

  public  int setX(int x) {
    return this.x = x;
  }

  public int setY(int y) {
    return this.y = y;
  }

  public int setDegree(int degree) {
    return this.degree = degree;
  }

  public int setDistance(int distance) {
    return this.distanceTravelled = distance;
  }

  public boolean penUp() {
    return isPenDown = false;
  }

  public boolean penDown() {
    return isPenDown = true;
  }

}
