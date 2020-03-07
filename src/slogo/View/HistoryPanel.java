package slogo.View;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import slogo.model.CommandParser;
import slogo.model.xml.XMLCreator;
import slogo.model.xml.XMLException;
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
  private PropertiesHolder myConfig;
  private CommandParser comParser;
  private static final int ZERO_INDEX=0;
  private static final String XML_FILES_TEXT= "XML files";
  private static final String XML_EXTENSION="*.xml";
  private static final String CLEAR_SCREEN="ClearScreen";
  private static final String DELIMITER="\n";
  private static final String RESOURCES_HISTORY_VIEW = "resources/HistoryView/";
  private static final String RESOURCES_XMLERRORS = "resources/XMLErrors";
  private static final String ERROR_MESSAGES = "resources/ErrorMessages";
  private static final String TITLE = "Title";
  private static final ResourceBundle BUTTON_NAMES_RESOURCES = ResourceBundle.getBundle(RESOURCES_HISTORY_VIEW + "HistoryButtonNames");
  private static final ResourceBundle BUTTON_METHODS_RESOURCES = ResourceBundle.getBundle(RESOURCES_HISTORY_VIEW + "HistoryButtonMethods");
  private static final ResourceBundle XML_ERRORS = ResourceBundle.getBundle(RESOURCES_XMLERRORS);
  private static final ResourceBundle ERRORS = ResourceBundle.getBundle(ERROR_MESSAGES);
  private static final String ERRORS_STRING = ERRORS.getString("Button");
  private static final String XML_ERRORS_STRING = XML_ERRORS.getString("Null");
  private static final ResourceBundle MY_PROPERTIES = ResourceBundle.getBundle(RESOURCES_HISTORY_VIEW + "HistoryButtonProperties");
  private static final List<String> BUTTON_NAMES = Arrays.asList("Command", "Variable", "Custom", "Properties","Undo", "Save", "Upload");;
  private HBox buttonsForPanes;
  private ObservableList<Triplet<String, String, String>> customCommandList;
  private static final int BUTTON_WIDTH = Integer.parseInt(MY_PROPERTIES.getString("ButtonWidth"));
  private static final int BUTTON_HEIGHT = Integer.parseInt(MY_PROPERTIES.getString("ButtonHeight"));
  private static final int BUTTON_FONT_SIZE = Integer.parseInt(MY_PROPERTIES.getString("ButtonFontSize"));
  private static final String BUTTON_COLOR = MY_PROPERTIES.getString("Color");
  private Stage myWindow;

  /**
   * Constructor for the HistoryPanel class
   * @param myWindow is the stage on which the history panel will be shown
   * @param parser is the command parser object that is used in the program
   */

  public HistoryPanel(Stage myWindow, CommandParser parser, PropertiesHolder propertiesHolder, ObservableMap<String,String> variables, ObservableList<Triplet<String, String, String>> customCommandList) {
    this.myWindow = myWindow;
    this.comParser = parser;
    this.customCommandList = customCommandList;
    myCommandHistory = new CommandHistory(comParser);
    myOutputView= new OutputView();
    myUserDefined = new UserDefinedCommands(customCommandList);
    myVariableHistory = new VariableHistory(variables);
    myConfig = propertiesHolder;
    historyVBox = new VBox();
    historyVBox.setAlignment(Pos.CENTER);
    buttonsForPanes= new HBox();
    buttonsForPanes.setBackground(new Background(new BackgroundFill(Color.web(BUTTON_COLOR), CornerRadii.EMPTY, Insets.EMPTY)));
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
    trial.setOnAction(e -> {comParser.parseText(newCommand); makeNewBox(newCommand);});
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
    for(String str : BUTTON_NAMES){
      String label = BUTTON_NAMES_RESOURCES.getString(str);
      String methodName = BUTTON_METHODS_RESOURCES.getString(str);
      Button btn = new ViewButton(label, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_FONT_SIZE);
      btn.setOnAction(e -> {
        try {
          Method method = HistoryPanel.class.getDeclaredMethod(methodName);
          method.invoke(HistoryPanel.this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
          // Do nothing
        }
      });
      buttonsForPanes.getChildren().add(btn);
    }
  }

  private void undoCommands(){
    comParser.miniParse(CLEAR_SCREEN);
    try{
      myCommandHistory.removeCommand();
      for(String commandsUndo: myCommandHistory.getCommandListCopy() ){
         comParser.parseText(commandsUndo);
      }
    } catch (IndexOutOfBoundsException e){
      makeNewTerminalBox(e.getMessage());
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

  private void uploadXML() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new ExtensionFilter(XML_FILES_TEXT, XML_FILES_TEXT));
    try {
      File xml = fileChooser.showOpenDialog(myWindow);
      XMLParser parser = new XMLParser(xml);
      parser.setUp();
      List<String> commands = parser.getCommands();
      String[] array = commands.toArray(new String[ZERO_INDEX]);
      String str = String.join(DELIMITER, array);
      comParser.parseText(str);
    } catch (Exception e) {
      //Do nothing: File was not chosen, throwing error is unnecessary.
    }
  }

  private void saveXML() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new ExtensionFilter(XML_FILES_TEXT, XML_EXTENSION));
    try {
      File xmlToSave = fileChooser.showOpenDialog(myWindow);
      XMLCreator creator = new XMLCreator(myCommandHistory.getCommandListCopy(), xmlToSave);
      creator.createFile(TITLE);
    } catch (NullPointerException e) {
      throw new XMLException(XML_ERRORS_STRING);
    }
  }

}
