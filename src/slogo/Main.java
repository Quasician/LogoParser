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
import slogo.View.Visualizer;
import slogo.model.*;
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
  private ObservableMap myMap = FXCollections.observableMap(new HashMap<String, String>());
  private ObservableList varList;
  //  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
  public static ResourceBundle myResources = ResourceBundle
      .getBundle(DEFAULT_RESOURCE_PACKAGE + "DisplayEnglish");

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    TurtleList.createTurtleLists(FXCollections.observableArrayList(),
        FXCollections.observableArrayList());
    Turtle modelTurtle1 = new Turtle();
    Turtle modelTurtle2 = new Turtle();
    TurtleList.addTurtleToModelList(modelTurtle1);
    TurtleList.addTurtleToModelList(modelTurtle2);

    Language language = new Language();
    DisplayOption displayOption = new DisplayOption();
    CommandParser commandParser = new CommandParser(TurtleList.getModelTurtleList(), language);
    commandParser.setDisplayOption(displayOption);
    StringProperty commandLineText = new SimpleStringProperty();
    StringProperty parseString = new SimpleStringProperty();
    parseString.bind(commandLineText);
    BooleanProperty textUpdate = new SimpleBooleanProperty();

    VariableHashMap.createMap(myMap);

    Visualizer vis = new Visualizer(primaryStage, TurtleList.getViewTurtleList(), commandLineText,
        textUpdate, language, commandParser, myMap);
    vis.setDisplayOption(displayOption);

    parseTextOnInput(textUpdate, parseString, commandParser, vis);

    TurtleList.makeModelTurtleActivated(1);
    TurtleList.makeModelTurtleDeactivated(2);
    //commandParser.parseText("fd 100");
    TurtleList.makeModelTurtleActivated(1);
    TurtleList.makeModelTurtleActivated(2);
    commandParser.parseText("atan 1");
//    commandParser.parseText("tell [ 4 ] turtles id ");
//    commandParser.parseText("tell [ 2 ]");
//    commandParser.parseText("left 90 fd 100 ");
    //commandParser.parseText("ask [ 2 4 ] [ fd 50 rt 45 ]");
    //commandParser.parseText("askwith [ less? xcor 75 ] [ rt 270 ]");
    //commandParser.parseText("fd 100");
    //commandParser.parseText("fd 50");
    for (Turtle turtle : TurtleList.getModelTurtleList()) {
      System.out.println(
          "MODELTurtle " + turtle.getId() + " x: " + turtle.getX() + " y: " + turtle.getY()
              + " Angle: " + turtle.getDegree() + " Activated: "+ turtle.isActivatedProperty().getValue());
    }

//        varList = FXCollections.observableList(Arrays.asList(myMap.keySet()));
   // parseTextOnInput(textUpdate, parseString, commandParser, vis);
  }

  private void printVariables() {
    Iterator it = VariableHashMap.getAllVariables().iterator();
    System.out.println("\nTHESE ARE THE CURRENT VARIABLES: ");
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next(); //current entry in a loop
      System.out.println(entry.getKey() + " = " + entry.getValue());
    }
  }

  private void printCustomCommands() {
    Iterator it = CustomCommandMap.getAllCustomCommands().iterator();
    System.out.println("\nTHESE ARE THE CURRENT CUSTOM COMMANDS: ");
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next(); //current entry in a loop
      System.out.println("CUSTOM COMMAND " + entry.getKey() + " = " + entry.getValue());
    }
  }

  private void bindTurtles(Turtle model, Turtle view) {
    view.distanceProperty().bind(model.distanceProperty());
    view.angleProperty().bind(model.angleProperty());
    view.isPenDownProperty().bind(model.isPenDownProperty());
    view.isShowingProperty().bind(model.isShowingProperty());
    view.coordinatesProperty().bind(model.coordinatesProperty());
    view.clearScreenProperty().bind(model.clearScreenProperty());
  }


  //Sanna changed this method to do error checking
  private void parseTextOnInput(BooleanProperty textUpdate, StringProperty parseText,
      CommandParser commandParser, Visualizer vis) {
    textUpdate.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        System.out.println(parseText.getValue());
        //commandParser.parseText(parseText.getValue());
        //vis.makeNewBox(parseText.getValue());

        try {
          commandParser.parseText(parseText.getValue());
          vis.makeNewBox(parseText.getValue());
//                    vis.makeNewVariableBox(myMap);
        } catch (CommandException | UserException e) {
          showError(e.getMessage());
        }
      }
    });
  }

  private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Alert test - error");
    alert.setContentText(message);
    alert.showAndWait();
  }

}


//        commandParser.parseText("to c [ :f ] [ repeat 5 [ rt 25 ]  ]");
//        printCustomCommands();
//        System.out.println("done printing");
//        commandParser.parseText(" c 1 ");
// commandParser.parseText("rt 45 fd 50");

 // Turtle modelTurtle = new Turtle();
//    Turtle viewTurtle = new Turtle();
//    bindTurtles(modelTurtle, viewTurtle);


// TurtleList.makeModelTurtleActivated(1);
//commandParser.parseText("fd 50");


// for (Turtle turtle : TurtleList.getModelTurtleList()) {
//     System.out.println(
//     "MODELTurtle " + turtle.getId() + " x: " + turtle.getX() + " y: " + turtle.getY()
//     + " Activated: " + turtle.isActivatedProperty().getValue());
//     }
//
//     for (Turtle turtle : TurtleList.getViewTurtleList()) {
//     System.out.println(
//     "VIEWTurtle " + turtle.getId() + " x: " + turtle.getX() + " y: " + turtle.getY()
//     + " Activated: " + turtle.isActivatedProperty().getValue());
//     }


//ObjectProperty<Turtle> modelTurtleProp = new SimpleObjectProperty<>(modelTurtle, "modelTurtle");
//ObjectProperty<Turtle> viewTurtleProp = new SimpleObjectProperty<>(viewTurtle, "viewTurtle");
// viewTurtleProp.bind(modelTurtleProp);

//        commandParser.parseText("to c [ :f ] [ repeat 5 [ rt 25 ]  ]");
//        printCustomCommands();
//        System.out.println("done printing");
//        commandParser.parseText(" c 1 ");
// commandParser.parseText("rt 45 fd 50");
//commandParser.parseText("fd 50");
//commandParser.parseText("rt 50 fd 50 ");