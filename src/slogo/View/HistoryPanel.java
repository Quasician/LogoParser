package slogo.View;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
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
import slogo.model.xml.XMLCreator;
import slogo.model.xml.XMLException;
import slogo.model.xml.XMLParser;

/**
 * This class holds all the possible "history views" and their button tabs so that the user can switch between views to see the
 * variable history, the command history, the custom commands, the properties of the turtle(s), as well as buttons like Undo, Save (as XML),
 * and Upload (an XML). This object also has a panel below the "history-viewing" part to show the return values of all commands, but mainly to show the
 * values of Math commands and commands that don't actually involve the turtle.
 *
 * Purpose: To hold all the different "history" views in one section and allow the panels in this view to be changed based on tab/button clicks. Also
 *          to hold the "terminal," which shows all the return values for each command.
 *
 * Assumptions: That we have written our reflection code and property files correctly so that the buttons have the correct onAction methods.
 *              Also, that there will not be added functionalities other than what is written, unless someone wants to add in proper private methods in this
 *              class and information in the properties files in the HistoryView folder. However, I think that the order can be changed based on the order of
 *              the names in the HistoryButtonNames.properties files
 *
 * Dependencies: Stage class, PropertiesHolder object, ObservableMap<String,String> object, ObservableList<Triplet<String, String, String>> object,
 *               StringProperty object, ActivityListeners object, Method class (for reflection)
 *
 * Example: Create the HistoryPanel object and then add it in a VBox to see a row of buttons at the top of the box, with the names "Command,"
 *          "Variable," "Custom," "Properties," "Undo," "Save," and "Upload." When you click the "Command" tab/button, the panel below
 *          the row of buttons will change to show the command history. When you click the "Variable" tab/button, the panel below the
 *          buttons changes to show the defined variables. When you click "Custom" tab/button, the panel changes to show all the user-defined commands.
 *          When you click "Properties," the panel changes to show the properties of each active turtle. When you click "Undo," it undoes the last
 *          command entered.
 *          "Save" and "Upload" are the only ones that don't visibly change the panel. When you click "Save," an XML file will be created, and it will hold
 *          all the commands that have been run up to that point. When you click "Upload," you can choose an XML file to upload, and all the commands
 *          within that file will be run.
 *          Whenever a command is entered, a new row will be made in the command history view, and the return value of that command will be displayed
 *          in the "terminal" below the "history-view" panel area.
 *
 * @author Michelle Tai
 * @author Himanshu Jain
 * @author Sanna Symer
 */

public class HistoryPanel implements HistoryView{
  private  StringProperty CommandLineString;
  private BooleanProperty CommandLineBool;
  private VBox historyVBox;
  private CommandHistory myCommandHistory;
  private OutputView myOutputView;
  private UserDefinedCommands myUserDefined;
  private VariableHistory myVariableHistory;
  private PropertiesHolder myConfig;
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
  private BooleanProperty checkBoxToBeMade;
  private ObservableList<Triplet<String, String, String>> customCommandList;
  private static final int BUTTON_WIDTH = Integer.parseInt(MY_PROPERTIES.getString("ButtonWidth"));
  private static final int BUTTON_HEIGHT = Integer.parseInt(MY_PROPERTIES.getString("ButtonHeight"));
  private static final int BUTTON_FONT_SIZE = Integer.parseInt(MY_PROPERTIES.getString("ButtonFontSize"));
  private static final String BUTTON_COLOR = MY_PROPERTIES.getString("Color");
  private Stage myWindow;
  private BooleanProperty checkTranslated;

  /**
   * Constructor for the HistoryPanel class that sets up everything for this part of the display.
   * @param myWindow is the Stage on which the history panel will be shown
   * @param propertiesHolder is a PropertiesHolder object that will holds the properties of the turtles
   * @param variables is an ObservableMap object of key and value type String that will keep track of the variables
   * @param customCommandList is an ObservableList of Triplets to keep track of when a new user-defined command is created
   * @param str is a StringProperty object that will hold that text that is currently in the text box of the command line
   * @param listeners is an ActivityListeners object that will initialize and keep track of all the listeners needed
   */

  public HistoryPanel(Stage myWindow, PropertiesHolder propertiesHolder, ObservableMap<String,String> variables, ObservableList<Triplet<String, String, String>> customCommandList, StringProperty str,ActivityListeners listeners) {
    this.myWindow = myWindow;
    this.checkTranslated = listeners.translateTextUpdateProperty();
    this.CommandLineString = str;
    this.CommandLineBool =listeners.textUpdateProperty();
    checkBoxToBeMade=listeners.checkBoxProperty();
    this.customCommandList = customCommandList;
    myCommandHistory = new CommandHistory();
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
   * Makes a new row purely for command history panel
   * @param newCommand is the command string that will be shown in the command history row
   */
  //TODO refactor this somehow
  public void makeNewBox(String newCommand) {
    myCommandHistory.makeBox(newCommand);
    Button trial = myCommandHistory.returnButton();
    trial.setOnAction(e -> {
      parseCommand(newCommand, true);});
  }

  /**
   * Makes a new row in the "terminal" that holds the return value of the command that has been run
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

  /**
   * The private methods below use reflection to create the button bar and assign the onAction methods for each button.
   * @author Sanna Symer
   * @author Michelle Tai
   */
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
    CommandLineString.set(CLEAR_SCREEN);
    checkTranslated.setValue(!checkTranslated.getValue());
    try{
      myCommandHistory.removeCommand();
      for(String commandsUndo: myCommandHistory.getCommandListCopy() ){
         parseCommand(commandsUndo,false);
      }
    } catch (IndexOutOfBoundsException e){
      makeNewTerminalBox(e.getMessage());
    }
  }
  private void parseCommand(String command, Boolean makeBox){
    CommandLineString.set(command);
    checkBoxToBeMade.setValue(makeBox);
    CommandLineBool.setValue(!CommandLineBool.getValue());
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
    fileChooser.getExtensionFilters().add(new ExtensionFilter(XML_FILES_TEXT, XML_EXTENSION));
    try {
      File xml = fileChooser.showOpenDialog(myWindow);
      XMLParser parser = new XMLParser(xml);
      parser.setUp();
      List<String> commands = parser.getCommands();
      String[] array = commands.toArray(new String[ZERO_INDEX]);
      String str = String.join(DELIMITER, array);
      parseCommand(str,true);
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
