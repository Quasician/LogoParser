package slogo.model.Commands;

import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import slogo.View.Language;
import slogo.model.*;
import slogo.model.Commands.VCUCommands.CustomCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanna, Thomas
 *
 * This is the abstract class representing the Command object. We created an inheritance hierarchy
 * for commands, giving general responsibilities to this superclass and more specific funcionality
 * down the hierarchy.
 *
 * The command class could stand on its own, but sub commands require certain data including the
 * turtles, the language, and the display option object (for display commands).
 *
 *
 */
public abstract class Command {

  protected ObservableList<Turtle> turtles;
  protected ObservableMap<String, String> variables;
  protected ObservableList<Turtle> activatedTurtles;
  protected String name;
  protected Language language;
  protected ArrayList<String> values;
  private static final String ERRORS = "resources.ErrorMessages";
  protected ResourceBundle errors = ResourceBundle.getBundle(ERRORS);
  protected DisplayOption displayOption;
  protected CustomCommandStorage customCommandStorage;

  /**
   * Constructor
   * @param name
   */
  public Command(String name) {
    this.name = name;
  }

  /**
   * This is the essential method of the Command. Each specific command type
   * overrides this method to implement their functionalities.
   * This method is called from command parsing, after the command has been parsed.
   * @param commandNode
   */
  public abstract void doCommand(TreeNode commandNode);

  /**
   * Used in CommandTreeExecutor to set the DisplayOption object that is used by display commands
   * @param d the display option
   */
  public void setDisplayOption(DisplayOption d) {
    displayOption = d;
  }

  /**
   * Returns the parameter list
   * @return list of parameters as strings
   */
  public List<String> getParamList() {
    return values;
  }

  /**
   * Set the list of turtles based on the parameter
   * Also initializes the activated turtles list
   * @param turtles the observable list of turtles
   */
  public void setTurtles(ObservableList<Turtle> turtles) {
    this.turtles = turtles;
    this.activatedTurtles = getActivatedTurtles();
  }

  /**
   * Set the map of variables
   * @param variables
   */
  public void setVariables(ObservableMap<String, String> variables) {
    this.variables = variables;
  }

  private ObservableList<Turtle> getActivatedTurtles() {
    ObservableList<Turtle> activeTurtles = FXCollections.observableArrayList();
    for (Turtle turtle : turtles) {
      if (turtle.isActivatedProperty().getValue()) {
        activeTurtles.add(turtle);
      }
    }
    return activeTurtles;
  }

  /**
   * Set the current language to be used in any secondary parsing within commands
   * @param language
   */
  public void setMiniParserLanguage(Language language) {
    this.language = language;
  }

  /**
   * Set the list of parameters for the command
   * @param params
   */
  public void setParams(List<String> params) {
    values = (ArrayList) params;
  }

  /**
   * Set the custom command storage object
   * @param customCommandStorage
   */
  public void setCustomCommandStorage(CustomCommandStorage customCommandStorage) {

    this.customCommandStorage = customCommandStorage;
  }

  protected String string(int value) {
    return value + "";
  }
}
