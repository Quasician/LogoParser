package slogo.model.Commands;

import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.View.Language;
import slogo.model.DisplayOption;
import slogo.model.TreeNode;
import slogo.model.Turtle;
import slogo.model.TurtleList;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

  protected ObservableList<Turtle> turtles;
  protected ObservableList<Turtle> activatedTurtles;
  protected String name;
  protected Language language;
  protected ArrayList<String> values;
  private static final String ERRORS = "resources.ErrorMessages";
  protected ResourceBundle errors = ResourceBundle.getBundle(ERRORS);
  protected DisplayOption displayOption;

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

  public void setTurtles(ObservableList<Turtle> turtles, ObservableList<Turtle> activatedTurtles) {
    this.turtles = turtles;
    this.activatedTurtles = activatedTurtles;
  }

  private ObservableList<Turtle> getActivatedTurtles()
  {
    return activatedTurtles;
  }

  public void setMiniParserLanguage(Language language){ this.language = language;}

  public void setParams(List<String> params) {
    values = (ArrayList)params;
  }

  protected String string(int value) {
    return value + "";
  }
}
