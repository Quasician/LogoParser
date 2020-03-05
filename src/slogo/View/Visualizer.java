package slogo.View;

import java.io.File;
import java.util.*;

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
import slogo.model.Commands.Command;
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
  private static final String nameofImage= "SlogoLogo";
  private static final String delimiter= "\n";
  private static final String Command = "Command";
  private static final String Variable = "Variable";
  private static final String Custom = "Custom";
  private static final String Properties = "Properties";
  private static final String Save = "Save";
  private static final String Upload = "Upload";
  private static final String style = "Style";
  private static final String title= "Title";
  private static final String UndoCommand= "Undo";
  private static final String space =" ";
  private static final int colorRed=10;
  private static final int colorGreen=10;
  private static final int colorBlue=20;
  private ObservableMap myMap;
  private DisplayOption displayOption;
  private Toolbar tool;
  private TurtleGrid grid;

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
    myUserDefined = new UserDefinedCommands();
    myVariableHistory = new VariableHistory();
    myConfig = new Configuration(viewTurtles);
    this.viewTurtles = viewTurtles;
    img = new Image(myResources.getString(nameofImage));
    buttonImage = new ImageView(img);
    buttonImage.setFitHeight(BUTTON_HEIGHT);
    buttonImage.setFitWidth(BUTTON_WIDTH);
    grid = new TurtleGrid(this.viewTurtles);
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
    String str = String.join(delimiter, array);
    System.out.println(str);
    comParser.parseText(str);
  }

  private void saveXML() {
    XMLCreator creator = new XMLCreator(myCommandHistory.getCommandListCopy());
    creator.createFile(myResources.getString(title));
  }




  private void addKeyHandler(Scene scene, TurtleGrid grid) {
    scene.setOnKeyPressed(ke -> {
      KeyCode keyCode = ke.getCode();
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
//        System.out.println("View turtles changed in turtle grid");
        for (Turtle changedTurtle : newTurtles) {
//          System.out.println("NEW VIEW turtle: " + changedTurtle.isActivatedProperty().getValue());
          //setUpTurtle(changedTurtle);
        }
      }
    });
  }

  private void setUpMapListener() {
    myMap.addListener(new MapChangeListener<String, String>() {
                        @Override
                        public void onChanged(Change<? extends String, ? extends String> change) {
                          System.out.println(change.getKey() + space + change.getValueAdded());
                          VariableHashMap.addToMap(change.getKey(), change.getValueAdded());
                        }
                      }
    );
  }

  private void setUpBorderPane(TurtleGrid grid, CommandLine commandLine, Toolbar tool) {
    bp = new BorderPane();
    bp.setBackground(Background.EMPTY);
    bp.setStyle(myResources.getString(style));
    bp.setBottom(commandLine.getCommandLineGroup());
    bp.setLeft(grid.getTurtleGrid());
    bp.setTop(tool.getToolBar());
  }

  private void makeHistory() {
    VBox historyVBox = new VBox();
    historyVBox.setAlignment(Pos.CENTER);
    Button showCommand= new ViewButton(myResources.getString(Command));
    Node toDisplay= myCommandHistory.returnScene();
    Node outputView= myOutputView.returnScene();
    Button showVariable= new ViewButton(myResources.getString(Variable));
    Button showCustomCommands= new ViewButton(myResources.getString(Custom));
    Button showProperties= new ViewButton(myResources.getString(Properties));
    Button saveConfig= new ViewButton(myResources.getString(Save));
    Button uploadConfig= new ViewButton(myResources.getString(Upload));
    Button Undo= new ViewButton(myResources.getString(UndoCommand));
    HBox buttonsForPanes= new HBox();
    buttonsForPanes.setBackground(new Background(new BackgroundFill(Color.rgb(colorRed, colorGreen, colorBlue), CornerRadii.EMPTY, Insets.EMPTY)));
    buttonsForPanes.getChildren().addAll(showCommand,showVariable,showCustomCommands,showProperties,Undo,saveConfig,uploadConfig);
    historyVBox.getChildren()
        .addAll(buttonImage, buttonsForPanes, toDisplay, outputView);
    saveConfig.setOnAction(e -> saveXML());
    uploadConfig.setOnAction(e -> getXML());
    Undo.setOnAction(e->undoCommand());
    showCommand.setOnAction(e -> setShowCommand(historyVBox));
    showVariable.setOnAction(e -> setShowVariable(historyVBox));
    showCustomCommands.setOnAction(e -> setShowCustom(historyVBox));
    showProperties.setOnAction(e -> setShowProperties(historyVBox));
    bp.setRight(historyVBox);
  }

  private void undoCommand() {
    comParser.parseText("clearscreen");
    try {
      myCommandHistory.removeCommand();
    } catch (IndexOutOfBoundsException e){
      String error= e.getMessage();
      makeNewTerminalBox(error);
    }
    for(String command:myCommandHistory.getCommandListCopy()){
      comParser.parseText(command);
    }
  }

  private void setShowProperties(VBox historyVBox) {
    historyVBox.getChildren()
        .removeAll(myCommandHistory.returnScene(), myVariableHistory.getScene(),
            myUserDefined.returnScene(), myOutputView.returnScene());
    if (!historyVBox.getChildren().contains(myConfig.getScene())) {
      historyVBox.getChildren().addAll(myConfig.getScene(),myOutputView.returnScene());
    }
  }

  private void setShowCustom(VBox historyVBox) {
    historyVBox.getChildren()
        .removeAll(myCommandHistory.returnScene(), myVariableHistory.getScene(),
            myConfig.getScene(),myOutputView.returnScene());
    if (!historyVBox.getChildren().contains(myUserDefined.returnScene())) {
      historyVBox.getChildren().addAll(myUserDefined.returnScene(),myOutputView.returnScene());
    }
  }

  private void setShowVariable(VBox historyVBox) {
    historyVBox.getChildren().removeAll(myCommandHistory.returnScene(), myUserDefined.returnScene(),
        myConfig.getScene(),myOutputView.returnScene());
    if (!historyVBox.getChildren().contains(myVariableHistory.getScene())) {
      historyVBox.getChildren().addAll(myVariableHistory.getScene(),myOutputView.returnScene());
    }
  }

  private void setShowCommand(VBox historyVBox) {
    historyVBox.getChildren()
        .removeAll(myVariableHistory.getScene(), myUserDefined.returnScene(), myConfig.getScene(),myOutputView.returnScene());
    if (!historyVBox.getChildren().contains(myCommandHistory.returnScene())) {
      historyVBox.getChildren().addAll(myCommandHistory.returnScene(),myOutputView.returnScene());
    }
  }


  public void makeNewBox(String newCommand) {
    myCommandHistory.makeBox(newCommand);
    Button trial = myCommandHistory.returnButton();
    trial.setOnAction(e -> {comParser.parseText(newCommand); makeNewBox(newCommand);});
  }

  public void makeNewTerminalBox(String parseText) {
    myOutputView.makeBox(parseText);
  }
}
