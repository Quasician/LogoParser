package slogo.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.Main;
import slogo.model.Turtle;

/**
 * This class holds the grid where the commands are executed on; for example, if the turtle moves
 * forward with its pen down, we would see the line on the canvas. The class also has a drawer class to
 * draw when the pen is down and knows the turtle's information (like location) through binding, and updates
 * the turtle's properties through these bindings
 *
 * @author Michelle Tai
 */
public class TurtleGrid {
  private int myCanvasWidth;
  private int myCanvasHeight;
  private Turtle viewTurtle;
  private ImageView turtleImageView;
  //private Turtle myTurtle;
  private Drawing myDrawer;
  private Pane myPane; //to change background of grid, change the background of the pane
  private Canvas myCanvas;
  private static final int DEFAULT_CANVAS_WIDTH = 800;
  private static final int DEFAULT_CANVAS_HEIGHT = 500;
  private StackPane retGrid;
  private double centerX;
  private double centerY;
//  private static final Paint DEFAULT_BACKGROUND  = Color.

  /**
   * Constructor for the TurtleGrid class, which initializes everything
   * @param canvasWidth is the width of the canvas where the turtle is located, and where all the shapes are drawn
   * @param canvasHeight is the height of the canvas
   * @param draw is the drawing class that would control what will be drawn on the canvas
   */
  public TurtleGrid(int canvasWidth, int canvasHeight, Drawing draw, Turtle viewTurtle){
    myDrawer = draw;
    this.viewTurtle = viewTurtle;
    turtleImageView = new ImageView(new Image(Main.myResources.getString("TurtleImage")));
    turtleImageView.setX(viewTurtle.getX());
    turtleImageView.setY(viewTurtle.getY());
    turtleImageView.setFitHeight(40);
    turtleImageView.setFitWidth(40);

    addListeners();

    myCanvasWidth = canvasWidth;
    myCanvasHeight = canvasHeight;
    centerX = canvasWidth / 2.0;
    centerY = canvasHeight / 2.0;
    myPane = new Pane();
    myPane.setMaxWidth(myCanvasWidth);
    myPane.setMaxHeight(myCanvasHeight);
    setBackground(Color.LINEN);
    myCanvas = new Canvas(myCanvasWidth, myCanvasHeight);
    retGrid = new StackPane();
    retGrid.setPadding(new Insets(20));
    retGrid.getChildren().addAll(myCanvas, myPane);
    retGrid.getChildren().add(turtleImageView);
  }

  public TurtleGrid(Turtle turtle, Drawing draw){
    this(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, draw, turtle);
  }

  private void addListeners() {
    viewTurtle.xProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        turtleImageView.setX(viewTurtle.getX());
      }
    });

    viewTurtle.yProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        turtleImageView.setY(viewTurtle.getY());
      }
    });
  }

  protected Node getTurtleGrid(){
    return retGrid;
  }

  protected void setBackground(Color color){
    myPane.setBackground(new Background(new BackgroundFill(color, null, null)));
  }

  private void updateTurtle(){

  }

}
