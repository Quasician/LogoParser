package slogo.View;

import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import slogo.Main;
import slogo.model.*;
import slogo.model.Commands.Command;

public class Visualizer {

  private static int WINDOW_WIDTH = 1500;
  private static int WINDOW_HEIGHT = 1000;
  private BorderPane bp;
  private ObservableList<Turtle> viewTurtles;
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
  private static final String Error="No Commands Found to Undo";
  private static final String space =" ";
  private static final int colorRed=10;
  private static final int colorGreen=10;
  private static final int colorBlue=20;
  private ObservableMap myMap;
  private DisplayOption displayOption;
  private Toolbar tool;
  private TurtleGrid grid;
  private Configuration config;
  private HistoryPanel myHistoryPanel;
  public static final int SLOGO_IMAGE_HEIGHT = 80;
  public static final double SLOGO_IMAGE_WIDTH = 200.0;
  private ResourceBundle myResources = Main.myResources;
  private ImageView slogoImage;


  /**
   * Constructor for the visualizer class
   *
   */
  public Visualizer(Stage window, ObservableList viewTurtles, ObservableList activatedTurtles,
      StringProperty commandLineText,
      BooleanProperty textUpdate, Language language, CommandParser parser,
      ObservableMap myMap, DisplayOption d) {
//    myWindow = window;
    this.viewTurtles = viewTurtles;
    config= new Configuration(viewTurtles, d);
    myHistoryPanel = new HistoryPanel(window, viewTurtles, parser, config);
    grid = new TurtleGrid(viewTurtles, config);
    tool = new Toolbar(grid, language, activatedTurtles);
    CommandLine cmdline = new CommandLine(commandLineText, textUpdate, grid, activatedTurtles);
    this.myMap = myMap;
    setUpMapListener();
    img = new Image(myResources.getString("SlogoLogo"));
    slogoImage = new ImageView(img);
    slogoImage.setFitHeight(SLOGO_IMAGE_HEIGHT);
    slogoImage.setFitWidth(SLOGO_IMAGE_WIDTH);
    slogoImage.isFocused();
    setUpBorderPane(grid, cmdline, tool);
    Scene scene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
    window.setScene(scene);
    window.show();
    addSizeListener();
//    addKeyHandler(scene, grid);
  }

  public void setDisplayOption(DisplayOption d) {
    tool.bindWithDisplayOption(d);
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

  // TODO need to change since this isnt used anywhere
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
    bp.setStyle(myResources.getString(style));
    bp.setBottom(commandLine.getCommandLineGroup());
    bp.setLeft(grid.getTurtleGrid());
    bp.setTop(tool.getToolBar());
    VBox rightSide = new VBox(10);
    rightSide.setAlignment(Pos.CENTER);
    rightSide.getChildren().addAll(slogoImage, myHistoryPanel.returnScene());
    bp.setRight(rightSide);
  }
  //i know this isnt good design since its being passed into "2 levels," but im gonna leave it until i find a better way to refactor
  public void makeNewBox(String value) {
    myHistoryPanel.makeNewBox(value);
  }

  //i know this isnt good design since its being passed into "2 levels," but im gonna leave it until i find a better way to refactor
  public void makeNewTerminalBox(String parseText) {
    myHistoryPanel.makeNewTerminalBox(parseText);
  }
}
