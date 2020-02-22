package slogo.model.Commands;

import slogo.model.CommandStack;
import slogo.model.Turtle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//merge
public abstract class Command {

  protected int params;
  protected Turtle turtle;
  protected String name;
  protected ArrayList<Double> values;

  protected CommandStack commandStack;

  public Command(int params, String name) {
    this.name = name;
    this.params = params;
  }

  public abstract void doCommand();

  public int getParamNumber() {
    return params;
  }

  public List<Double> getParamList() {
    return values;
  }

  public void setCommandStack(CommandStack commandStack) {
    this.commandStack = commandStack;
  }

  public void setTurtle(Turtle turtle) {
    this.turtle = turtle;
  }

  public void setParams(List<Double> params) {
    values = (ArrayList)params;
  }
}
