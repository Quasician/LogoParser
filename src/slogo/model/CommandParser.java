package slogo.model;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import slogo.View.Language;
import slogo.model.Commands.Command;
import slogo.model.Commands.CommandFactory;
import slogo.model.Commands.CommandFactoryInterface;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class CommandParser {

  // where to find resources specifically for this class
  private static final String RESOURCES_PACKAGE =
      CommandParser.class.getPackageName() + ".resources.languages.";

  private static final String THIS_PACKAGE = CommandParser.class.getPackageName() + ".";

  // "types" and the regular expression patterns that recognize those types
  private List<Entry<String, Pattern>> mySymbols;
  private Map<String, Command> stringToCommand;
  private Turtle turtle;
  private ObjectProperty<Turtle> turtleProperty = new SimpleObjectProperty<Turtle>(this, "turtle");
  private CommandFactoryInterface commandFactory;
  private CommandTreeExecutor treeExec;
  private CommandTreeConstructor treeMaker;
  private HashMap<Pattern,String> translations = new HashMap<>();
  private static final String RESOURCES = "resources.";
  private static final String ERRORS = RESOURCES + "ErrorMessages";
  private ResourceBundle errors = ResourceBundle.getBundle(ERRORS);
  private Language language;

  /**
   * Create an empty parser
   */
  public CommandParser(Turtle turtle, Language language) {
    this.language = language;
    mySymbols = new ArrayList<>();
    addPatterns(this.language.getCurrentLanguage());
    createReverseHashMap(mySymbols);
    commandFactory = new CommandFactory();
    this.turtle = turtle;
    System.out.println(RESOURCES_PACKAGE + language);
  }

  //add a listener in the command parser
  //that can tell when the language is changed

  /**
   * Adds the given resource file to this language's recognized types
   */
  public void addPatterns(String syntax) {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      mySymbols.add(new SimpleEntry<>(key,
          // THIS IS THE IMPORTANT LINE
          Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
  }

  /**
   * Returns language's type associated with the given text if one exists.
   * Throws an error if there is no match
   */
  public String getSymbol(String text) {
    final String ERROR = "NO MATCH";
    try {
      for (Entry<String, Pattern> e : mySymbols) {
        if (match(text, e.getValue())) {
          return e.getKey();
        }
      }
    } catch (Exception e) {
      throw new CommandException(new Exception(), errors.getString("InvalidCommand"));
    }
    return ERROR;
    //return ERROR;
  }

  public void createReverseHashMap (List<Entry<String, Pattern>> mySymbols) {
    for (Entry<String, Pattern> e : mySymbols) {
      translations.putIfAbsent(e.getValue(), e.getKey());
    }
    // FIXME: perhaps throw an exception instead
  }

  // Returns true if the given text matches the given regular expression pattern
  private boolean match(String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }

  public String parseText(String commandLine) {
    Pattern constantPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
    Pattern commandPattern = Pattern.compile("[a-zA-Z_]+(\\?)?");

    System.out.println("The current language is " + language.getCurrentLanguage());
    mySymbols = new ArrayList<>();
    addPatterns(language.getCurrentLanguage());

    String[] lineValues = commandLine.split("\\s+");

    for (int i = 0; i < lineValues.length; i++) {
      if (match(lineValues[i], commandPattern)) {
        lineValues[i] = getSymbol(lineValues[i]);
        System.out.println("ELEMENT:" + lineValues[i]);
      }
      if (lineValues[i].equals("\n")) {
        lineValues[i] = "|n";
      }
      System.out.println("GENERAL ELEMENT:" + lineValues[i]);
    }
    String translatedCommands = String.join(" ", lineValues);
    System.out.println("TRANSLATED: " +translatedCommands);
    return makeCommandTree(translatedCommands);
  }

  public String miniParse(String commandLine) {
    Pattern constantPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
    Pattern commandPattern = Pattern.compile("[a-zA-Z_]+(\\?)?");
    mySymbols = new ArrayList<>();
    addPatterns(language.getCurrentLanguage());
    String[] lineValues = commandLine.split("\\s+");
//    for (int i = 0; i < lineValues.length; i++) {
//      if (lineValues[i].equals("\n")) {
//        lineValues[i] = "|n";
//      }
//    }
    String translatedCommands = String.join(" ", lineValues);
    System.out.println("TRANSLATED: " +translatedCommands);
    return makeCommandTree(translatedCommands);
  }

  private String makeCommandTree(String commands) {
    treeMaker = new CommandTreeConstructor(translations);
    ArrayList<TreeNode> head = (ArrayList) treeMaker.buildTrees(commands);
    treeExec = new CommandTreeExecutor(commandFactory, turtle, translations, language);
    return treeExec.executeTrees(head);
  }
}
