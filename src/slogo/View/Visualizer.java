package slogo.View;

import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.lang.model.element.Element;
import slogo.model.Turtle;

public class Visualizer {

  public static final String RESOURCES = "resources";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
//  public static final String LANGUAGE = "English";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
//  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "DisplayEnglish");

  private static int WINDOW_WIDTH = 1200;
  private static int WINDOW_HEIGHT = 800;
  private Stage myWindow;
  private CommandHistory myCommandHistory;
  private VariableHistory myVariableHistory;
  private BorderPane bp;
  private Turtle viewTurtle;


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
    TurtleGrid grid = new TurtleGrid(this.viewTurtle, drawer);

    Toolbar tool = new Toolbar(drawer, grid);

    bp = new BorderPane();
    bp.setBottom(cmdline.getCommandLineGroup());
    bp.setLeft(grid.getTurtleGrid());

    bp.setTop(tool.ToolBar());
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER);

    makeHistory();

    Scene scene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
//    vbox.getChildren().addAll(tool.ToolBar(), cmdline.getCommandLineGroup());
    window.setScene(scene);
    window.show();
  }

  private void makeHistory(){
    VBox historyVBox = new VBox();
    historyVBox.setAlignment(Pos.CENTER);
    myCommandHistory.makeBox("hellghvhgvhgvjhvjhbjbjbkjbkjbvhgcgfxhgchgvhvjhhjfgvhgchhkjbkjhkjgcuyfcvhklhkhco");
    myCommandHistory.makeBox("he");
    myVariableHistory.addVariable("avsd",5);
    myVariableHistory.addVariable("avsdgcvghvg",5);
    historyVBox.getChildren().addAll(myVariableHistory.getScene(),myCommandHistory.returnScene());
    bp.setRight(historyVBox);

  }
}
