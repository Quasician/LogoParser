package slogo.View;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
  private Turtle myTurtle;
  private Drawing myDrawer;
  private Pane myPane; //to change background of grid, change the background of the pane
  private Canvas myCanvas;
  private static final int DEFAULT_CANVAS_SIZE = 500;
  private StackPane retGrid;
//  private static final Paint DEFAULT_BACKGROUND  = Color.

  /**
   * Constructor for the TurtleGrid class, which initializes everything
   * @param canvasWidth is the width of the canvas where the turtle is located, and where all the shapes are drawn
   * @param canvasHeight is the height of the canvas
   * @param turtle is the turtle that will be drawing on the canvas
   * @param draw is the drawing class that would control what will be drawn on the canvas
   */
  public TurtleGrid(int canvasWidth, int canvasHeight, Turtle turtle, Drawing draw){
    myCanvasWidth = canvasWidth;
    myCanvasHeight = canvasHeight;
    myPane = new Pane();
    myPane.setMaxWidth(myCanvasWidth);
    myPane.setMaxHeight(myCanvasHeight);
    setBackground();
    myCanvas = new Canvas(myCanvasWidth, myCanvasHeight);
    myTurtle = turtle;
    myDrawer = draw;
    retGrid = new StackPane();
    retGrid.getChildren().addAll(myCanvas, myPane);
  }

  public TurtleGrid(Turtle turtle, Drawing draw){
    this(DEFAULT_CANVAS_SIZE, DEFAULT_CANVAS_SIZE, turtle, draw);
  }

  protected Node getTurtleGrid(){
    return retGrid;
  }
  private void setBackground(){
    myPane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
  }

  private void updateTurtle(){
    
  }

}
