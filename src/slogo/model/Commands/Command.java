package slogo.model.Commands;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import slogo.View.Language;
import slogo.model.TreeNode;
import slogo.model.Turtle;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

  protected Turtle turtle;
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

  public void setTurtle(Turtle turtle) { this.turtle = turtle; }

  public void setMiniParserLanguage(Language language){ this.language = language;}

  public void setParams(List<String> params) {
    values = (ArrayList)params;
  }

  protected String string(int value) {
    return value + "";
  }
}
