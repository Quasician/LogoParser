package slogo.View;


import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import slogo.Main;
import slogo.model.Turtle;
import slogo.model.TurtleList;

/**
 * consider: what if I wanna add more buttons?
 * This class encapsulates the command line object, which is where the user enters the commands to control the turtle.
 * @author Michelle Tai
 */
public class CommandLine{
  public static ResourceBundle myResources = Main.myResources;

  private TextArea inputArea;
  private ViewButton runButton;
  private ViewButton clearButton;
  private HBox commandLineGroup;

  private static final int BUTTON_HEIGHT = 60;
  private static final int BUTTON_WIDTH = 100;
  private static final int COMMAND_LINE_TEXTAREA_HEIGHT = BUTTON_HEIGHT * 2 + 10;
  private static final int SPACING_VALUE = 10;
  private static final int MOVEMENT = 50;
  private static final int DEGREE_CHANGE = 30;
  private static final int TEXTAREA_FONTSIZE = 20;
  private StringProperty commandLineText;
  private static final String Empty="";
  private static final String Run="Run";
  private static final String Clear="Clear";
  private static final String FONT_NAME="Avenir";
  private static final String PROMPT="EnterCommandPrompt";
  private BooleanProperty textUpdate;
  private ObservableList<Turtle> activatedTurtles;


  /**
   * Constructor for the command line group, which includes the run and clear button, as well as an area
   * where the user can type in commands to control the turtle.
   */
  public CommandLine(StringProperty commandLineText, BooleanProperty textUpdate, TurtleGrid turt, ObservableList<Turtle> activatedTurtles){
    this.activatedTurtles = activatedTurtles;
    this.textUpdate = textUpdate;
    this.commandLineText = commandLineText;
    setInputArea(turt);
    setButtons();
    formatCommandLineGroup();
  }

  /**
   * @return commandLineGroup, which is a Node that has the text area where users type in commands and the run and clear button.
   */
  public Node getCommandLineGroup(){
    return commandLineGroup;
  }

  private void setInputArea(TurtleGrid turtle){
    inputArea = new TextArea();
    inputArea.setOnKeyPressed(e-> {
              if ((e.getCode() == KeyCode.UP)) {
                for (Turtle t : activatedTurtles) {
                  if(t.isActivatedProperty().getValue()) {
                    t.setCoordinate(t.getX(), t.getY() + MOVEMENT);
                  }
                }
              }
              if ((e.getCode() == KeyCode.DOWN)) {
                for (Turtle t : activatedTurtles) {
                  if(t.isActivatedProperty().getValue()) {
                    t.setCoordinate(t.getX(), t.getY() - MOVEMENT);
                  }
                }
              }
              if ((e.getCode() == KeyCode.RIGHT)) {
                for (Turtle t : activatedTurtles) {
                  if(t.isActivatedProperty().getValue()) {
                    t.setDegree(t.getDegree() + DEGREE_CHANGE);
                  }
                }
              }
              if ((e.getCode() == KeyCode.LEFT)) {
                for (Turtle t : activatedTurtles) {
                  if(t.isActivatedProperty().getValue()) {
                    t.setDegree(t.getDegree() - DEGREE_CHANGE);
                  }
                }
              }});
    inputArea.setMaxHeight(COMMAND_LINE_TEXTAREA_HEIGHT);
    inputArea.setFont(Font.font(FONT_NAME, FontWeight.NORMAL, TEXTAREA_FONTSIZE));
    inputArea.setPromptText(myResources.getString(PROMPT));
  }

  private void setButtons(){
    runButton = new ViewButton(myResources.getString(Run), BUTTON_HEIGHT, BUTTON_WIDTH);
    runButton.setOnAction(e -> {
      commandLineText.set(inputArea.getText()+Empty);
      textUpdate.set(!textUpdate.getValue());
      System.out.println("FROM VIEW: " + commandLineText);
    });
    clearButton = new ViewButton(myResources.getString(Clear), BUTTON_HEIGHT, BUTTON_WIDTH);
    clearButton.setOnAction(e -> {
      inputArea.setText(Empty);
    });
  }

  private void formatCommandLineGroup(){
    VBox commandLineButtons = new VBox(SPACING_VALUE);
    commandLineButtons.getChildren().addAll(runButton, clearButton);
    commandLineGroup = new HBox(SPACING_VALUE);
    commandLineGroup.getChildren().addAll(inputArea, commandLineButtons);
  }

}
