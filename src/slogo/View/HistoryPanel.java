package slogo.View;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.model.CommandParser;
import slogo.model.Turtle;
import slogo.model.xml.XMLCreator;
import slogo.model.xml.XMLParser;

/**
 * This class holds all the possible history views and their button tabs so that the user can switch between views
 */

public class HistoryPanel implements HistoryView{

  private VBox historyVBox;
  private CommandHistory myCommandHistory;
  private OutputView myOutputView;
  private UserDefinedCommands myUserDefined;
  private VariableHistory myVariableHistory;
  private Configuration myConfig;
  private CommandParser comParser;
  private final String buttonNameStr = "resources/HistoryView/HistoryButtonNames";
  private final String buttonMethodStr = "resources/HistoryView/HistoryButtonMethods";
  private ResourceBundle buttonNamesResources = ResourceBundle.getBundle(buttonNameStr);
  private ResourceBundle buttonMethodsResources = ResourceBundle.getBundle(buttonMethodStr);
  private List<String> buttonNames;
  private HBox buttonsForPanes;

  private final int BUTTON_WIDTH = 65;
  private final int BUTTON_HEIGHT = 45;
  private final int BUTTON_FONT_SIZE = 10;

  private Stage myWindow;

  /**
   * Constructor for the HistoryPanel class
   * @param myWindow is the stage on which the history panel will be shown
   * @param viewTurtles is the observable list of turtles currently on the scren
   * @param parser is the command parser object that is used in the program
   */

  public HistoryPanel(Stage myWindow, ObservableList<Turtle> viewTurtles, CommandParser parser) {
    buttonNames = Arrays.asList("Command", "Variable", "Custom", "Properties", "Save", "Upload");
    this.myWindow = myWindow;
    this.comParser = parser;
    myCommandHistory = new CommandHistory(comParser);
    myOutputView= new OutputView();
    myUserDefined = new UserDefinedCommands();
    myVariableHistory = new VariableHistory();
    myConfig = new Configuration(viewTurtles);
    historyVBox = new VBox();
    historyVBox.setAlignment(Pos.CENTER);

    buttonsForPanes= new HBox();
    buttonsForPanes.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 20), CornerRadii.EMPTY, Insets.EMPTY)));

    makeAndSetButtons();

    //TODO put this back in the visualizer
    //  bp.setRight(historyVBox);
    Node toDisplay= myCommandHistory.returnScene();
    Node outputView= myOutputView.returnScene();
    historyVBox.getChildren()
        .addAll(buttonsForPanes, toDisplay, outputView);
  }

  /**
   * Makes a new row purely for command history
   * @param newCommand is the command string that will be shown in the command history row
   */
  //TODO refactor this somehow
  public void makeNewBox(String newCommand) {
    myCommandHistory.makeBox(newCommand);
    Button trial = myCommandHistory.returnButton();
    trial.setOnAction(e -> {comParser.parseText(newCommand); myCommandHistory.makeBox(newCommand);});
  }

  /**
   * Makes a new box in terminal
   * @param parseText is the string that was run from the commandline
   */
  public void makeNewTerminalBox(String parseText) {
    myOutputView.makeBox(parseText);
  }

  /**
   * returns the node/parent of all the elements within this view, which are the buttons and its corresponding history
   * view panels
   * @return a Node that holds all the history views and buttons
   */
  public Node returnScene(){
    return historyVBox;
  }

  private void makeAndSetButtons(){
    for(String str : buttonNames){
      String label = buttonNamesResources.getString(str);
      String methodName = buttonMethodsResources.getString(str);
      Button btn = new ViewButton(label, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_FONT_SIZE);
      btn.setOnAction(e -> {
        try {
          // System.out.println("Method name = " + methodName);
          Method method = HistoryPanel.class.getDeclaredMethod(methodName);
          method.invoke(HistoryPanel.this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
          //TODO throw an excpetion that is good
          ex.printStackTrace();
        }
      });
      buttonsForPanes.getChildren().add(btn);

    }

  }


  private void setShowProperties() {
    historyVBox.getChildren()
        .removeAll(myCommandHistory.returnScene(), myVariableHistory.returnScene(),
            myUserDefined.returnScene(), myOutputView.returnScene());
    if (!historyVBox.getChildren().contains(myConfig.getScene())) {
      historyVBox.getChildren().addAll(myConfig.getScene(),myOutputView.returnScene());
    }
  }

  private void setShowCustom() {
    historyVBox.getChildren()
        .removeAll(myCommandHistory.returnScene(), myVariableHistory.returnScene(),
            myConfig.getScene(),myOutputView.returnScene());
    if (!historyVBox.getChildren().contains(myUserDefined.returnScene())) {
      historyVBox.getChildren().addAll(myUserDefined.returnScene(),myOutputView.returnScene());
    }
  }

  private void setShowVariable() {
    historyVBox.getChildren().removeAll(myCommandHistory.returnScene(), myUserDefined.returnScene(),
        myConfig.getScene(),myOutputView.returnScene());
    if (!historyVBox.getChildren().contains(myVariableHistory.returnScene())) {
      historyVBox.getChildren().addAll(myVariableHistory.returnScene(),myOutputView.returnScene());
    }
  }

  private void setShowCommand() {
    historyVBox.getChildren()
        .removeAll(myVariableHistory.returnScene(), myUserDefined.returnScene(), myConfig.getScene(),myOutputView.returnScene());
    if (!historyVBox.getChildren().contains(myCommandHistory.returnScene())) {
      historyVBox.getChildren().addAll(myCommandHistory.returnScene(),myOutputView.returnScene());
    }
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
      if (keyCode == KeyCode.ENTER) {
        getXML(); //TODO: CHANGE THIS LATER
      }
      if (keyCode == KeyCode.Q) {
        saveXML();
      }
    });

  }

}
