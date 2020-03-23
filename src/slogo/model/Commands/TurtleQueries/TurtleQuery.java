package slogo.model.Commands.TurtleQueries;

import slogo.model.Commands.Command;

/**
 * @author Sanna
 *
 * Abstract class representing turtle query commands, those that return some
 * value pertaining to the turtle's current status.
 *
 * Assumption: we always return values pertaining to the first turtle in the list
 * of turtles. Even if there is only one turtle, this will work.
 */
public abstract class TurtleQuery extends Command {

  protected static final String TRUE = "1";
  protected static final String FALSE = "0";

  public TurtleQuery(String name) {
    super(name);
  }

}
