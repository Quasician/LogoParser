package slogo.View;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.Main;
import slogo.model.Coordinate;

public class ViewTurtle {

  private static final String DEFAULT_TURTLE = "TurtleImage";
  private static final Image DEFAULT_IMAGE =  new Image(Main.myResources.getString(DEFAULT_TURTLE));
  private static final double HEIGHT = 40;
  private static final double WIDTH = 40;
  private static final int coordInitial = 0;
  private static final int screenCenter = 2;
  private double height;
  private double width;

  private ImageView myImageView;
  private ObjectProperty<Coordinate> coordinateProperty;
  private DoubleProperty angleProperty;

  public ViewTurtle() {
    this(HEIGHT, WIDTH);
  }

  public ViewTurtle(double height, double width) {
    myImageView = new ImageView(DEFAULT_IMAGE);
    coordinateProperty = new SimpleObjectProperty(new Coordinate(coordInitial,coordInitial));
    angleProperty = new SimpleDoubleProperty(coordInitial);
    this.height = height;
    this.width = width;
  }

  protected DoubleProperty getAngleProperty() {
    return angleProperty;
  }

  protected ObjectProperty getCoordinateProperty() {
    return coordinateProperty;
  }

  protected double getX() {
    return (coordinateProperty.get()).getX();
  }

  protected double getY() {
    return (coordinateProperty.get()).getY();
  }

  protected double getAngle() {
    return angleProperty.get();
  }

  protected ImageView getImage() {
    return myImageView;
  }

  protected void setImage(String imageName) {
    String imageString = Main.myResources.getString(imageName);
    try {
      myImageView = new ImageView(new Image(imageString));
    } catch (Exception e) {
      throw new UserException(e);
    }
  }

  protected double getCenterX() {
    return width / screenCenter;
  }

  protected double getCenterY() {
    return height / screenCenter;
  }

}
