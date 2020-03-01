package slogo.model;

import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public abstract class AbstractCommandParsing {

  protected static final String USER_INSTRUCTION = "MakeUserInstruction";

  protected static final String RESOURCES_PACKAGE =
      CommandParser.class.getPackageName() + ".resources.languages.";

  protected static final String RESOURCES = "resources.";
  protected static final String ERRORS = RESOURCES + "ErrorMessages";
  protected ResourceBundle errors = ResourceBundle.getBundle(ERRORS);

  // "types" and the regular expression patterns that recognize those types
  protected List<Entry<String, Pattern>> mySymbols;

  protected static final Pattern CONSTANT_PATTERN = Pattern.compile("-?[0-9]+\\.?[0-9]*");
  protected static final Pattern COMMAND_PATTERN = Pattern.compile("[a-zA-Z_]+(\\?)?");


  /**
   * Returns language's type associated with the given text if one exists.
   * Throws an error if there is no match
   */
  public String getSymbol(String text) {
    final String ERROR = "NO MATCH";
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    throw new CommandException(new Exception(), errors.getString("InvalidCommand"));
    //return ERROR;
  }

  // Returns true if the given text matches the given regular expression pattern
  private boolean match(String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }


}
