package slogo;

import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import slogo.View.Language;
import slogo.View.UserException;
import slogo.model.*;

import slogo.View.Visualizer;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  private static final String RESOURCES = "resources";
  private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
  private static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
  private static final String TURTLE_PNG = "turtle.png";
  private static final String ERROR_TITLE ="Alert test - error";
  private static final String DEBUGGING_T ="fd 100";
  private static final int ACTIVATED =1;
  private static final int DEACTIVATED =2;
  private ObservableMap myMap = FXCollections.observableMap(new HashMap<String, String>());
  private ObservableMap myCustomMap = FXCollections.observableMap(new HashMap<String, String>());
  private TurtleList turtleList;
  public static final ResourceBundle MY_RESOURCES = ResourceBundle
      .getBundle(DEFAULT_RESOURCE_PACKAGE + "DisplayEnglish");

  public VariableStorage variableStorage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    TurtleList turtleList = new TurtleList(FXCollections.observableArrayList(), FXCollections.observableArrayList());
    Turtle modelTurtle1 = new Turtle();
    Turtle modelTurtle2 = new Turtle();
    turtleList.addTurtleToModelList(modelTurtle1);
    turtleList.addTurtleToModelList(modelTurtle2);

    Language language = new Language();
    DisplayOption displayOption = new DisplayOption();
    VariableStorage variableStorage = new VariableStorage(myMap);
    CustomCommandStorage customCommandStorage = new CustomCommandStorage();
    this.variableStorage = variableStorage;
    CommandParser commandParser = new CommandParser(turtleList.getModelTurtleList(), variableStorage.getModelObservableMap(), language, customCommandStorage);
    commandParser.setDisplayOption(displayOption);
    StringProperty commandLineText = new SimpleStringProperty();
    StringProperty parseString = new SimpleStringProperty();
    parseString.bind(commandLineText);
    BooleanProperty textUpdate = new SimpleBooleanProperty();

    Visualizer vis = new Visualizer(primaryStage, turtleList.getViewTurtleList(), turtleList.getActiveTurtleList(), variableStorage.getViewObservableMap(), commandLineText,
        textUpdate, language, commandParser, myMap, displayOption, customCommandStorage.getCommandTriplets());
    vis.setDisplayOption(displayOption);

    parseTextOnInput(textUpdate, parseString, commandParser, vis);

    turtleList.makeModelTurtleActivated(1);
    turtleList.makeModelTurtleDeactivated(2);
    turtleList.makeModelTurtleActivated(1);
    turtleList.makeModelTurtleActivated(2);
    commandParser.parseText("tell [ 3 ]");
    commandParser.parseText("fd 100");
    commandParser.parseText("tell [ 2 ]");
    commandParser.parseText("rt 45 fd 100");
    commandParser.parseText("tell [ 1 ]");
    commandParser.parseText("rt 315 fd 100");
    commandParser.parseText("tell [ 1 2 3 ]");
    commandParser.parseText("to c [ ] [ repeat 100 [  fd 50 rt 50  ] ]");
    commandParser.parseText("c ");
    //commandParser.parseText("fd 50");
//    commandParser.parseText("to c [ :f ] [ rt :f fd :f ]");
//    commandParser.parseText("c 70 ");
    for (Turtle turtle : turtleList.getModelTurtleList()) {
      System.out.println(
          "MODELTurtle " + turtle.getId() + " x: " + turtle.getX() + " y: " + turtle.getY()
              + " Angle: " + turtle.getDegree() + " Activated: "+ turtle.isActivatedProperty().getValue());
    }
    turtleList.makeModelTurtleActivated(ACTIVATED);
    turtleList.makeModelTurtleDeactivated(DEACTIVATED);
    turtleList.makeModelTurtleActivated(ACTIVATED);
    turtleList.makeModelTurtleActivated(DEACTIVATED);
    commandParser.parseText(DEBUGGING_T);
  }

  private void bindTurtles(Turtle model, Turtle view) {
    view.distanceProperty().bind(model.distanceProperty());
    view.angleProperty().bind(model.angleProperty());
    view.isPenDownProperty().bind(model.isPenDownProperty());
    view.isShowingProperty().bind(model.isShowingProperty());
    view.coordinatesProperty().bind(model.coordinatesProperty());
    view.clearScreenProperty().bind(model.clearScreenProperty());
  }


  private void parseTextOnInput(BooleanProperty textUpdate, StringProperty parseText,
      CommandParser commandParser, Visualizer vis) {
    textUpdate.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println(parseText.getValue());
        try {
          String finalValue = commandParser.parseText(parseText.getValue());
          vis.makeNewBox(parseText.getValue());
          vis.makeNewTerminalBox(finalValue);
        } catch (CommandException | UserException e) {
          showError(e.getMessage());
        }
      }
    });
  }

  private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(ERROR_TITLE);
    alert.setContentText(message);
    alert.showAndWait();
  }
}