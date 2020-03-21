package slogo.View;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.model.DisplayOption;
import slogo.model.Turtle;
import slogo.model.TurtleList;

import java.util.HashMap;
import java.util.ResourceBundle;

public class PropertiesHolder {

  private ListView variablesTable;
  private static final String THIS_PACKAGE = PropertiesHolder.class.getPackageName();
  private static final String MY_RESOURCE_FOLDER = THIS_PACKAGE + ".visualProperty.";
  private static final ResourceBundle TURTLE_IMAGES = ResourceBundle
      .getBundle(MY_RESOURCE_FOLDER + "PropertiesWindow");
  private static final String TURTLE_NUMBER_TEXT = TURTLE_IMAGES.getString("TurtlesNumber");
  private static final String PEN_THICKNESS = TURTLE_IMAGES.getString("PenThicknessText");
  private static final String PEN_DOWN_PROP = TURTLE_IMAGES.getString("PenDownText");
  private static final String BACKGROUND_COLOR_TEXT = TURTLE_IMAGES
      .getString("BackgroundColorText");
  private static final String PEN_COLOR_TEXT = TURTLE_IMAGES.getString("PenColorText");
  private static final String IMAGE_INDEX_TEXT = TURTLE_IMAGES.getString("ImageIndexText");
  private static final String TURT_COORD_TEXT = TURTLE_IMAGES.getString("TurtleCoordText");
  private static final String TURT_STATUS_TEXT = TURTLE_IMAGES.getString("TurtStatusText");
  private static final String IS_STRING = " is ";
  private static final String COMMA_STRING = ",";
  private static final String ID_STRING="Turtle ID: ";
  private static final String X_STRING= " X coordinate: ";
  private static final String Y_STRING=" Y coordinate of : ";
  private static final String SPACE=" ";
  private Label PropertyName;
  private Label number;
  private Label color;
  private Label size;
  private Label penDown;
  private Label penColor;
  private Label turtleStatus;
  private VBox turtleCoord;
  private Label turtleCooord;
  private Label imageIndex;
  private HashMap<Integer, HBoxFactory> MapForCoord;

  public PropertiesHolder(ObservableList<Turtle> turtles, DisplayOption displayOption) {
    variablesTable = new ListView<>();
    MapForCoord= new HashMap<>();
    PropertyName = new Label();
    number = new Label();
    color = new Label();
    size = new Label();
    penColor = new Label();
    imageIndex = new Label();
    penDown = new Label();
    turtleStatus = new Label();
    turtleCoord = new VBox();
    turtleCooord = new Label();
    addRowListener(turtles);
    sendDisplay(displayOption);
    initialDisplay(displayOption, turtles);
  }

  public void initialDisplay(DisplayOption displayOption, ObservableList<Turtle> turtles) {
    color.setText(PEN_THICKNESS + displayOption.getPenWidthProperty().getValue());
    size.setText(BACKGROUND_COLOR_TEXT + displayOption.getBgIndex());
    penColor.setText(PEN_COLOR_TEXT + displayOption.getPenIndex());
    imageIndex.setText(IMAGE_INDEX_TEXT + displayOption.getImageIndex());
    int count = 0;
    for (Turtle t : turtles) {
      count++;
    }
    if (variablesTable.getItems().contains(number)) {
      variablesTable.getItems().remove(number);
    }
    number.setText(TURTLE_NUMBER_TEXT + count);
    penDown.setText(PEN_DOWN_PROP + true);
    variablesTable.getItems()
        .addAll(number, color, size, penColor, imageIndex, penDown, turtleStatus, turtleCoord);


  }

  public void addRowListener(ObservableList<Turtle> turtles) {
    turtles.addListener((ListChangeListener<? super Turtle>) e -> {
      int count = 0;
      for (Turtle t : turtles) {
        count++;
      }
      number.setText(TURTLE_NUMBER_TEXT + count);
    });
  }

  protected Node getScene() {
    return variablesTable;
  }

  public void sendDisplay(DisplayOption displayOption) {
    displayOption.getPenWidthProperty().addListener(e -> {
      color.setText(PEN_THICKNESS + displayOption.getPenWidthProperty().getValue());
    });

    displayOption.getBgIndex().addListener(e -> {
      size.setText(BACKGROUND_COLOR_TEXT + displayOption.getBgIndex());
    });

    displayOption.getPenIndex().addListener(e -> {
      penColor.setText(PEN_COLOR_TEXT + displayOption.getPenIndex());
    });

    displayOption.getImageIndex().addListener(e -> {
      imageIndex.setText(IMAGE_INDEX_TEXT + displayOption.getImageIndex());
    });
  }


  public void sendPenColor(Paint penColorIndex) {
    penColor.setText(PEN_COLOR_TEXT + penColorIndex);
  }

  public void setTurtleIndex(Object value) {
    imageIndex.setText(IMAGE_INDEX_TEXT + value);
  }

  public void changeBackground(Color color) {
    size.setText(BACKGROUND_COLOR_TEXT + color);
  }

  public void changePenDown(Boolean isPenDown) {
    penDown.setText(PEN_DOWN_PROP + isPenDown);
  }

  public void changeActive(Turtle viewTurtle) {
    turtleStatus.setText(
        TURT_STATUS_TEXT + viewTurtle.getId() + IS_STRING + viewTurtle.isActivatedProperty()
            .getValue());
  }

  public void makeCoord(ObservableList<Turtle> viewTurtles) {
    for(Turtle i:viewTurtles){
      if(i.isActivatedProperty().getValue()) {
        if(MapForCoord.containsKey(i.getId())){
            HBoxFactory updateValues= MapForCoord.get(i.getId());
            updateValues.SetXCord(i.getId(),i.getX());
            updateValues.SetYCord(i.getId(),i.getY());
        }else{
          turtleCoord.getChildren().add(makeLabel(i.getX(), i.getY(), i));
        }

      }
    }
    }

  private HBox  makeLabel(double x, double y, Turtle i) {
    String name= ID_STRING+i.getId();
    HBoxFactory makeHb= new HBoxFactory(x,y,name);
    MapForCoord.put(i.getId(),makeHb);
    return makeHb.newBox;
  }

}













