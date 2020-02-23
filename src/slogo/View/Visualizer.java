package slogo.View;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.model.Turtle;

public class Visualizer {

  public static final String RESOURCES = "resources";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
//  public static final String LANGUAGE = "English";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
//  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "DisplayEnglish");
  private Stage myWindow;

  /**
   * Constructor for the visualizer class
   * @param window
   */
  public Visualizer(Stage window){
    myWindow = window;

//        ViewButton btn = new ViewButton("hi", 50, 100);
    CommandLine cmdline = new CommandLine();
    Turtle turtle = new Turtle();
    Drawing drawer = new Drawing();
    TurtleGrid grid = new TurtleGrid(turtle, drawer);
    Toolbar tool = new Toolbar(drawer, grid);


    BorderPane bp = new BorderPane();
    bp.setBottom(cmdline.getCommandLineGroup());
    bp.setCenter(grid.getTurtleGrid());

    bp.setTop(tool.ToolBar());
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER);

    Scene scene = new Scene(bp, 1000, 1000);
//    vbox.getChildren().addAll(tool.ToolBar(), cmdline.getCommandLineGroup());
    window.setScene(scene);
    window.show();
  }
}
