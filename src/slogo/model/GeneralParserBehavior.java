package slogo.model;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class GeneralParserBehavior {

  private static final Pattern COMMAND_PATTERN = Pattern.compile("(\\+)|(\\-)|(\\*)|(\\~)|(\\/)|(\\%)|[a-zA-Z_]+(\\?)?");
  private static final Pattern CONSTANT_PATTERN = Pattern.compile("-?[0-9]+\\.?[0-9]*");
  private static final Pattern VARIABLE_PATTERN = Pattern.compile(":[a-zA-Z_]+");
  private static final Pattern COMMENT_PATTERN = Pattern.compile("^#.*");
  private static final Pattern NEW_LINE_PATTERN = Pattern.compile("\n");

  private static final String RESOURCES = "resources.";
  private static final String ERRORS = RESOURCES + "ErrorMessages";
  private ResourceBundle errors = ResourceBundle.getBundle(ERRORS);

  public GeneralParserBehavior() {

  }

  protected String getResourcesString() {
    return RESOURCES;
  }

  protected Pattern getCommentPattern() {
    return COMMENT_PATTERN;
  }

  protected Pattern getCommandPattern() {
    return COMMAND_PATTERN;
  }

  protected Pattern getConstantPattern() {
    return CONSTANT_PATTERN;
  }

  protected Pattern getVariablePattern() {
    return VARIABLE_PATTERN;
  }

  protected ResourceBundle getErrorBundle() {
    return errors;
  }

}
