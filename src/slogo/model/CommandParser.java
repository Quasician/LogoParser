package slogo.model;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
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
  private static final String RESOURCES_PACKAGE = "resources.";

  // "types" and the regular expression patterns that recognize those types
  private List<Entry<String, Pattern>> mySymbols;
//  private Turtle turtle;
//  private Map<String, Command> stringToCommand;
  private ObservableList<Turtle> turtles;
  //private ObservableList<Turtle> activatedTurtles;
  private ObjectProperty<Turtle> turtleProperty = new SimpleObjectProperty<Turtle>(this, "turtle");
  private CommandFactoryInterface commandFactory;
  private CommandTreeExecutor treeExec;
  private CommandTreeConstructor treeMaker;
  private HashMap<Pattern,String> translations = new HashMap<>();
  private static final String RESOURCES = "resources.";
  private static final String ERRORS = RESOURCES + "ErrorMessages";
  private ResourceBundle errors = ResourceBundle.getBundle(ERRORS);
  private Language language;
  private DisplayOption displayOption;

  /**
   * Create an empty parser
   */
  public CommandParser(ObservableList<Turtle> turtles, ObservableList<Turtle>activatedTurtles, Language language) {
    this.language = language;
    mySymbols = new ArrayList<>();
    addPatterns(this.language.getCurrentLanguage());
    createReverseHashMap(mySymbols);
    commandFactory = new CommandFactory();
    this.turtles = turtles;
    System.out.println(RESOURCES_PACKAGE + language);
  }

  public void setDisplayOption(DisplayOption disp) {
    displayOption = disp;
  }

  public void setTurtles(ObservableList<Turtle> turtles)
  {
    this.turtles = turtles;
  }

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

    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    System.out.println("INVALID COMMAND: " + text);
    throw new CommandException(new Exception(), errors.getString("InvalidCommand"));

   // return ERROR;
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

    boolean toCommand = false;

    for (int i = 0; i < lineValues.length; i++) {
      if (match(lineValues[i], commandPattern)) {
        String string = lineValues[i];

        if (toCommand) {
          toCommand = false;
        } else {
         if (CustomCommandMap.getKeySet().contains(string)) {

         } else {
           lineValues[i] = getSymbol(lineValues[i]);
         }
        }
        System.out.println("ELEMENT:" + lineValues[i]);
        if (string.equals("to"))
          toCommand = true;
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
    for (int i = 0; i < lineValues.length; i++) {
      System.out.println("GET ELEMENT AT i = " + lineValues[i]);
    }
    String translatedCommands = String.join(" ", lineValues);
    System.out.println("TRANSLATED: " +translatedCommands);
    return makeCommandTree(translatedCommands);
  }

  private String makeCommandTree(String commands) {
    treeMaker = new CommandTreeConstructor(translations);
    ArrayList<TreeNode> head = (ArrayList) treeMaker.buildTrees(commands);
    treeExec = new CommandTreeExecutor(commandFactory, turtles, translations, language);
    treeExec.setDisplayOption(displayOption);
    return treeExec.executeTrees(head);
  }
}
