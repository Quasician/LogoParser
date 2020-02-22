package slogo.model;

import slogo.model.Commands.Command;
import slogo.model.Commands.CommandFactory;

import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CommandParser {

  // where to find resources specifically for this class
  private static final String RESOURCES_PACKAGE = CommandParser.class.getPackageName() + ".resources.languages.";

  private static final String THIS_PACKAGE = CommandParser.class.getPackageName() + ".";

  // "types" and the regular expression patterns that recognize those types
  // note, it is a list because order matters (some patterns may be more generic)
  private List<Entry<String, Pattern>> mySymbols;

  private Map<String, Command> stringToCommand;
  private Turtle turtle;

  /**
   * Create an empty parser
   */
  public CommandParser (Turtle turtle) {
    mySymbols = new ArrayList<>();
    this.turtle = turtle;
  }

  /**
   * Initializes and adds values to the map, mapping strings in mySymbols
   * to Command objects.
   *
   * Note: every Command implementation should have a basic constructor that
   * just takes in a string
   */
  public void makeMap() {
    stringToCommand = new HashMap<>();
    for (Entry<String, Pattern> entry : mySymbols) {
      String string = entry.getKey();
      System.out.println(string);
      Command command;

      Constructor[] constructors = null;
      try {
        constructors = Class.forName(THIS_PACKAGE + string).getConstructors();
      } catch (ClassNotFoundException e) {
        constructors = null;
        //throw an exception
      }

      try {
        command = (Command) constructors[0].newInstance(string);
      } catch (Exception e) {
        command = null;
      }
      stringToCommand.put(string, command);
    }
  }

  /**
   * Adds the given resource file to this language's recognized types
   */
  public void addPatterns (String syntax) {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      mySymbols.add(new SimpleEntry<>(key,
          // THIS IS THE IMPORTANT LINE
          Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
  }

  /**
   * Returns language's type associated with the given text if one exists
   */
  public String getSymbol (String text) {
    final String ERROR = "NO MATCH";
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        System.out.println(e.getValue());
        return e.getKey();
      }
    }
    // FIXME: perhaps throw an exception instead
    return ERROR;
  }


  // Returns true if the given text matches the given regular expression pattern
  private boolean match (String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }


  public void parseText(String commandLine)
  {
    CommandStack commandStack = new CommandStack();
    String[] lineValues = commandLine.split(" ");
    fillCommandStack(lineValues,commandStack);
    printStack(commandStack);
  }

  // currently only pushes commands and constants ot their respective stacks (not variables)
  private void fillCommandStack(String[] lineValues, CommandStack commandStack)
  {
    Pattern constantPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
    Pattern commandPattern = Pattern.compile("[a-zA-Z_]+(\\?)?");
    for(String s: lineValues)
    {
      if (match(s, constantPattern))
      {
        commandStack.pushOntoValueStack(Integer.parseInt(s));
        continue;
      }
      String command = getSymbol(s);
      if(match(s, commandPattern) && !command.equals("NO MATCH!") )
      {
        commandStack.pushOntoCommandStack(command);
      }
    }
  }


  // Prints entire stack until command stack (not the value stack) is empty
  private void printStack(CommandStack commandStack)
  {
    while(!commandStack.isCommandStackEmpty())
    {
      String command = commandStack.popCommandStack();
      System.out.print("\n" + command + " ");
      if(!commandStack.isValueStackEmpty())
      {
        Command commandObject = CommandFactory.getCommandInstance("slogo.model.Commands."+command);
        commandObject.setCommandStack(commandStack);
        commandObject.setTurtle(turtle);
        //System.out.println("Param number" + commandObject.getParamNumber());
        for(int i = 0; i<commandObject.getParamNumber();i++)
        {
          // needs a try catch in case there is not enough params on the value stack -> could also change the if statement to circumvent this
          commandObject.getParamList()[i] = commandStack.popValueStack();
          //System.out.println(commandObject.getParamList()[i]);
        }
        commandObject.doCommand();

      }
    }
    System.out.println(commandStack.popValueStack());
  }

// testing
//  public static void main(String[] args) {
//    CommandParser c = new CommandParser();
//
//    String english = "English";
//    String chinese = "Chinese";
//
//    String forward = "forward";
//    String chineseCommand = "nizhengqie";
//
//    c.addPatterns(english);
//    c.addPatterns(chinese);
////    System.out.println(c.getSymbol(forward));
////    System.out.println(c.getSymbol(chineseCommand));
//
//    c.makeMap();
//
//    forward = "Forward";
//
//  }

}
