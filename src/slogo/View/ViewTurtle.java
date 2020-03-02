package slogo.View;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.Main;

public class ViewTurtle {

  private static final String DEFAULT_TURTLE = "TurtleImage";
  private static final Image DEFAULT_IMAGE =  new Image(Main.myResources.getString(DEFAULT_TURTLE));
  private static final double HEIGHT = 40;
  private static final double WIDTH = 40;
  private double height;
  private double width;

  private ImageView myImageView;

  public ViewTurtle() {
    this(HEIGHT, WIDTH);
  }

  public ViewTurtle(double height, double width) {
    myImageView = new ImageView(DEFAULT_IMAGE);
    this.height = height;
    this.width = width;
  }

  protected void setImage(String imageName) {
    String imageString = Main.myResources.getString(imageName);
    try {
      myImageView = new ImageView(new Image(imageString));
    } catch (Exception e) {
      throw new UserException("Make this a real error message later");
    }
  }

  protected double getCenterX() {
    return 0;
  }

  protected double getCenterY() {
    return 0;
  }

}
