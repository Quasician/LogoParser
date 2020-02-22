package slogo.model;

import javafx.scene.control.Alert;
import slogo.model.Commands.Command;
import slogo.model.Commands.CommandFactory;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class CommandParser {

  // where to find resources specifically for this class
  private static final String RESOURCES_PACKAGE = CommandParser.class.getPackageName() + ".resources.languages.";
  // "types" and the regular expression patterns that recognize those types
  // note, it is a list because order matters (some patterns may be more generic)
  private List<Entry<String, Pattern>> mySymbols;


  /**
   * Create an empty parser
   */
  public CommandParser () {

    mySymbols = new ArrayList<>();
  }

  /**
   * Adds the given resource file to this language's recognized types
   */
  public void addPatterns (String syntax) {
    try {
      ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
      for (String key : Collections.list(resources.getKeys())) {
        String regex = resources.getString(key);
        mySymbols.add(new SimpleEntry<>(key,
                // THIS IS THE IMPORTANT LINE
                Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
      }
    }catch ( MissingResourceException e)
    {
      showError("No resource file with that name.");
    }
  }

  /**
   * Returns language's type associated with the given text if one exists
   */
  public String getSymbol (String text) {
    final String ERROR = "NO MATCH!";
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    // FIXME: perhaps throw an exception instead
    showError(ERROR + " Cannot understand the command "+ text);
    return ERROR;
  }


  // Returns true if the given text matches the given regular expression pattern
  private boolean match (String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }

  // parses a line of text (currently only parses 1 line of text), fills the command stack, prints the command stack
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
  //DUMMY COMMENT
  private void printStack(CommandStack commandStack)
  {
    while(!commandStack.isCommandStackEmpty())
    {
      String command = commandStack.popCommandStack();
      System.out.print("\n" + command+ " ");
      if(!commandStack.isValueStackEmpty())
      {
        Command commandObject = CommandFactory.getCommandInstance("slogo.model.Commands."+command + "Command");
        commandObject.setCommandStack(commandStack);
        for(int i = 0; i<commandObject.getParamNumber();i++)
        {
          // needs a try catch in case there is not enough params on the value stack -> could also change the if statement to circumvent this
          commandObject.getParamList()[i] = commandStack.popValueStack();
          System.out.print(commandObject.getParamList()[i]);
        }
        commandObject.doCommand();
      }

    }
  }

  // Alert method that shows error inside of a pop up box
  private void showError(String mes)
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText(mes);
    alert.showAndWait();
  }

  //testing
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
//    System.out.println(c.getSymbol(forward));
//    System.out.println(c.getSymbol(chineseCommand));
//  }

}
