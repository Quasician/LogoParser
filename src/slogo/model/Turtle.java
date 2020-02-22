package slogo.model;

public class Turtle {

  private static final int DEGREE_LOWER_BOUND = 0;
  private static final int DEGREE_UPPER_BOUND = 360;
  private int x, y, distanceTravelled, degree;
  private boolean isPenDown, isVisible;

  public Turtle() {
    isPenDown = true;
    isVisible = true;
  }

  public boolean isVisible() {
    return isVisible;
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

  /**
   *
   * @param degree must be between 0 and 359, inclusive
   * @return
   */
  public int setDegree(int degree) {
    if (degree < DEGREE_LOWER_BOUND || degree > DEGREE_UPPER_BOUND)
      throw new ArithmeticException("Degree not in valid range");
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

  public void show() {
    isVisible = true;
  }

  public void hide() {
    isVisible = false;
  }

}
