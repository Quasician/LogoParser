package slogo.model;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import slogo.View.Language;
import slogo.model.Commands.Command;
import slogo.model.Commands.CommandFactoryInterface;
import java.util.*;
import java.util.regex.Pattern;

public class CommandTreeExecutor {

  private static GeneralParserBehavior generalParserBehavior = new GeneralParserBehavior();
  private static final String MAKE_VARIABLE = "MakeVariable";
  private static final String MAKE_USER_INSTRUCTION = "MakeUserInstruction";
  private static final String THIS_PACKAGE = "slogo.model.Commands.";
  private static final String VCU_COMMAND = THIS_PACKAGE + "VCUCommands.CustomCommand";
  private CommandFactoryInterface commandFactory;
  private ObservableList<Turtle> turtles;
  private ObservableMap<String,String> variables;
  private Language language;
  private HashMap<Pattern, String> translations;
  private String finalValue = "";
  private DisplayOption displayOption;
  private CustomCommandStorage customCommandStorage;

  private static final String RESOURCES_PACKAGE =
      generalParserBehavior.getResourcesString();
  public static ResourceBundle commandPackageNames = ResourceBundle
      .getBundle(RESOURCES_PACKAGE + "CommandPackages");
  public static ResourceBundle commandParameterNumbers = ResourceBundle
      .getBundle(RESOURCES_PACKAGE + "ParameterNumbers");
  private static final String ERRORS = RESOURCES_PACKAGE + "ErrorMessages";
  private ResourceBundle errors = ResourceBundle.getBundle(ERRORS);

  public CommandTreeExecutor(CommandFactoryInterface factory, ObservableList<Turtle> turtles, ObservableMap<String,String> variables, HashMap<Pattern, String> translations, Language language, CustomCommandStorage customCommandStorage) {
    this.language = language;
    this.turtles = turtles;
    commandFactory = factory;
    this.translations = translations;
    this.variables = variables;
    this.customCommandStorage = customCommandStorage;
  }

  public String executeTrees(List<TreeNode> elementNodes) {
    for (TreeNode element : elementNodes) {
      executeSubTree(element);
    }
    finalValue = elementNodes.get(elementNodes.size() - 1).getResult();
    return finalValue;
  }

  public void setDisplayOption(DisplayOption d) {
    displayOption = d;
  }

  private boolean isMakeVariableCommand(TreeNode element) {
    return element.getName().equals(MAKE_VARIABLE);
  }

  private boolean isMakeUserInstruction(TreeNode element) {
    return element.getName().equals(MAKE_USER_INSTRUCTION);
  }

  private Command createCommand(TreeNode element, List<String> parameters) {
    String commandClass = "";
    if (customCommandStorage.isACustomCommand(element.getName())) {
      commandClass = VCU_COMMAND;
    } else { //not a custom command
      commandClass = getCommandClass(element, parameters);
    }
    return commandFactory.createCommand(commandClass);
  }

  private boolean isCommand(TreeNode element) {
    return match(element.getName(), generalParserBehavior.getCommandPattern());
  }

  private boolean isVariable(TreeNode element) {
    return match(element.getName(), generalParserBehavior.getVariablePattern());
  }

  private boolean isUserMade(TreeNode element) {
    return isMakeVariableCommand(element) || isMakeUserInstruction(element);
  }

  private void executeSubTree(TreeNode element) {
    if (isCommand(element)) {
      List<TreeNode> children = element.getChildren();
      ArrayList<String> parameters = new ArrayList<>();

      if (isUserMade(element)) {
        parameters.add(children.get(0).getName());
        children.remove(0);
      }
      for (TreeNode child : children) {
        executeSubTree(child);
        parameters.add(child.getResult());
      }
      setupCommand(element, parameters);
    } else if (isVariable(element)) {
      element.setResult(variables.get(element.getName()));
    }
  }

  private void setupCommand(TreeNode element, List<String> parameters) {
    Command commandObject = createCommand(element, parameters);
    commandObject.setParams(parameters);
    commandObject.setTurtles(turtles);
    commandObject.setVariables(variables);
    commandObject.setMiniParserLanguage(language);
    commandObject.setDisplayOption(displayOption);
    commandObject.setCustomCommandStorage(customCommandStorage);
    commandObject.doCommand(element);
  }

  private String getPackageName(String commandName) {
    return commandPackageNames.getString(commandName);
  }

  private String getCommandClass(TreeNode element, List<String> parameters) {
    String commandName = element.getName();
    String commandClass = THIS_PACKAGE + getPackageName(commandName) + "."
            + commandName;
    int numParamsShouldHave = Integer.parseInt(commandParameterNumbers.getString(commandName));
    if (parameters.size() != numParamsShouldHave) {
      throw new CommandException(errors.getString("WrongParameterNumber"));
    }
    return commandClass;
  }

  private boolean match(String text, Pattern regex) {
    return regex.matcher(text).matches();
  }

}
