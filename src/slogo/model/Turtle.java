package slogo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;


public class Turtle {

  private static final double DEGREE_LOWER_BOUND = 0;
  private static final double DEGREE_UPPER_BOUND = 360;
  //private int x, y, distanceTravelled, degree;
  //private boolean isPenDown, isVisible;

  private Coordinate coordinate;
  private ObjectProperty coordinates;
  private DoubleProperty x = new SimpleDoubleProperty();
  private DoubleProperty y = new SimpleDoubleProperty();
  private DoubleProperty distance = new SimpleDoubleProperty();
  private DoubleProperty angleFacing = new SimpleDoubleProperty();
  private BooleanProperty isPenDown = new SimpleBooleanProperty();
  private BooleanProperty isShowing = new SimpleBooleanProperty();


  public Turtle() {
    isShowing.set(true);
    isPenDown.set(true);
    coordinate = new Coordinate(0,0);
    coordinates = new SimpleObjectProperty(coordinate, "coordinate");
    coordinates.set(coordinate);
  }

  public ObjectProperty coordinatesProperty() {
    return coordinates;
  }

  public DoubleProperty xProperty() {
    return x;
  }

  public DoubleProperty yProperty() {
    return y;
  }

  public DoubleProperty distanceProperty() {
    return distance;
  }

  public DoubleProperty angleProperty() {
    return angleFacing;
  }

  public BooleanProperty isPenDownProperty() {
    return isPenDown;
  }

  public BooleanProperty isShowingProperty() {
    return isShowing;
  }

  public boolean isVisible() {
    return isShowing.get();
  }

  public boolean isPenDown() {
    return isPenDown.get();
  }

  public double getX() {
    //return x.get();
    return ((Coordinate)coordinates.get()).getX();
  }

  public double getY() {
   // return y.get();
    System.out.println("get y" + coordinate.getY());
    return ((Coordinate)coordinates.get()).getY();
  }

  // returns an int from 0 to 359
  public double getDegree() {
    return angleFacing.get();
  }

  protected double getDistance() {
    return distance.get();
  }

  public void setCoordinate(double newX, double newY) {
    coordinate.setX(newX);
    System.out.println("just set x " + coordinate.getX());
    coordinate.setY(newY);
    System.out.println("new x " + newX);
    System.out.println("new y " + newY);
    coordinates.set(new Coordinate(newX, newY));
    System.out.println("coordinate x " + ((Coordinate)coordinates.get()).getX());
  }

//  public void setX(double newX) {
//    x.set(newX);
//    System.out.println("new x " + newX);
//    coordinates.set(new Coordinate(newX, y.get()));
//  }
//
//  public void setY(double newY) {
//    y.set(newY);
//    coordinates.set(new Coordinate(x.get(), newY));
//  }

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
