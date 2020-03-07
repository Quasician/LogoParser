package slogo.View;

import java.util.List;

import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
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

public class Visualizer {
  private static final int WINDOW_WIDTH = 1500;
  private static final int WINDOW_HEIGHT = 1000;
  private BorderPane bp;
  private ObservableList<Turtle> viewTurtles;
  private ObservableMap<String,String> variables;
  private javafx.scene.image.Image img;
  private static final String STYLE = "Style";
  private DisplayOption displayOption;
  private Toolbar tool;
  private TurtleGrid grid;
  private PropertiesHolder config;
  private HistoryPanel myHistoryPanel;
  private static final int SPACING =10;
  public static final int SLOGO_IMAGE_HEIGHT = 80;
  public static final double SLOGO_IMAGE_WIDTH = 200.0;
  private static final ResourceBundle MY_RESOURCES = Main.MY_RESOURCES;
  private static final String IMAGE_STRING= MY_RESOURCES.getString("SlogoLogo");
  private ImageView slogoImage;
  private BooleanProperty checkMin;
  private ObservableList<Triplet<String, String, String>> customCommandList;

  /**
   * Constructor for the visualizer class
   *
   */
  public Visualizer(Display display, ActivityListeners activityListeners, Observables lists, ObservableList<Triplet<String, String, String>> customCommandList) {
    this.viewTurtles =lists.getViewTurtleList();
    this.customCommandList = customCommandList;
    this.variables = lists.getViewObservableMap();
    config= new PropertiesHolder(viewTurtles, display.getOptions());
    myHistoryPanel = new HistoryPanel(display.getUserStage(),config,variables, customCommandList, display.getCommandText(),activityListeners);
    grid = new TurtleGrid(viewTurtles, config,lists.getActiveTurtleList());
    tool = new Toolbar(grid, display.getDisplayLang(), lists.getActiveTurtleList());
    CommandLine cmdline = new CommandLine(display.getCommandText(), activityListeners, lists.getActiveTurtleList());
    img = new Image(IMAGE_STRING);
    slogoImage = new ImageView(img);
    slogoImage.setFitHeight(SLOGO_IMAGE_HEIGHT);
    slogoImage.setFitWidth(SLOGO_IMAGE_WIDTH);
    slogoImage.isFocused();
    setUpBorderPane(grid, cmdline, tool);
    Scene scene = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
    display.getUserStage().setScene(scene);
    display.getUserStage().show();
    addSizeListener();
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
      }
    });
  }

  private void setUpBorderPane(TurtleGrid grid, CommandLine commandLine, Toolbar tool) {
    bp = new BorderPane();
    bp.setBackground(Background.EMPTY);
    bp.setStyle(MY_RESOURCES.getString(STYLE));
    bp.setBottom(commandLine.getCommandLineGroup());
    bp.setLeft(grid.getTurtleGrid());
    bp.setTop(tool.getToolBar());
    VBox rightSide = new VBox(SPACING);
    rightSide.setAlignment(Pos.CENTER);
    rightSide.getChildren().addAll(slogoImage, myHistoryPanel.returnScene());
    bp.setRight(rightSide);
  }
  public void makeNewBox(String value) {
    myHistoryPanel.makeNewBox(value);
  }
  public void makeNewTerminalBox(String parseText) {
    myHistoryPanel.makeNewTerminalBox(parseText);
  }
}
