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

  public static final int BUTTON_HEIGHT = 80;
  public static final double BUTTON_WIDTH = 200.0;
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
    this.viewTurtle = viewTurtle;
    img = new Image(myResources.getString("SlogoLogo"));
    buttonImage = new ImageView(img);
    buttonImage.setFitHeight(BUTTON_HEIGHT);
    buttonImage.setFitWidth(BUTTON_WIDTH);

    CommandLine cmdline = new CommandLine(commandLineText, textUpdate);
    Drawing drawer = new Drawing();
    TurtleGrid grid = new TurtleGrid(viewTurtle, drawer);
    Toolbar tool = new Toolbar(grid);
    setUpBorderPane(grid, cmdline, tool);
    makeHistory();
    Scene scene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
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
