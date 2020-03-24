package slogo.model;

import java.util.ResourceBundle;
import javafx.beans.property.*;

/**
 * @author Sanna, Thomas
 *
 * This is the object representing a turtle and all its necessary properties,
 * such as its location, angle facing, showing/not showing, and activated/not activated.
 */
public class Turtle {

  private static final double DEGREE_LOWER_BOUND = 0;
  private static final double DEGREE_UPPER_BOUND = 360;
  private ObjectProperty coordinates;
  private ObjectProperty pastCoordinates;
  private DoubleProperty distance = new SimpleDoubleProperty();
  private IntegerProperty id = new SimpleIntegerProperty();
  private DoubleProperty angleFacing = new SimpleDoubleProperty();
  private BooleanProperty isPenDown = new SimpleBooleanProperty();
  private BooleanProperty isShowing = new SimpleBooleanProperty();
  private BooleanProperty clearScreenCalled = new SimpleBooleanProperty();
  private BooleanProperty isActivated = new SimpleBooleanProperty();
  private ResourceBundle errors = ResourceBundle.getBundle("resources.ErrorMessages");

  /**
   * Turtle constructor
   */
  public Turtle() {
    isShowing.set(true);
    isPenDown.set(true);
    clearScreenCalled.set(false);
    Coordinate coordinate = new Coordinate(0,0);
    Coordinate past = new Coordinate(0,0);
    pastCoordinates = new SimpleObjectProperty(past, "past");
    pastCoordinates.set(past);
    coordinates = new SimpleObjectProperty(coordinate, "coordinate");
    coordinates.set(coordinate);
    setActivated(true);
  }

  /**
   * @return coordinates property
   */
  public ObjectProperty coordinatesProperty() {
    return coordinates;
  }

  /**
   * @return past coordinates property
   */
  public ObjectProperty pastCoordinatesProperty() {
    return pastCoordinates;
  }

  /**
   * @return id property, the id of the turtle
   */
  public IntegerProperty idProperty() {
    return id;
  }

  /**
   * @return the distance property, the distance travelled
   */
  public DoubleProperty distanceProperty() {
    return distance;
  }

  /**
   * @return angle property, the angle the turtle is facing
   */
  public DoubleProperty angleProperty() {
    return angleFacing;
  }

  /**
   * @return pen down property
   */
  public BooleanProperty isPenDownProperty() {
    return isPenDown;
  }

  /**
   * @return showing property
   */
  public BooleanProperty isShowingProperty() {
    return isShowing;
  }

  /**
   * @return clear screen property, is true if clear screen is called
   */
  public BooleanProperty clearScreenProperty() {
    return clearScreenCalled;
  }

  /**
   * @return isActivated property, true if the turtle is activated
   */
  public BooleanProperty isActivatedProperty() {
    return isActivated;
  }

  /**
   * @return true if visible
   */
  public boolean isVisible() {
    return isShowing.get();
  }

  /**
   * @return true if pen is down, false if up
   */
  public boolean isPenDown() {
    return isPenDown.get();
  }

  /**
   * @return x coordinate
   */
  public double getX() {
    return ((Coordinate)coordinates.get()).getX();
  }

  /**
   * @return y coordinate
   */
  public double getY() {
    return ((Coordinate)coordinates.get()).getY();
  }

  /**
   * @return past x coordinate
   */
  public double getPastX() {
    return ((Coordinate)pastCoordinates.get()).getX();
  }

  /**
   * @return past y coordinate
   */
  public double getPastY() {
    return ((Coordinate)pastCoordinates.get()).getY();
  }

  /**
   * @return id
   */
  public int getId() {
    return id.get();
  }

  /**
   * @return the turtle's degree, a double from [0, 360)
   */
  public double getDegree() {
    return angleFacing.get();
  }

  /**
   * Update the coordinates. Set the past coordinates to the current coordinates
   */
  public void updateCoordinates() {
    Coordinate coords = ((Coordinate)coordinates.get());
    pastCoordinates.set(new Coordinate(coords.getX(), coords.getY()));
  }

  protected double getDistance() {
    return distance.get();
  }

  /**
   * Set coordinate
   * @param newX
   * @param newY
   */
  public void setCoordinate(double newX, double newY) {
    updateCoordinates();
    coordinates.set(new Coordinate(newX, newY));
  }

  /**
   * Set degree of direction turtle is facing
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
      throw new CommandException(errors.getString("Degree"));
    angleFacing.set(degree);
  }

  /**
   * Set distance turtle has moved
   * @param distance
   */
  public void setDistance(double distance) {
    this.distance.set(distance);
  }

  /**
   * Set activation of turtle
   * @param state
   */
  public void setActivated(boolean state) { this.isActivated.set(state); }

  /**
   * Make pen down false
   */
  public void penUp() {
    isPenDown.set(false);
  }

  /**
   * Make pen down true
   */
  public void penDown() {
    isPenDown.set(true);
  }

  /**
   * Change turtle showing or not
   * @param showing
   */
  public void changeShowing(boolean showing) {
    isShowing.set(showing);
  }

  /**
   * Set the turtle's id
   * @param id
   */
  public void setId(int id) {
    this.id.set(id);
  }
}
