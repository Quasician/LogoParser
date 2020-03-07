package slogo.model;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import slogo.View.Language;
import slogo.model.Commands.Command;
import slogo.model.Commands.CommandFactory;
import slogo.model.Commands.CommandFactoryInterface;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class CommandParser {

  private static final String MAKE_USER_INSTRUCTION = "MakeUserInstruction";
  private static GeneralParserBehavior BEHAVIOR = new GeneralParserBehavior();
  private List<Entry<String, Pattern>> mySymbols;
  private ObservableList<Turtle> turtles;
  private ObservableMap<String,String> variables;
  private CommandFactoryInterface commandFactory;
  private CommandTreeExecutor treeExec;
  private CommandTreeConstructor treeMaker;
  private HashMap<Pattern,String> translations = new HashMap<>();
  private static final String RESOURCES = BEHAVIOR.getResourcesString();
  private ResourceBundle errors = BEHAVIOR.getErrorBundle();
  private Language language;
  private DisplayOption displayOption;
  private CustomCommandStorage customCommandStorage;

  /**
   * Create an empty parser
   */
  public CommandParser(ObservableList<Turtle> turtles, ObservableMap<String, String> variables, Language language, CustomCommandStorage customCommandStorage) {
    this.language = language;
    mySymbols = new ArrayList<>();
    addPatterns(this.language.getCurrentLanguage());
    createReverseHashMap(mySymbols);
    commandFactory = new CommandFactory();
    this.turtles = turtles;
    this.variables = variables;
    this.customCommandStorage = customCommandStorage;
  }

  public void setDisplayOption(DisplayOption dispOption) {
    displayOption = dispOption;
  }

  public void setTurtles(ObservableList<Turtle> turtles) {
    this.turtles = turtles;
  }

  /**
   * Adds the given resource file to this language's recognized types
   */
  public void addPatterns(String syntax) {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES + syntax);
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      mySymbols.add(new SimpleEntry<>(key,
          Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
  }

  /**
   * Returns language's type associated with the given text if one exists.
   * Throws an error if there is no match
   */
  public String getSymbol(String text) {
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    throw new CommandException(new Exception(), errors.getString("InvalidCommand"));
  }

  public void createReverseHashMap (List<Entry<String, Pattern>> mySymbols) {
    for (Entry<String, Pattern> e : mySymbols) {
      translations.putIfAbsent(e.getValue(), e.getKey());
    }
  }

  // Returns true if the given text matches the given regular expression pattern
  private boolean match(String text, Pattern regex) {
    return regex.matcher(text).matches();
  }

  public String parseText(String commandLine) {
    mySymbols = new ArrayList<>();
    addPatterns(language.getCurrentLanguage());
    String[] lineValues = commandLine.split("\\s+");
    boolean toCommand = false;

    for (int i = 0; i < lineValues.length; i++) {
      if (match(lineValues[i], BEHAVIOR.getCommandPattern())) {
        String string = lineValues[i];
        if (toCommand) {
          toCommand = false;
        } else if (!customCommandStorage.getKeySet().contains(string)) {
          lineValues[i] = getSymbol(lineValues[i]);
          if (getSymbol(string).equals(MAKE_USER_INSTRUCTION)) {
            toCommand = true;
          }
        }
      }
      System.out.println("GENERAL ELEMENT:" + lineValues[i]);
    }
    String translatedCommands = String.join(" ", lineValues);
    if (translatedCommands.trim().equals(""))
      throw new CommandException(errors.getString("Empty"));
    return makeCommandTree(translatedCommands);
  }

  public String miniParse(String commandLine) {
    mySymbols = new ArrayList<>();
    addPatterns(language.getCurrentLanguage());
    String[] lineValues = commandLine.split("\\s+");
    String translatedCommands = String.join(" ", lineValues);
    return makeCommandTree(translatedCommands);
  }

  private String makeCommandTree(String commands) {
    treeMaker = new CommandTreeConstructor(translations, customCommandStorage);
    ArrayList<TreeNode> head = (ArrayList) treeMaker.buildTrees(commands);
    treeExec = new CommandTreeExecutor(commandFactory, turtles, variables, translations, language,  customCommandStorage);
    treeExec.setDisplayOption(displayOption);
    return treeExec.executeTrees(head);
  }

}