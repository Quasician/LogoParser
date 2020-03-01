package slogo.View;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
  private ObservableList<Turtle> viewTurtles;
  private ArrayList<ImageView> turtleImageView = new ArrayList<>();
  private Pane myPane; //to change background of grid, change the background of the pane
  private Canvas myCanvas;
  private static final int DEFAULT_CANVAS_WIDTH = 600;
  private static final int DEFAULT_CANVAS_HEIGHT = 600;
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
  public TurtleGrid(int canvasWidth, int canvasHeight, ObservableList<Turtle> viewTurtles) {
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
    this.viewTurtles = viewTurtles;
    for(Turtle viewTurtle: this.viewTurtles)
    {
      System.out.println("ASDF");
      setUpTurtle(viewTurtle);
    }
    addSizeListener();
    //viewTurtles.add(new Turtle());
  }

  public TurtleGrid(ObservableList<Turtle> turtles) {
    this(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, turtles);
  }

  private BooleanProperty clearScreenProperty() {
    return clearScreen;
  }

  private void setUpTurtle(Turtle turtle) {
      turtleImageView.add(turtle.getId(), new ImageView(new Image(Main.myResources.getString(TURTLE_IMAGE))));
      turtleImageView.get(turtle.getId()).setX(centerX);
      turtleImageView.get(turtle.getId()).setY(centerY);
      turtleImageView.get(turtle.getId()).setFitHeight(TURTLE_IMAGE_HEIGHT);
      turtleImageView.get(turtle.getId()).setFitWidth(TURTLE_IMAGE_WIDTH);
      turtleImageView.get(turtle.getId()).rotateProperty();
      addListeners(turtle);
      myPane.getChildren().add(turtleImageView.get(turtle.getId()));

      turtleCenterX = turtleImageView.get(turtle.getId()).getFitWidth() / 2;
      turtleCenterY = turtleImageView.get(turtle.getId()).getFitHeight() / 2;

      pastX = turtleImageView.get(turtle.getId()).getX() + turtleCenterX;
      pastY = turtleImageView.get(turtle.getId()).getY() + turtleCenterY;
  }

  private void addListeners(Turtle viewTurtle) {
    addCoordinatesListener(viewTurtle);
    addAnglePropertyListener(viewTurtle);
    addPenDownListener(viewTurtle);
    addClearScreenListener(viewTurtle);
    addShowingListener(viewTurtle);
  }

  private void addCoordinatesListener(Turtle viewTurtle) {
    viewTurtle.coordinatesProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println("DSDSDFDSF");
        turtleImageView.get(viewTurtle.getId()).setX(viewTurtle.getX() + centerX);
        turtleImageView.get(viewTurtle.getId()).setY(-(viewTurtle.getY()) + centerY);
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

  private void addAnglePropertyListener(Turtle viewTurtle) {

      viewTurtle.angleProperty().addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue o, Object oldVal, Object newVal) {
          //System.out.println("Angle changed to: " + viewTurtle.getDegree());
          turtleImageView.get(viewTurtle.getId()).setRotate(viewTurtle.getDegree());
        }
      });
  }

  private void addPenDownListener(Turtle viewTurtle) {
    viewTurtle.isPenDownProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println("Pen has been changed to: " + viewTurtle.isPenDown());
        isPenDown = viewTurtle.isPenDown();
      }
    });
  }

  private void addClearScreenListener(Turtle viewTurtle) {
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

  private void addShowingListener(Turtle viewTurtle) {
    viewTurtle.isShowingProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                          Boolean newValue) {
        if (viewTurtle.isShowingProperty().get()) { //make turtle visible
          turtleImageView.get(viewTurtle.getId()).setVisible(true);
        } else { //make turtle invisible
          turtleImageView.get(viewTurtle.getId()).setVisible(false);
        }
      }
    });
  }

  private void addSizeListener()
  {
    viewTurtles.addListener(new ListChangeListener<Turtle>() {
      @Override
      public void onChanged(Change<? extends Turtle> c) {
        c.next();
        List<Turtle> newTurtles = (List<Turtle>) c.getAddedSubList();
        System.out.println("View turtles changed in turtle grid");
        for(Turtle changedTurtle:newTurtles) {
          System.out.println("NEW VIEW turtle: " + changedTurtle.isActivatedProperty().getValue());
          setUpTurtle(changedTurtle);
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

  protected void updateTurtlesImage(String string, ObservableList<Turtle> updateTurtles) {
    for(Turtle viewTurtle: updateTurtles)
    {
      turtleImageView.get(viewTurtle.getId()).setImage(new Image(Main.myResources.getString(string)));
    }
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
