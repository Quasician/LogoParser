package slogo.model;


import java.util.ResourceBundle;

import slogo.View.Language;
import slogo.model.Commands.Command;
import slogo.model.Commands.CommandFactoryInterface;
import java.util.*;
import java.util.regex.Pattern;

public class CommandTreeExecutor {


  //private static final String RESOURCES_PACKAGE = CommandParser.class.getPackageName() + ".resources.languages.";
  private Pattern constantPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
  private Pattern commandPattern = Pattern.compile("[a-zA-Z_]+(\\?)?");
  private Pattern variablePattern = Pattern.compile(":[a-zA-Z_]+");
  private CommandFactoryInterface commandFactory;
  private Turtle turtle;
  private List<Map.Entry<String, Pattern>> mySymbols;
  private Language language;
  private HashMap<Pattern, String> translations;
  private String finalValue = "";

  private static final String RESOURCES_PACKAGE =
      "resources.";

  public static ResourceBundle commandPackageNames = ResourceBundle
      .getBundle(RESOURCES_PACKAGE + "CommandPackages");

  public static ResourceBundle commandParameterNumbers = ResourceBundle
      .getBundle(RESOURCES_PACKAGE + "ParameterNumbers");

  private static final String ERRORS = RESOURCES_PACKAGE + "ErrorMessages";

  //make a properties file for errors
  private ResourceBundle errors = ResourceBundle.getBundle(ERRORS);

  // private static final String COMMAND_PACKAGES = CommandTreeExecutor.class.getPackageName() + ".resources.packages.CommandPackages.properties";


  public CommandTreeExecutor(CommandFactoryInterface factory, Turtle turtle,
      HashMap<Pattern, String> translations, Language language) {
    this.language = language;
    this.turtle = turtle;
    commandFactory = factory;
    this.translations = translations;
  }

  public String executeTrees(List<TreeNode> elementNodes) {

    for (TreeNode element : elementNodes) {
      executeSubTree(element);
      System.out.println("EXECUTED NODES: " + element.getName());
      for (TreeNode child : element.getChildren()) {
        System.out.println("Children: " + child.getName());
      }
    }
    System.out.println("Last commands result " + elementNodes.get(elementNodes.size() - 1).getResult());
    finalValue = elementNodes.get(elementNodes.size()-1).getResult();
    return finalValue;
  }

    private void executeSubTree(TreeNode element) {
        if (match(element.getName(), commandPattern)) {
            ArrayList<TreeNode> children = element.getChildren();
            ArrayList<String> parameters = new ArrayList<>();
            // will also need to check for to commands
            if (getSymbol(element.getName()).equals("MakeVariable") || getSymbol(element.getName()).equals("MakeUserInstruction")) {
                System.out.println("YEET2");
                parameters.add(children.get(0).getName());
                children.remove(0);
            }
            for (TreeNode child : children) {
                executeSubTree(child);
                parameters.add(child.getResult());
            }
            String commandClass = "";
            if (CustomCommandMap.isACustomCommand(element.getName())) {
                commandClass = "slogo.model.Commands." + "VCUCommands" + "." + "CustomCommand";
            } else {
                commandClass = "slogo.model.Commands." + CommandTypeHashMap.getCommandType(getSymbol(element.getName())) + "." + getSymbol(element.getName());
                int numParamsShouldHave = Integer.parseInt(commandParameterNumbers.getString(getSymbol(element.getName())));
                // System.out.println(parameters.size() + " " + numParamsShouldHave);
                if (parameters.size() != numParamsShouldHave) {
                    throw new CommandException(errors.getString("WrongParameterNumber"));
                }
            }
            System.out.println(commandClass);
            Command commandObject = commandFactory.createCommand(commandClass);
            for (String s : parameters) {
                System.out.println("Param of " + element.getName() + ": " + s);
            }
            commandObject.setParams(parameters);
            commandObject.setTurtle(turtle);
            commandObject.setMiniParserLanguage(language);
            commandObject.doCommand(element);
            //nd.setData(replacementValue);
        } else if (match(element.getName(), variablePattern)) {
            element.setResult(VariableHashMap.getVarValue(element.getName()));
        }
      }

  // for variables later on

  private boolean match(String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }

//        if(type.equals(VARIABLE_KEY)){
//            nd.setData(myVars.getVariable(nd.getName()));
//        }

  private String getSymbol(String text) {
    final String ERROR = "NO MATCH";
    for (Map.Entry<Pattern, String> e : translations.entrySet()) {
      if (match(text, e.getKey())) {
        //System.out.println(e.getKey());
        return e.getValue();
      }
    }
    // FIXME: perhaps throw an exception instead
    return ERROR;
  }
}
