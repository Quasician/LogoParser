package slogo.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import slogo.Main;
import slogo.model.CommandParser;
import slogo.model.Turtle;

public class Visualizer {

  public static final int BUTTON_HEIGHT = 80;
  public static final double BUTTON_WIDTH = 200.0;
  private ResourceBundle myResources = Main.myResources;
  private static int WINDOW_WIDTH = 1500;
  private static int WINDOW_HEIGHT = 1000;
  private Stage myWindow;
  private CommandHistory myCommandHistory;
  private VariableHistory myVariableHistory;
  private BorderPane bp;
  private ObservableList<Turtle> viewTurtles;
  private ImageView buttonImage;
  private CommandParser comParser;
  private Map<String,String> VarMap;
  private javafx.scene.image.Image img;
  private static final String style = "-fx-background-color: rgba(0, 0, 0, 0.7);";

  /**
   * Constructor for the visualizer class
   *
   * @param window '
   */
  public Visualizer(Stage window, ObservableList<Turtle> viewTurtles, StringProperty commandLineText,
                    BooleanProperty textUpdate, Language language, CommandParser parser) {
    myWindow = window;
    comParser=parser;
    myCommandHistory = new CommandHistory(comParser);
    myVariableHistory = new VariableHistory();
    this.viewTurtles = viewTurtles;
    img = new Image(myResources.getString("SlogoLogo"));
    buttonImage = new ImageView(img);
    buttonImage.setFitHeight(BUTTON_HEIGHT);
    buttonImage.setFitWidth(BUTTON_WIDTH);
    CommandLine cmdline = new CommandLine(commandLineText, textUpdate);
    TurtleGrid grid = new TurtleGrid(this.viewTurtles);
    Toolbar tool = new Toolbar(grid, language);
    setUpBorderPane(grid, cmdline, tool);
    makeHistory();
    Scene scene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
    window.setScene(scene);
    window.show();
    addSizeListener();
  }

  private void addSizeListener()
  {
    viewTurtles.addListener(new ListChangeListener<Turtle>() {
      @Override
      public void onChanged(Change<? extends Turtle> c) {
        c.next();
        List<Turtle> newTurtles = (List<Turtle>) c.getAddedSubList();
        System.out.println("View turtles changed in turtle grid");
        for(Turtle changedTurtle:newTurtles) {
          System.out.println("NEW VIEW turtle: " + changedTurtle.isActivatedProperty().getValue());
          //setUpTurtle(changedTurtle);
        }
      }
    });
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
    Button showVariable= new ViewButton("Variable",45,65,10);
    HBox buttonsForPanes= new HBox();
    buttonsForPanes.getChildren().addAll(showCommand,showVariable);
    historyVBox.getChildren()
            .addAll(buttonImage,buttonsForPanes,toDisplay);
    showCommand.setOnAction(e->setShowCommand(historyVBox));
    showVariable.setOnAction(e->setShowVariable(historyVBox));
    bp.setRight(historyVBox);
  }

  private void setShowVariable(VBox historyVBox) {
    historyVBox.getChildren().remove(myCommandHistory.returnScene());
      if(!historyVBox.getChildren().contains(myVariableHistory.getScene())){
        historyVBox.getChildren().add(myVariableHistory.getScene());}
  }

  private void setShowCommand(VBox historyVBox) {
    historyVBox.getChildren().remove(myVariableHistory.getScene());
      if(!historyVBox.getChildren().contains(myCommandHistory.returnScene())){
        historyVBox.getChildren().add(myCommandHistory.returnScene());}
  }


  public void makeNewBox(String newCommand){
    myCommandHistory.makeBox(newCommand);
    Button trial= myCommandHistory.returnButton();
    trial.setOnAction(e->comParser.parseText(newCommand));
  }

  public void makeNewVariableBox(ObservableMap<String,String> newMap){
    for(String variableKey :newMap.keySet()) {
      myVariableHistory.addVariable(variableKey, newMap.get(variableKey));
    }
  }

}
