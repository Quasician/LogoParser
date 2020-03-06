package slogo.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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

import javax.sound.midi.SysexMessage;

/**
 * This class holds the grid where the commands are executed on; for example, if the turtle moves
 * forward with its pen down, we would see the line on the canvas. The class also has a drawer class
 * to draw when the pen is down and knows the turtle's information (like location) through binding,
 * and updates the turtle's properties through these bindings
 *
 * @author Michelle Tai, Sanna Symer
 */
public class TurtleGrid {

  private static final int TURTLE_IMAGE_HEIGHT = 40;
  private static final int TURTLE_IMAGE_WIDTH = 40;
  private static final Color DEFAULT_PEN_COLOR = Color.RED;
  private static final Color DEFAULT_BACKGROUND = Color.LINEN;
  private static final double DEFAULT_PEN_WIDTH = 1;
  private int myCanvasWidth, myCanvasHeight;
  private ObservableList<Turtle> viewTurtles;
  private Configuration properties;
  private ArrayList<ImageView> turtleImageViews = new ArrayList<>();
  private Pane myPane; //to change background of grid, change the background of the pane
  private Canvas myCanvas;
  private static final int DEFAULT_CANVAS_WIDTH = 600;
  private static final int DEFAULT_CANVAS_HEIGHT = 600;
  private static final String TURTLE_IMAGE = "TurtleImage";
  private StackPane retGrid;
  private double centerX, centerY;
  private double turtleCenterX, turtleCenterY;
  private Boolean isPenDown = true;
  private ArrayList<Line> linesDrawn;
  private Paint penColor;
  private double penWidth;
  private Configuration PropertiesView;
  private BooleanProperty clearScreen = new SimpleBooleanProperty();
  private static final int PADDING_INSET = 10;

  /**
   * Constructor for the TurtleGrid class, which initializes everything
   *
   * @param canvasWidth  is the width of the canvas where the turtle is located, and where all the
   *                     shapes are drawn
   * @param canvasHeight is the height of the canvas
   */
  public TurtleGrid(int canvasWidth, int canvasHeight, ObservableList<Turtle> viewTurtles) {
    PropertiesView= new Configuration(viewTurtles);
    myCanvasWidth = canvasWidth;
    myCanvasHeight = canvasHeight;
    centerX = canvasWidth / 2.0;
    centerY = canvasHeight / 2.0;
    setUpPane();
    setBackground(DEFAULT_BACKGROUND);
    myCanvas = new Canvas(myCanvasWidth, myCanvasHeight);
    setUpGrid();
    penColor = DEFAULT_PEN_COLOR;
    penWidth = DEFAULT_PEN_WIDTH;
    linesDrawn = new ArrayList<>();
    this.viewTurtles = viewTurtles;
    for (Turtle viewTurtle : this.viewTurtles) {
      setUpTurtle(viewTurtle);
    }
    addSizeListener();
  }

  public TurtleGrid(ObservableList<Turtle> turtles) {
    this(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT, turtles);
  }

  private void setUpGrid() {
    retGrid = new StackPane();
    retGrid.setPadding(new Insets(PADDING_INSET, PADDING_INSET, PADDING_INSET, 0));
    retGrid.getChildren().addAll(myCanvas, myPane);
  }

  private void setUpPane() {
    myPane = new Pane();
    myPane.setMaxWidth(myCanvasWidth);
    myPane.setMaxHeight(myCanvasHeight);
  }

  private BooleanProperty clearScreenProperty() {
    return clearScreen;
  }

  private void setUpTurtle(Turtle turtle) {
    Image turtleImage = new Image(Main.myResources.getString(TURTLE_IMAGE));
    ImageView turtleImageView = new ImageView(turtleImage);
    turtleImageView.setOpacity(0.7);
    turtleImageViews.add(turtle.getId()-1, turtleImageView);
    turtleImageViews.get(turtle.getId()-1).setX(centerX);
    turtleImageViews.get(turtle.getId()-1).setY(centerY);
    turtleImageViews.get(turtle.getId()-1).setFitHeight(TURTLE_IMAGE_HEIGHT);
    turtleImageViews.get(turtle.getId()-1).setFitWidth(TURTLE_IMAGE_WIDTH);
    turtleImageViews.get(turtle.getId()-1).rotateProperty();
    turtleImageViews.get(turtle.getId()-1).requestFocus();
    turtleImageViews.get(turtle.getId()-1).setOnMouseClicked(e-> {
      turtle.setActivated(!turtle.isActivatedProperty().getValue());
      changeOpacity(turtle);
    });
    addListeners(turtle);
    myPane.getChildren().add(turtleImageViews.get(turtle.getId()-1));

    turtleCenterX = turtleImageViews.get(turtle.getId()-1).getFitWidth() / 2;
    turtleCenterY = turtleImageViews.get(turtle.getId()-1).getFitHeight() / 2;
  }

