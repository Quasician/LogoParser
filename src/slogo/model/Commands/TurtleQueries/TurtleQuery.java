package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.Command;

public abstract class TurtleQuery extends Command {

  protected static final String TRUE = "1";
  protected static final String FALSE = "0";

  public TurtleQuery(String name) {
    super(name);
  }

}
