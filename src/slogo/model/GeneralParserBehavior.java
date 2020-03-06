package slogo.model;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class GeneralParserBehavior {

  private static final Pattern COMMAND_PATTERN = Pattern.compile("[a-zA-Z_]+(\\?)?");

  private static final String RESOURCES = "resources.";
  private static final String ERRORS = RESOURCES + "ErrorMessages";
  private ResourceBundle errors = ResourceBundle.getBundle(ERRORS);

  public GeneralParserBehavior() {

  }

  protected Pattern getCommandPattern() {
    return COMMAND_PATTERN;
  }

  protected ResourceBundle getErrorBundle() {
    return errors;
  }

}
