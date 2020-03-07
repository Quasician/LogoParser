package slogo;

import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import slogo.View.*;
import slogo.model.*;

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
    turtleList.addTurtleToModelList(modelTurtle1);
    Language language = new Language();
    DisplayOption displayOption = new DisplayOption();
    VariableStorage variableStorage = new VariableStorage(myMap);
    CustomCommandStorage customCommandStorage = new CustomCommandStorage();
    this.variableStorage = variableStorage;
    CommandParser commandParser = new CommandParser(turtleList.getModelTurtleList(), variableStorage.getModelObservableMap(), language, customCommandStorage);
    commandParser.setDisplayOption(displayOption);
    StringProperty commandLineText = new SimpleStringProperty();
    StringProperty parseString = new SimpleStringProperty();
    BooleanProperty checkBox= new SimpleBooleanProperty();
    BooleanProperty translatedTextUpdate= new SimpleBooleanProperty();
    checkBox.setValue(true);
    parseString.bind(commandLineText);
    BooleanProperty textUpdate = new SimpleBooleanProperty();
    Display userScreen= new Display(primaryStage,commandLineText,language,displayOption);
    ActivityListeners propertiesFile= new ActivityListeners(textUpdate,checkBox,translatedTextUpdate);
    Observables lists= new Observables(turtleList.getViewTurtleList(), turtleList.getActiveTurtleList(), variableStorage.getViewObservableMap());
    Visualizer vis = new Visualizer(userScreen, propertiesFile,lists, customCommandStorage.getCommandTriplets());
    vis.setDisplayOption(displayOption);
    parseTextOnInput(textUpdate, parseString, commandParser, vis,checkBox);
    parseTranslatedText(translatedTextUpdate,parseString,commandParser);
  }

  private void parseTextOnInput(BooleanProperty textUpdate, StringProperty parseText,
      CommandParser commandParser, Visualizer vis, BooleanProperty checkB) {
    textUpdate.addListener(e->{
        try {
          System.out.println(parseText.getValue());
          String finalValue = commandParser.parseText(parseText.getValue());
          if(checkB.getValue()) {
            vis.makeNewBox(parseText.getValue());
            vis.makeNewTerminalBox(finalValue);
          }
        } catch (CommandException | UserException ex) {
          showError(ex.getMessage());
        }
    });
  }

  private void parseTranslatedText(BooleanProperty translatedUpdate, StringProperty parseText,
                                   CommandParser commandParser) {
    translatedUpdate.addListener(e-> {
        System.out.println(parseText.getValue());
        try {
            commandParser.miniParse(parseText.getValue());
        } catch (CommandException | UserException ex) {
          showError(ex.getMessage());
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