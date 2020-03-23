package slogo.View;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * The VariableHistory class displays all the defined variables so far. There also an interactive component in which the user can edit the value of
 * a defined variable.
 *
 * Purpose: To give the user a visual on all the defined variables. Whenever a variable value is updated, it is also reflected in ListView.
 *
 * Assumptions: That every variable defined will have a String representation.
 *
 * Dependencies: ObservableMap<String, String>, ListView, VariableHistoryRow
 *
 * Example: Create an ObservableMap of String keys and values. Use that to create a VariableHistory object. Now, when a new variable is created,
 *          the String name of the variable is saved as a key and the String representation of the variable value is saved as a value. This entry is
 *          added to the ObservableMap internally, and then user sees a new row with the variable name followed by its value, which is also followed
 *          by a TextField and a "Enter" button so that the user can interactively change the value of the variables.
 *          For example, let's say "make :a 10" was entered. A new row appears, the contents of the row being ":a 10 *TextField* *Enter Button*".
 *          If the user then typed in "20" into this TextField and pressed "Enter," then the value of the variable and its display would change instantly
 *          so that the row now showed ":a 20 *TextField* *Enter Button*".
 *          If the user typed "make :a 30" into the command line, the same row would be updated to show ":a 30 *TextField* *Enter Button*"
 *
 * @author Michelle Tai
 */
public class VariableHistory implements HistoryView {

  private ListView variablesHolder;
  private static final double VAR_BOX_HEIGHT = 310.0;
  private static final double VAR_BOX_WIDTH = 300.0;
  private VBox variableHist;
  private ObservableMap<String, String> variables;

  /**
   * Constructor for the VariableHistory class that takes in an ObservableMap as a parameter so that a new row will be added to the ListView of the
   * variables when there is a new variable made.
   * @param variables is an ObservableMap of String keys and objects that is updated when a new variable is created or a variable's value
   *        is changed.
   */
  public VariableHistory(ObservableMap<String, String> variables) {
    variableHist = new VBox();
    variableHist.setPrefWidth(VAR_BOX_WIDTH);
    variableHist.setPrefHeight(VAR_BOX_HEIGHT);
    this.variables = variables;
    variablesHolder = new ListView();
    variablesHolder.setPrefHeight(VAR_BOX_HEIGHT);
    variablesHolder.setPrefWidth(VAR_BOX_WIDTH);

    variables.addListener(new MapChangeListener() {
      @Override
      public void onChanged(MapChangeListener.Change change) {
        variablesHolder.getItems().clear();
        for (Map.Entry entry : variables.entrySet()) {
          VariableHistoryRow row = new VariableHistoryRow(entry, variables);
          variablesHolder.getItems().add(row);
        }
      }
    });

    variableHist.getChildren().addAll(variablesHolder);
  }

  /**
   * @return the Node that holds the ListView, aka the whole display, of the variable history
   */
  public Node returnScene() {
    return variableHist;
  }

}
