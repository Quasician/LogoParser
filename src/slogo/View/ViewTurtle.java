package slogo.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewTurtle {

  private static final String TURTLE = "turtle.png";
  private ImageView turtleImage = new ImageView(new Image(TURTLE));

  public ViewTurtle() {
    Image turtleImage = new Image("turtle.png");
    ImageView turtle = new ImageView(turtleImage);
  }

}
