package slogo.model;

import javafx.beans.property.*;


public class Turtle {

  private static final double DEGREE_LOWER_BOUND = 0;
  private static final double DEGREE_UPPER_BOUND = 360;
  //private int x, y, distanceTravelled, degree;
  //private boolean isPenDown, isVisible;

  private ObjectProperty coordinates;
  private ObjectProperty pastCoordinates = new SimpleObjectProperty();
  private DoubleProperty distance = new SimpleDoubleProperty();
  private IntegerProperty id = new SimpleIntegerProperty();
  private DoubleProperty angleFacing = new SimpleDoubleProperty();
  private BooleanProperty isPenDown = new SimpleBooleanProperty();
  private BooleanProperty isShowing = new SimpleBooleanProperty();
  private BooleanProperty clearScreenCalled = new SimpleBooleanProperty();
  private BooleanProperty isActivated = new SimpleBooleanProperty();

  public Turtle() {
    isShowing.set(true);
    isPenDown.set(true);
    clearScreenCalled.set(false);
    Coordinate coordinate = new Coordinate(0,0);
    coordinates = new SimpleObjectProperty(coordinate, "coordinate");
    coordinates.set(coordinate);
    pastCoordinates.set(coordinate);
    setActivated(true);
  }

  public ObjectProperty coordinatesProperty() {
    return coordinates;
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

  public BooleanProperty clearScreenProperty() {
    return clearScreenCalled;
  }

  public BooleanProperty isActivatedProperty() {
    return isActivated;
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
    return ((Coordinate)coordinates.get()).getY();
  }

  public int getId() {
    return id.get();
  }

  // returns an int from 0 to 359
  public double getDegree() {
    return angleFacing.get();
  }

  public void updateCoordinates() {
    pastCoordinates.set(coordinates);
  }

  protected double getDistance() {
    return distance.get();
  }

  public void setCoordinate(double newX, double newY) {
    coordinates.set(new Coordinate(newX, newY));
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

  public void setActivated(boolean state) { this.isActivated.set(state); }

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

  public void setId(int id) {
    this.id.set(id);
  }
}
