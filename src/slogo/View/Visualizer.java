package slogo.View;

import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import slogo.Main;
import slogo.model.Turtle;

public class Visualizer {
  public static final String RESOURCES = "resources";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
//  public static final String LANGUAGE = "English";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
  public static final int BUTTON_HEIGHT = 80;
  public static final double BUTTON_WIDTH = 200.0;
  //  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "DisplayEnglish");
  private ResourceBundle myResources = Main.myResources;
  private static int WINDOW_WIDTH = 1000;
  private static int WINDOW_HEIGHT = 500;
  private Stage myWindow;

  private CommandHistory myCommandHistory;
  private VariableHistory myVariableHistory;
  private BorderPane bp;
  private Turtle viewTurtle;
  private ImageView buttonImage;
  private javafx.scene.image.Image img;
  private static final String style="-fx-background-color: rgba(0, 0, 0, 0.7);";

  /**
   * Constructor for the visualizer class
   * @param window
'   */
  public Visualizer(Stage window, Turtle viewTurtle, StringProperty commandLineText, BooleanProperty textUpdate){
    myWindow = window;
    myCommandHistory = new CommandHistory();
    myVariableHistory = new VariableHistory();

//        ViewButton btn = new ViewButton("hi", 50, 100);
    CommandLine cmdline = new CommandLine(commandLineText, textUpdate);
    this.viewTurtle = viewTurtle;
    Drawing drawer = new Drawing();
    img = new Image(myResources.getString("SlogoLogo"));
    buttonImage = new ImageView(img);
    buttonImage.setFitHeight(BUTTON_HEIGHT);
    buttonImage.setFitWidth(BUTTON_WIDTH);
    TurtleGrid grid = new TurtleGrid(viewTurtle, drawer);
    Toolbar tool = new Toolbar(drawer, grid);
    setUpBorderPane(grid, cmdline, tool);
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER);
    makeHistory();
    Scene scene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);

//    vbox.getChildren().addAll(tool.getToolBar(), cmdline.getCommandLineGroup());
    window.setScene(scene);
    window.show();
  }

  private void setUpBorderPane(TurtleGrid grid, CommandLine commandLine, Toolbar tool) {
    bp = new BorderPane();
    bp.setBackground(Background.EMPTY);
    bp.setStyle(style);
    bp.setBottom(commandLine.getCommandLineGroup());
    bp.setLeft(grid.getTurtleGrid());
    bp.setTop(tool.getToolBar());
  }

  private void makeHistory(){
    VBox historyVBox = new VBox();
    historyVBox.setAlignment(Pos.CENTER);
    myCommandHistory.makeBox("Command 1");
    myCommandHistory.makeBox("Command 2");
    myVariableHistory.addVariable("Variable 1",5);
    myVariableHistory.addVariable("Variable 2",5);
    historyVBox.getChildren().addAll(buttonImage,myVariableHistory.getScene(),myCommandHistory.returnScene());
    bp.setRight(historyVBox);
  }
}
