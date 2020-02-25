package slogo.View;

import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
 * forward with its pen down, we would see the line on the canvas. The class also has a drawer class
 * to draw when the pen is down and knows the turtle's information (like location) through binding,
 * and updates the turtle's properties through these bindings
 *
 * @author Michelle Tai, Sanna Symer
 */
public class TurtleGrid {

  public static final int TURTLE_IMAGE_HEIGHT = 40;
  public static final int TURTLE_IMAGE_WIDTH = 40;
  public static final Color DEFAULT_PEN_COLOR = Color.RED;
  private int myCanvasWidth, myCanvasHeight;
  private Turtle viewTurtle;
  private ImageView turtleImageView;
  private Pane myPane; //to change background of grid, change the background of the pane
  private Canvas myCanvas;
  private static final int DEFAULT_CANVAS_WIDTH = 1140;
  private static final int DEFAULT_CANVAS_HEIGHT = 630;
  private static final String TURTLE_IMAGE = "TurtleImage";
  private StackPane retGrid;
  private double centerX, centerY, pastX, pastY;
  private double turtleCenterX, turtleCenterY;
  private Boolean isPenDown = true;
  private ArrayList<Line> linesDrawn;
  private Paint penColor;
//  private static final Paint DEFAULT_BACKGROUND  = Color.

  private BooleanProperty clearScreen = new SimpleBooleanProperty();

  /**
   * Constructor for the TurtleGrid class, which initializes everything
   *  @param canvasWidth  is the width of the canvas where the turtle is located, and where all the
   *                     shapes are drawn
   * @param canvasHeight is the height of the canvas
   */
  public TurtleGrid(int canvasWidth, int canvasHeight, Turtle viewTurtle) {
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
    retGrid.setPadding(new Insets(10, 10, 10, 0));
    retGrid.getChildren().addAll(myCanvas, myPane);

    penColor = DEFAULT_PEN_COLOR;
    linesDrawn = new ArrayList<>();
    this.viewTurtle = viewTurtle;
    setUpTurtle();
  }

  public TurtleGrid(Turtle turtle) {
    this(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, turtle);
  }

  private BooleanProperty clearScreenProperty() {
    return clearScreen;
  }

  private void setUpTurtle() {
    turtleImageView = new ImageView(new Image(Main.myResources.getString(TURTLE_IMAGE)));
    turtleImageView.setX(centerX);
    turtleImageView.setY(centerY);
    turtleImageView.setFitHeight(TURTLE_IMAGE_HEIGHT);
    turtleImageView.setFitWidth(TURTLE_IMAGE_WIDTH);
    turtleImageView.rotateProperty();
    addListeners();
    myPane.getChildren().add(turtleImageView);

    turtleCenterX = turtleImageView.getFitWidth() / 2;
    turtleCenterY = turtleImageView.getFitHeight() / 2;

    pastX = turtleImageView.getX() + turtleCenterX;
    pastY = turtleImageView.getY() + turtleCenterY;
  }

  private void addListeners() {
    addCoordinatesListener();
    addAnglePropertyListener();
    addPenDownListener();
    addClearScreenListener();
    addShowingListener();
  }

  private void addCoordinatesListener() {
    viewTurtle.coordinatesProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        turtleImageView.setX(viewTurtle.getX() + centerX);
        turtleImageView.setY(-(viewTurtle.getY()) + centerY);
        if (isPenDown) {
          makeLine(pastX, pastY, viewTurtle.getX() + turtleCenterX + centerX,
              -(viewTurtle.getY() - turtleCenterY) + centerY);

        }
        drawAllLines();
        pastX = viewTurtle.getX() + turtleCenterX + centerX;
        pastY = -(viewTurtle.getY() - turtleCenterY) + centerY;
      }
    });
  }

  private void addAnglePropertyListener() {
    viewTurtle.angleProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        //System.out.println("Angle changed to: " + viewTurtle.getDegree());
        turtleImageView.setRotate(viewTurtle.getDegree());
      }
    });
  }

  private void addPenDownListener() {
    viewTurtle.isPenDownProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println("Pen has been changed to: " + viewTurtle.isPenDown());
        isPenDown = viewTurtle.isPenDown();
      }
    });
  }

  private void addClearScreenListener() {
    viewTurtle.clearScreenProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
          Boolean newValue) {
        if (viewTurtle.clearScreenProperty().get()) { //if true
          removeLines();
        }
      }
    });
  }

  private void addShowingListener() {
    viewTurtle.isShowingProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
          Boolean newValue) {
        if (viewTurtle.isShowingProperty().get()) { //make turtle visible
          turtleImageView.setVisible(true);
        } else { //make turtle invisible
          turtleImageView.setVisible(false);
        }
      }
    });
  }

  protected void setPenColor(Paint color) {
    penColor = color;
  }

  private void makeLine(double x1, double y1, double x2, double y2) {
    System.out.println("From: (" + x1 + " , " + y1 + ")   to  (" + x2 + " , " + y2 + ")");
    Line line = new Line(x1, y1, x2, y2);
    line.setStroke(penColor);
    linesDrawn.add(line);
  }

  private void drawAllLines() {
    for (Line line : linesDrawn) {
      if (!myPane.getChildren().contains(line))
        myPane.getChildren().add(line);
    }
  }

  private void removeLines() {
    for (Line line : linesDrawn) {
      if (myPane.getChildren().contains(line)) {
        myPane.getChildren().remove(line);
      }
    }
    linesDrawn = new ArrayList<>();
  }

  protected Node getTurtleGrid() {
    return retGrid;
  }

  protected void setBackground(Color color) {
    myPane.setBackground(new Background(new BackgroundFill(color, null, null)));
  }

  private void updateTurtle() {

  }

}



//    viewTurtle.xProperty().addListener(new ChangeListener() {
//      @Override
//      public void changed(ObservableValue o, Object oldVal, Object newVal) {
//        //System.out.println("X value changed to: " + (viewTurtle.getX() + centerX));
//        turtleImageView.setX(viewTurtle.getX() + centerX);
//        //System.out.println("Y val on x change:" + -(viewTurtle.getY()) + centerY);
//        if (isPenDown) {
//          makeLine(pastX, pastY, viewTurtle.getX() + turtleCenterX + centerX,
//              -(viewTurtle.getY() - turtleCenterY) + centerY);
//
//        }
//        drawAllLines();
//        pastX = viewTurtle.getX() + turtleCenterX + centerX;
//        pastY = -(viewTurtle.getY() - turtleCenterY) + centerY;
//      }
//    });
//
//    viewTurtle.yProperty().addListener(new ChangeListener() {
//      @Override
//      public void changed(ObservableValue o, Object oldVal, Object newVal) {
//      //  System.out.println("Y value changed to: " + (-(viewTurtle.getY()) + centerY));
//        turtleImageView.setY(-(viewTurtle.getY()) + centerY);
//        if (isPenDown) {
//          makeLine(pastX, pastY, viewTurtle.getX() + turtleCenterX + centerX,
//              -(viewTurtle.getY() - turtleCenterY) + centerY);
//
//        }
//        drawAllLines();
//        pastX = viewTurtle.getX() + turtleCenterX + centerX;
//        pastY = -(viewTurtle.getY() - turtleCenterY) + centerY;
//      }
//    });
