package slogo;

import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import slogo.View.Language;
import slogo.View.UserException;
import slogo.model.*;

import slogo.View.AbstractTurtle;
import slogo.View.Visualizer;
import slogo.model.xml.XMLException;

import java.util.Iterator;
import java.util.Map;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  private static final String RESOURCES = "resources";
  private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
  private static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
  private static final String TURTLE_PNG = "turtle.png";
  private static final String errorTitle="Alert test - error";
  private static final String debuggingT="fd 100";
  private static final int activated=1;
  private static final int deactivated =2;
  private ObservableMap myMap = FXCollections.observableMap(new HashMap<String, String>());
  private ObservableMap myCustomMap = FXCollections.observableMap(new HashMap<String, String>());
  private TurtleList turtleList;
  public static final ResourceBundle myResources = ResourceBundle
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
    this.variableStorage = variableStorage;
    CommandParser commandParser = new CommandParser(turtleList.getModelTurtleList(), variableStorage.getModelObservableMap(), language);
    commandParser.setDisplayOption(displayOption);
    StringProperty commandLineText = new SimpleStringProperty();
    StringProperty parseString = new SimpleStringProperty();
    parseString.bind(commandLineText);
    BooleanProperty textUpdate = new SimpleBooleanProperty();

    Visualizer vis = new Visualizer(primaryStage, turtleList.getViewTurtleList(), turtleList.getActiveTurtleList(), variableStorage.getViewObservableMap(), commandLineText,
        textUpdate, language, commandParser, myMap, displayOption);
    vis.setDisplayOption(displayOption);

    parseTextOnInput(textUpdate, parseString, commandParser, vis);

    turtleList.makeModelTurtleActivated(activated);
    turtleList.makeModelTurtleDeactivated(deactivated);
    turtleList.makeModelTurtleActivated(activated);
    turtleList.makeModelTurtleActivated(deactivated);
    commandParser.parseText(debuggingT);
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
    alert.setTitle(errorTitle);
    alert.setContentText(message);
    alert.showAndWait();
  }
}