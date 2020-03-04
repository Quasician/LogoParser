package slogo.View;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import slogo.Main;
import slogo.model.CommandParser;
import slogo.model.DisplayOption;
import slogo.model.Turtle;
import slogo.model.VariableHashMap;
import slogo.model.xml.XMLCreator;
import slogo.model.xml.XMLParser;

public class Visualizer {

  public static final int BUTTON_HEIGHT = 80;
  public static final double BUTTON_WIDTH = 200.0;
  private ResourceBundle myResources = Main.myResources;
  private static int WINDOW_WIDTH = 1500;
  private static int WINDOW_HEIGHT = 1000;
  private Stage myWindow;
  private CommandHistory myCommandHistory;
  private OutputView myOutputView;
  private UserDefinedCommands myUserDefined;
  private VariableHistory myVariableHistory;
  private Configuration myConfig;
  private BorderPane bp;
  private ObservableList<Turtle> viewTurtles;
  private ImageView buttonImage;
  private CommandParser comParser;
  private Map<String, String> VarMap;
  private javafx.scene.image.Image img;
  private static final String style = "-fx-background-color: rgba(0, 0, 0, 0.7);";
  private ObservableMap myMap;
  private DisplayOption displayOption;
  private Toolbar tool;

  /**
   * Constructor for the visualizer class
   *
   * @param window '
   * @param myMap
   */
  public Visualizer(Stage window, ObservableList<Turtle> viewTurtles,
      StringProperty commandLineText,
      BooleanProperty textUpdate, Language language, CommandParser parser,
      ObservableMap myMap) {
    myWindow = window;
    comParser = parser;
    myCommandHistory = new CommandHistory(comParser);
    myOutputView= new OutputView();
    myUserDefined = new UserDefinedCommands(comParser);
    myVariableHistory = new VariableHistory();
    myConfig = new Configuration();
    this.viewTurtles = viewTurtles;
    img = new Image(myResources.getString("SlogoLogo"));
    buttonImage = new ImageView(img);
    buttonImage.setFitHeight(BUTTON_HEIGHT);
    buttonImage.setFitWidth(BUTTON_WIDTH);
    TurtleGrid grid = new TurtleGrid(this.viewTurtles);
    tool = new Toolbar(grid, language);
    CommandLine cmdline = new CommandLine(commandLineText, textUpdate, grid);
    setUpBorderPane(grid, cmdline, tool);
    makeHistory();
    this.myMap = myMap;
    setUpMapListener();
    Scene scene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
    buttonImage.isFocused();
//    addKeyHandler(scene,grid);
    window.setScene(scene);
    window.show();
    addSizeListener();
    addKeyHandler(scene, grid);
  }

  public void setDisplayOption(DisplayOption d) {
    tool.bindWithDisplayOption(d);
  }

  //TODO: CHANGE THIS
  private void getXML() {
    FileChooser fileChooser = new FileChooser();
    File xml = fileChooser.showOpenDialog(myWindow);
    XMLParser parser = new XMLParser(xml);
    parser.setUp();
    List<String> commands = parser.getCommands();
    String[] array = commands.toArray(new String[0]);
    String str = String.join("\n", array);
    System.out.println(str);
    comParser.parseText(str);
  }

  private void saveXML() {
    XMLCreator creator = new XMLCreator(myCommandHistory.getCommandListCopy());
    creator.createFile("Title");
  }



