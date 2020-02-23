package slogo.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
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
  private Canvas  myCanvas;
  private static final int DEFAULT_CANVAS_WIDTH = 1140;
  private static final int DEFAULT_CANVAS_HEIGHT = 630;
  private static final String TURT_IMAGE = "TurtleImage";
  private StackPane retGrid;
  private double centerX;
  private double centerY;
  private double pastX;
  private double pastY;
  private Boolean ispenDown = true;
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
    turtleImageView = new ImageView(new Image(Main.myResources.getString(TURT_IMAGE)));
    turtleImageView.setX(viewTurtle.getX());
    turtleImageView.setY(viewTurtle.getY());
    turtleImageView.setFitHeight(40);
    turtleImageView.setFitWidth(40);
    addListeners();
    myCanvasWidth = canvasWidth;
    myCanvasHeight = canvasHeight;
    centerX = canvasWidth / 2.0;
    centerY = canvasHeight / 2.0;
    pastX = centerX;
    pastY = centerY;
    myPane = new Pane();
    myPane.setMaxWidth(myCanvasWidth);
    myPane.setMaxHeight(myCanvasHeight);
    setBackground(Color.LINEN);
    myCanvas = new Canvas(myCanvasWidth, myCanvasHeight);
    retGrid = new StackPane();
    retGrid.setPadding(new Insets(10,10,10,0));
    retGrid.getChildren().addAll(myCanvas, myPane);

    this.viewTurtle = viewTurtle;
    turtleImageView = new ImageView(new Image(Main.myResources.getString("TurtleImage")));
    turtleImageView.setX(centerX);
    turtleImageView.setY(centerY);
    turtleImageView.setFitHeight(40);
    turtleImageView.setFitWidth(40);
    turtleImageView.rotateProperty();
    addListeners();
    myPane.getChildren().add(turtleImageView);
  }

  public TurtleGrid(Turtle turtle, Drawing draw){
    this(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, draw, turtle);
  }

  private void addListeners() {
    viewTurtle.xProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println("X value changed to: " + (viewTurtle.getX()+centerX));
        turtleImageView.setX(viewTurtle.getX()+centerX);
        System.out.println("Y val on x change:"+ -(viewTurtle.getY())+centerY);
        if(ispenDown)
        {
          drawLine(myPane, pastX, pastY, viewTurtle.getX()+centerX , -(viewTurtle.getY())+centerY);

        }
        pastX = viewTurtle.getX()+centerX;
      }
    });

    viewTurtle.yProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println("Y value changed to: " + (-(viewTurtle.getY())+centerY));
        turtleImageView.setY(-(viewTurtle.getY())+centerY);
        if(ispenDown)
        {
          drawLine(myPane, pastX, pastY, viewTurtle.getX()+centerX , -(viewTurtle.getY())+centerY);

        }
        pastY = -(viewTurtle.getY())+centerY;
      }
    });

    viewTurtle.angleProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println("Angle changed to: " + viewTurtle.getDegree());
        turtleImageView.setRotate(viewTurtle.getDegree());
      }
    });

    viewTurtle.isPenDownProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println("Pen has been changed to: " + viewTurtle.isPenDown());
        ispenDown = viewTurtle.isPenDown();
      }
    });
  }

  public void drawLine(Pane pane, double x1, double y1, double x2, double y2)
  {
    System.out.println("From: (" +x1+" , "+ y1+")   to  (" + x2+ " , "+ y2+")");
    Line line = new Line(x1,y1,x2,y2);
    line.setStroke(Color.RED);
    pane.getChildren().add(line);
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
