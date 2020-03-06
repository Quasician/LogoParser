package slogo.model;

import javafx.beans.property.*;

public class Turtle {

  private static final double DEGREE_LOWER_BOUND = 0;
  private static final double DEGREE_UPPER_BOUND = 360;
  private ObjectProperty coordinates;
  private Coordinate pastCoordinates;
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
    pastCoordinates = new Coordinate(0,0);
    coordinates = new SimpleObjectProperty(coordinate, "coordinate");
    coordinates.set(coordinate);
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
    return ((Coordinate)coordinates.get()).getX();
  }

  public double getY() {
    return ((Coordinate)coordinates.get()).getY();
  }

  public double getPastX() {
    return pastCoordinates.getX();
  }

  public double getPastY() {
    return pastCoordinates.getY();
  }

  public int getId() {
    return id.get();
  }

  // returns an int from 0 to 359
  public double getDegree() {
    return angleFacing.get();
  }

  public void updateCoordinates() {
    Coordinate coords = ((Coordinate)coordinates.get());
    pastCoordinates.setX(coords.getX());
    pastCoordinates.setY(coords.getY());
  }

  protected double getDistance() {
    return distance.get();
  }

  public void setCoordinate(double newX, double newY) {
    updateCoordinates();
    coordinates.set(new Coordinate(newX, newY));
  }

  /**
   *
   * @param degree must be between 0 and 359, inclusive
   */
  public void setDegree(double degree) {
    while (degree >= DEGREE_UPPER_BOUND) {
      degree -= DEGREE_UPPER_BOUND;
    }
    while (degree < DEGREE_LOWER_BOUND) {
      degree = DEGREE_UPPER_BOUND + degree;
    }

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
