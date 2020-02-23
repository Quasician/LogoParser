package slogo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Turtle {

  private static final double DEGREE_LOWER_BOUND = 0;
  private static final double DEGREE_UPPER_BOUND = 360;
  //private int x, y, distanceTravelled, degree;
  //private boolean isPenDown, isVisible;

  private DoubleProperty x = new SimpleDoubleProperty();
  private DoubleProperty y = new SimpleDoubleProperty();
  private DoubleProperty distance = new SimpleDoubleProperty();
  private DoubleProperty angleFacing = new SimpleDoubleProperty();
  private BooleanProperty isPenDown = new SimpleBooleanProperty();
  private BooleanProperty isShowing = new SimpleBooleanProperty();


  public Turtle() {
    isShowing.set(true);
    isPenDown.set(true);
//    isPenDown = true;
//    isVisible = true;
  }

  public boolean isVisible() {
    return isShowing.get();
  }

  public boolean isPenDown() {
    return isPenDown.get();
  }

  public double getX() {
    return x.get();
  }

  public double getY() {
    return y.get();
  }

  // returns an int from 0 to 359
  public double getDegree() {
    return angleFacing.get();
  }

  protected double getDistance() {
    return distance.get();
  }

  public void setX(double newX) {
    x.set(newX);
  }

  public void setY(double newY) {
    y.set(newY);
  }

  /**
   *
   * @param degree must be between 0 and 359, inclusive
   */
  public void setDegree(double degree) {
    if (degree < DEGREE_LOWER_BOUND || degree >= DEGREE_UPPER_BOUND)
      throw new ArithmeticException("Degree not in valid range");
    angleFacing.set(degree);
  }

  public void setDistance(double distance) {
     this.distance.set(distance);
  }

  public void penUp() {
    isPenDown.set(false);
  }

  public void penDown() {
    isPenDown.set(true);
  }

  public void show() {
    isShowing.set(true);
  }

  public void hide() {
    isShowing.set(false);
  }

}
