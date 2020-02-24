package slogo.View;

import java.util.ResourceBundle;
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
  private static int WINDOW_WIDTH = 1400;
  private static int WINDOW_HEIGHT = 1000;
  private Stage myWindow;

  private CommandHistory myCommandHistory;
  private VariableHistory myVariableHistory;
  private BorderPane bp;
  private Turtle viewTurtle;
  private CommandLine cmdline;
  private Drawing drawer;
  private TurtleGrid grid;
  private Toolbar tool;

  /**
   * Constructor for the visualizer class
   * @param window
   */
  public Visualizer(Stage window, Turtle viewTurtle){
    myWindow = window;
    myCommandHistory = new CommandHistory();
    myVariableHistory = new VariableHistory();

//        ViewButton btn = new ViewButton("hi", 50, 100);
    cmdline = new CommandLine();
    this.viewTurtle = viewTurtle;
    drawer = new Drawing();
    grid = new TurtleGrid(viewTurtle, drawer);
    tool = new Toolbar(drawer, grid);
    bp = new BorderPane();

    setupBorderPane();

    Scene scene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
    window.setScene(scene);
    window.show();
  }

  private void setupBorderPane() {
    bp.setBottom(cmdline.getCommandLineGroup());
    bp.setLeft(grid.getTurtleGrid());
    bp.setTop(tool.ToolBar());
    makeHistory();
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