  private void addKeyHandler(Scene scene, TurtleGrid grid) {
    scene.setOnKeyPressed(ke -> {
      KeyCode keyCode = ke.getCode();
//      if (keyCode== KeyCode.RIGHT) {
//        grid.getTurtleImage().get(0).setX(100);
//      }
//      if(keyCode==KeyCode.LEFT){
//        grid.getTurtleImage().get(0).setX(50);
//      }
//      if(keyCode== KeyCode.J){
//        grid.getTurtleImage().get(0).setY(50);
//      }
//      if(keyCode== KeyCode.N){
//        grid.getTurtleImage().get(0).setY(30);
//      }
      if (keyCode == KeyCode.ENTER) {
        getXML(); //TODO: CHANGE THIS LATER
      }
      if (keyCode == KeyCode.Q) {
        saveXML();
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
          //setUpTurtle(changedTurtle);
        }
      }
    });
  }

  private void setUpMapListener() {
    myMap.addListener(new MapChangeListener<String, String>() {
                        @Override
                        public void onChanged(Change<? extends String, ? extends String> change) {
                          System.out.println(change.getKey() + " " + change.getValueAdded());
                          VariableHashMap.addToMap(change.getKey(), change.getValueAdded());
                        }
                      }
    );
  }

  private void setUpBorderPane(TurtleGrid grid, CommandLine commandLine, Toolbar tool) {
    bp = new BorderPane();
    bp.setBackground(Background.EMPTY);
    bp.setStyle(style);
    bp.setBottom(commandLine.getCommandLineGroup());
    bp.setLeft(grid.getTurtleGrid());
    bp.setTop(tool.getToolBar());
  }

  private void makeHistory() {
    VBox historyVBox = new VBox();
    historyVBox.setAlignment(Pos.CENTER);
    Button showCommand= new ViewButton("Command",45,65,10);
    Node toDisplay= myCommandHistory.returnScene();
    Node outputView= myOutputView.returnScene();
    Button showVariable= new ViewButton("Variable",45,65,10);
    Button showCustomCommands= new ViewButton("Custom",45,65,10);
    Button showProperties= new ViewButton("Properties",45,65,10);
    Button saveConfig= new ViewButton("Save", 45, 65, 10);
    Button uploadConfig= new ViewButton("Upload",45,65,10);
    HBox buttonsForPanes= new HBox();
    buttonsForPanes.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 20), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonsForPanes.getChildren().addAll(showCommand,showVariable,showCustomCommands,showProperties,saveConfig,uploadConfig);
    historyVBox.getChildren()
        .addAll(buttonImage, buttonsForPanes, toDisplay, outputView);
    saveConfig.setOnAction(e -> saveXML());
    uploadConfig.setOnAction(e -> getXML());
    showCommand.setOnAction(e -> setShowCommand(historyVBox));
    showVariable.setOnAction(e -> setShowVariable(historyVBox));
    showCustomCommands.setOnAction(e -> setShowCustom(historyVBox));
    showProperties.setOnAction(e -> setShowProperties(historyVBox));
    bp.setRight(historyVBox);
  }

  private void setShowProperties(VBox historyVBox) {
    historyVBox.getChildren()
        .removeAll(myCommandHistory.returnScene(), myVariableHistory.getScene(),
            myUserDefined.returnScene());
    if (!historyVBox.getChildren().contains(myConfig.getScene())) {
      historyVBox.getChildren().add(myConfig.getScene());
    }
  }

  private void setShowCustom(VBox historyVBox) {
    historyVBox.getChildren()
        .removeAll(myCommandHistory.returnScene(), myVariableHistory.getScene(),
            myConfig.getScene());
    if (!historyVBox.getChildren().contains(myUserDefined.returnScene())) {
      historyVBox.getChildren().add(myUserDefined.returnScene());
    }
  }

  private void setShowVariable(VBox historyVBox) {
    historyVBox.getChildren().removeAll(myCommandHistory.returnScene(), myUserDefined.returnScene(),
        myConfig.getScene());
    if (!historyVBox.getChildren().contains(myVariableHistory.getScene())) {
      historyVBox.getChildren().add(myVariableHistory.getScene());
    }
  }

  private void setShowCommand(VBox historyVBox) {
    historyVBox.getChildren()
        .removeAll(myVariableHistory.getScene(), myUserDefined.returnScene(), myConfig.getScene());
    if (!historyVBox.getChildren().contains(myCommandHistory.returnScene())) {
      historyVBox.getChildren().add(myCommandHistory.returnScene());
    }
  }


  public void makeNewBox(String newCommand) {
    myCommandHistory.makeBox(newCommand);
    Button trial = myCommandHistory.returnButton();
    trial.setOnAction(e -> comParser.parseText(newCommand));
  }

  public void makeNewTerminalBox(String parseText) {
    myOutputView.makeBox(parseText);
  }
}
