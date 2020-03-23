package slogo.model;

/**
 * @author Sanna, revised from Professor Duvall's code
 *
 * This class is used for exceptions that have to do with commands, including
 * when users input an invalid command or the wrong number of parameters.
 * The command exceptions are caught by Main.
 */
public class CommandException extends RuntimeException {

  // for serialization
  private static final long serialVersionUID = 1L;

  /**
   * Create an exception based on an issue in our code.
   * @param message the String representing the desired message
   */
  public CommandException(String message, Object... values) {
    super(String.format(message, values));
  }

  /**
   * Create an exception based on a caught exception with a different message.
   */
  public CommandException(Throwable cause, String message, Object... values) {
    super(String.format(message, values), cause);
  }

  /**
   * Create an exception based on a caught exception, with no additional message.
   */
  public CommandException(Throwable cause) {
    super(cause);
  }


}
