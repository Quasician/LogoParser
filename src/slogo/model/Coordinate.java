package slogo.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author Sanna
 *
 * This simple class was made to package the turtle's x and y coordinates
 * into one object.
 */
public class Coordinate extends SimpleObjectProperty {
  private double x;
  private double y;

  /**
   * Constructor for Coordinate
   * @param x
   * @param y
   */
  public Coordinate(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x coordinate
   * @return x
   */
  public double getX() {
    return x;
  }

  /**
   * Returns the y coordinate
   * @return y
   */
  public double getY() {
    return y;
  }

  /**
   * Sets the x coordinate
   * @param x the new x coordinate
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Sets the y coordinate
   * @param y the new y coordinate
   */
  public void setY(double y) {
    this.y = y;
  }
}
