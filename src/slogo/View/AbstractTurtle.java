package slogo.View;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import slogo.model.Coordinate;

public abstract class AbstractTurtle {
  protected static final double DEGREE_LOWER_BOUND = 0;
  protected static final double DEGREE_UPPER_BOUND = 360;
  protected ObjectProperty coordinates;
  protected ObjectProperty pastCoordinates;
  protected DoubleProperty distance = new SimpleDoubleProperty();
  protected IntegerProperty id = new SimpleIntegerProperty();
  protected DoubleProperty angleFacing = new SimpleDoubleProperty();
  protected BooleanProperty isPenDown = new SimpleBooleanProperty();
  protected BooleanProperty isShowing = new SimpleBooleanProperty();
  protected BooleanProperty clearScreenCalled = new SimpleBooleanProperty();
  protected BooleanProperty isActivated = new SimpleBooleanProperty();
  protected static final String COORDINATE = "coordinate";

  public AbstractTurtle() {
    isShowing.set(true);
    isPenDown.set(true);
    clearScreenCalled.set(false);
    Coordinate coordinate = new Coordinate(0,0);
    Coordinate pastCoordinate = new Coordinate(0,0);
    coordinates = new SimpleObjectProperty(coordinate, COORDINATE);
    coordinates.set(coordinate);
    pastCoordinates = new SimpleObjectProperty(pastCoordinate, COORDINATE);
    pastCoordinates.set(pastCoordinate);
    setActivated(true);
  }


  public ObjectProperty coordinatesProperty() {
    return coordinates;
  }

  public ObjectProperty pastCoordinatesProperty() {
    return pastCoordinates;
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
    return ((Coordinate)pastCoordinates.getBean()).getX();
  }

  public double getPastY() {
    return ((Coordinate)pastCoordinates.getBean()).getY();
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
    pastCoordinates.set(coordinates);
    coordinates.set(new Coordinate(newX, newY));
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
