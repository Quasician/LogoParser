package slogo.model.Commands;

import slogo.model.CommandStack;
import slogo.model.TreeNode;
import slogo.model.Turtle;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

  protected int params;
  protected Turtle turtle;
  protected String name;
  protected ArrayList<String> values;

  protected CommandStack commandStack;

  public Command(int params, String name) {
    this.name = name;
    this.params = params;
  }

  public abstract void doCommand(TreeNode commandNode);

  public int getParamNumber() {
    return params;
  }

  public List<String> getParamList() {
    return values;
  }

  public void setCommandStack(CommandStack commandStack) {
    this.commandStack = commandStack;
  }

  public void setTurtle(Turtle turtle) {
    this.turtle = turtle;
  }

  public void setParams(List<String> params) {
    values = (ArrayList)params;
  }
}
