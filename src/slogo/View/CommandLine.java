package slogo.View;


import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import slogo.Main;
import slogo.model.Turtle;

/**
 * This class encapsulates the command line object, which is where the user enters the commands to control the turtle.
 *
 * Purpose: Give the user an interactive way to enter turtle commands
 *
 * Assumptions: Pressing "enter" is not the same as pressing the "go" button, so multi-line commands can be run. Also,
 * sometimes, when copying in some of the example commands that we were given (that were relatively long and had multiple
 * parts), the parser wouldn't be able to parser to commands. Unsure if this is due to the commandline or parsing or both.
 * An interesting assumption is that the user can only use the arrow keys to move the turtle around if the command line is in focus.
 *
 * Dependencies: Main (for the static resource bundle), the Turtle class, StringProperty class, ActivityListeners class, and ObservableList<Turtle>
 *
 * Example: Only need to declare an instance of this class, and the user can type in "fd 100," and when the user presses the "Go"
 * button, the text within in the text area is parsed for the turtle to move forward 100.
 *
 * @author Michelle Tai
 * @author Himanshu Jain
 */
public class CommandLine{
  public static ResourceBundle myResources = Main.MY_RESOURCES;
  private TextArea inputArea;
  private ViewButton runButton;
  private ViewButton clearButton;
  private HBox commandLineGroup;
  private static final int BUTTON_HEIGHT = 60;
  private static final int BUTTON_WIDTH = 100;
  private static final int COMMAND_LINE_TEXTAREA_HEIGHT = BUTTON_HEIGHT * 2 + 10;
  private static final int SPACING_VALUE = 10;
  private static final int TEXTAREA_FONTSIZE = 20;
  private StringProperty commandLineText;
  private static final String EMPTY ="";
  private static final String RUN ="Run";
  private static final String FORWARD_50 ="Forward 50";
  private static final String BACKWARD_50 ="Backward 50";
  private static final String RIGHT_45 ="Right 45";
  private static final String LEFT_45 ="Left 45";
  private static final String CLEAR ="Clear";
  private static final String FONT_NAME="Avenir";
  private static final String PROMPT="EnterCommandPrompt";
  private BooleanProperty textUpdate;
  private ObservableList<Turtle> activatedTurtles;
  private BooleanProperty translatedTextUpdate;
  private BooleanProperty makeBox;


  /**
   * Constructor for the command line group, which includes the run and clear button, as well as an area
   * where the user can type in commands to control the turtle.
   * @param commandLineText is the text current in the textarea
   * @param listeners is an ActivityListeners object that holds all the listeners needed
   * @param activatedTurtles is an ObservableList of Turtles
   */
  public CommandLine(StringProperty commandLineText, ActivityListeners listeners, ObservableList<Turtle> activatedTurtles){
    this.activatedTurtles = activatedTurtles;
    this.textUpdate = listeners.textUpdateProperty();
    this.translatedTextUpdate = listeners.translateTextUpdateProperty();
    this.makeBox= listeners.checkBoxProperty();
    this.commandLineText = commandLineText;
    setInputArea();
    setButtons();
    formatCommandLineGroup();
  }

  /**
   * @return commandLineGroup, which is a Node that has the text area where users type in commands and the run and clear button.
   */
  public Node getCommandLineGroup(){
    return commandLineGroup;
  }

  private void setInputArea(){
    inputArea = new TextArea();
    inputArea.setOnKeyPressed(e-> keyMovementHandler(e) );
    inputArea.setMaxHeight(COMMAND_LINE_TEXTAREA_HEIGHT);
    inputArea.setFont(Font.font(FONT_NAME, FontWeight.NORMAL, TEXTAREA_FONTSIZE));
    inputArea.setPromptText(myResources.getString(PROMPT));
  }

  private void keyMovementHandler(KeyEvent e) {
      if ((e.getCode() == KeyCode.UP)) {
        parseTranslatedCommands(FORWARD_50);
      }
      if ((e.getCode() == KeyCode.DOWN)) {
        parseTranslatedCommands(BACKWARD_50);
      }
      if ((e.getCode() == KeyCode.RIGHT)) {
        parseTranslatedCommands(RIGHT_45);
      }
      if ((e.getCode() == KeyCode.LEFT)) {
        parseTranslatedCommands(LEFT_45);
      }
  }

  private void parseTranslatedCommands(String newCommand){
    commandLineText.set(newCommand);
    translatedTextUpdate.setValue(!translatedTextUpdate.getValue());
  }

  private void setButtons(){
    runButton = new ViewButton(myResources.getString(RUN), BUTTON_HEIGHT, BUTTON_WIDTH);
    runButton.setOnAction(e -> {
     runCommand(inputArea.getText()+ EMPTY, true, !textUpdate.getValue());
    });
    clearButton = new ViewButton(myResources.getString(CLEAR), BUTTON_HEIGHT, BUTTON_WIDTH);
    clearButton.setOnAction(e -> {
      inputArea.setText(EMPTY);
    });
  }

  private void formatCommandLineGroup(){
    VBox commandLineButtons = new VBox(SPACING_VALUE);
    commandLineButtons.getChildren().addAll(runButton, clearButton);
    commandLineGroup = new HBox(SPACING_VALUE);
    commandLineGroup.getChildren().addAll(inputArea, commandLineButtons);
  }

  private void runCommand(String command, Boolean bool, Boolean update){
    commandLineText.set(command);
    makeBox.setValue(bool);
    textUpdate.set(update);
  }

}
