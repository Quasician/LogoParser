package slogo.model.Commands;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.View.Language;
import slogo.model.TreeNode;
import slogo.model.Turtle;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

  protected ObservableList<Turtle> turtles;
  protected ObservableList<Turtle> activatedTurtles;
  protected String name;
  protected Language language;
  protected ArrayList<String> values;

  public Command(String name) {
    this.name = name;
  }

  public abstract void doCommand(TreeNode commandNode);

  public List<String> getParamList() {
    return values;
  }

  public void setTurtles(ObservableList<Turtle> turtles) {
    this.turtles = turtles;
    activatedTurtles = (this.turtles);
  }

  private ObservableList<Turtle> getActivatedTurtles(ObservableList<Turtle> turtles)
  {
    ObservableList<Turtle> activeTurtles = FXCollections.emptyObservableList();
    for(Turtle turtle :turtles)
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

  protected String string(int value) {
    return value + "";
  }
}
