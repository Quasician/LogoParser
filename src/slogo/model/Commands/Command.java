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

public abstract class Command {

  protected ObservableList<Turtle> turtles;
  protected ObservableMap<String,String> variables;
  protected ObservableList<Turtle> activatedTurtles;
  protected String name;
  protected Language language;
  protected ArrayList<String> values;
  private static final String ERRORS = "resources.ErrorMessages";
  protected ResourceBundle errors = ResourceBundle.getBundle(ERRORS);
  protected DisplayOption displayOption;
  protected CustomCommandStorage customCommandStorage;

  public Command(String name) {
    this.name = name;
  }

  public abstract void doCommand(TreeNode commandNode);

  public void setDisplayOption(DisplayOption d) {
    displayOption = d;
  }

  public List<String> getParamList() {
    return values;
  }

  public void setTurtles(ObservableList<Turtle> turtles) {
    this.turtles = turtles;
    this.activatedTurtles = getActivatedTurtles();
  }

  public void setVariables(ObservableMap<String, String> variables) {
    this.variables = variables;
  }

  private ObservableList<Turtle> getActivatedTurtles()
  {
    ObservableList<Turtle> activeTurtles = FXCollections.observableArrayList();
    for(Turtle turtle : turtles)
    {
      if(turtle.isActivatedProperty().getValue())
      {
        activeTurtles.add(turtle);
      }
    }
    return activeTurtles;
  }

  public void setMiniParserLanguage(Language language){ this.language = language;}

  public void setParams(List<String> params) {
    values = (ArrayList)params;
  }

  public void setCustomCommandStorage(CustomCommandStorage  customCommandStorage) {

    this.customCommandStorage = customCommandStorage;
  }

  protected String string(int value) {
    return value + "";
  }
}