  private void changeOpacity(Turtle turtle) {
    ImageView opaquePics = turtleImageViews.get(turtle.getId()-1);
    if(!turtle.isActivatedProperty().getValue()){
      opaquePics = turtleImageViews.get(turtle.getId()-1);
      opaquePics.setOpacity(0.2);
      System.out.println("inactive");
    }
    else{
      opaquePics = turtleImageViews.get(turtle.getId()-1);
      opaquePics.setOpacity(0.7);
      System.out.println("active");
    }
    turtleImageViews.set(turtle.getId()-1, opaquePics);
  }

  private void addListeners(Turtle viewTurtle) {
    addCoordinatesListener(viewTurtle);
    addAnglePropertyListener(viewTurtle);
    addPenDownListener(viewTurtle);
    addClearScreenListener(viewTurtle);
    addShowingListener(viewTurtle);
    addActiveListener(viewTurtle);
  }

  private double keepInBounds(double coordinate, int bound) {
    if (coordinate > bound) {
      return bound;
    } else if (coordinate < 0) {
      return 0;
    }
    return coordinate;
  }

  //TODO: need to make each turtle have a center x
  private void addCoordinatesListener(Turtle viewTurtle) {
    viewTurtle.coordinatesProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println("COORDINATES WERE CHANGED");
        int id = viewTurtle.getId()-1;
        ImageView thisView = turtleImageViews.get(id);

        double newX = viewTurtle.getX() + centerX;
        double newY = -(viewTurtle.getY()) + centerY;
        newX = keepInBounds(newX, myCanvasWidth);
        newY = keepInBounds(newY, myCanvasHeight);
        thisView.setX(newX);
        thisView.setY(newY);

        double pastX = keepInBounds(viewTurtle.getPastX() + centerX, myCanvasWidth);
        double pastY = keepInBounds( - viewTurtle.getPastY() + centerY, myCanvasHeight);

        double oldX = pastX + turtleCenterX;
        double oldY = pastY + turtleCenterY;
        double currentX = newX + turtleCenterX;
        double currentY = newY + turtleCenterY;

        if (isPenDown) {
          makeLine(oldX, oldY, currentX, currentY);
        }

        drawAllLines();
      }
    });
  }

  private void addActiveListener(Turtle viewTurtle) {
    viewTurtle.isActivatedProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        changeOpacity(viewTurtle);
      }
    });
  }


  private void addAnglePropertyListener(Turtle viewTurtle) {
    viewTurtle.angleProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        turtleImageViews.get(viewTurtle.getId()-1).setRotate(viewTurtle.getDegree());
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
        if (viewTurtle.clearScreenProperty().get()) {
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
          turtleImageViews.get(viewTurtle.getId()-1).setVisible(true);
        } else { //make turtle invisible
          turtleImageViews.get(viewTurtle.getId()-1).setVisible(false);
        }
      }
    });
  }

  private void addSizeListener() {
    viewTurtles.addListener(new ListChangeListener<Turtle>() {
      @Override
      public void onChanged(Change<? extends Turtle> c) {
        c.next();
        List<Turtle> newTurtles = (List<Turtle>) c.getAddedSubList();
        System.out.println("View turtles changed in turtle grid");
        for (Turtle changedTurtle : newTurtles) {
          System.out.println("NEW VIEW turtle: " + changedTurtle.isActivatedProperty().getValue());
          setUpTurtle(changedTurtle);
        }
        PropertiesView.addRowListener(viewTurtles);
      }
    });
  }

  protected void setPenColor(Paint color) {
    penColor = color;
  }

  protected void setPenWidth(double width) {
    penWidth = width;
  }

  private void makeLine(double x1, double y1, double x2, double y2) {
    System.out.println("From: (" + x1 + " , " + y1 + ")   to  (" + x2 + " , " + y2 + ")");
    Line line = new Line(x1, y1, x2, y2);
    line.setStroke(penColor);
    line.setStrokeWidth(penWidth);
    linesDrawn.add(line);
  }

  private void drawAllLines() {
    for (Line line : linesDrawn) {
      if (!myPane.getChildren().contains(line)) {
        myPane.getChildren().add(line);
      }
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
    for (Turtle viewTurtle : updateTurtles) {
      String imageName = string.split(", ")[0];
      turtleImageViews.get(viewTurtle.getId()-1)
          .setImage(new Image(Main.myResources.getString(imageName)));
    }
  }

  public List<ImageView> getTurtleImage(){
    return turtleImageViews;
  }
}


//  protected void updateTurtlesImage(int index, ObservableList<Turtle> updateTurtles) {
//    for (Turtle viewTurtle : updateTurtles) {
//
//      turtleImageView.get(viewTurtle.getId()-1)
//          .setImage(new Image(Main.myResources.getString(imageName)));
//    }
//  }

