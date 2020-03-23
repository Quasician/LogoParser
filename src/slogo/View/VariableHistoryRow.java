package slogo.View;

import javafx.collections.ObservableMap;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * The VariableHistoryRow class creates a row that holds the variable name and value, as well as the input field and the corresponding "Enter" button.
 *
 * Purpose: To hold all the necessary information to create a new row (in VariableHistory) and be able to update the row whenever the variable's value
 *          changed. The latter is the main reason why a new class was created for the rows in oppose to having a method within the VariableHistory class.
 *
 * Assumption: The variable and its value will have String representations and that the user will be "smart" and keep the variable values of the same "type"
 *             even when updated (like if :a is an integer, then the new value will also be an integer)
 *
 * Dependencies: Map.Entry object, ObservableMap object
 *
 * Example:
 * <hr><blockquote><pre>
 *   private VBox variableHist = new VBox();
 *   private ListView variablesHolder = new ListView();
 *   ObservableMap<String, String> variables = ...;
 *   for (Map.Entry entry : variables.entrySet()) {
 *      VariableHistoryRow row = new VariableHistoryRow(entry, variables);
 *      variablesHolder.getItems().add(row);
 *   }
 * </pre></blockquote><hr>
 * Which adds a new row whenever for each entry in the ObservableMap
 *
 * @author Michelle Tai
 */
public class VariableHistoryRow extends HBox{
  private Text variableText;
  private static final String ENTER_STRING = "Enter";
  private static final int BUTTON_HEIGHT = 25;
  private static final int BUTTON_WIDTH = 50;
  private Text valText;
  private static final int SPACING =10;
  private static final int FONT_SIZE = 13;
  private ViewButton enterBtn = new ViewButton(ENTER_STRING, BUTTON_HEIGHT, BUTTON_WIDTH, FONT_SIZE);

  /**
   * The constructor for the VariableHistoryRow class that sets up the row in the follow order:
   * variable name, variable value, TextField, "Enter" button
   * @param entry is the entry that contains the key value pair of the variable
   * @param variables is the ObservableMap that holds all the variable names and values
   */
  public VariableHistoryRow(Map.Entry entry, ObservableMap<String,String> variables) {
    super.setSpacing(SPACING);
    variableText = new Text(entry.getKey().toString());
    valText = new Text(variables.get(entry.getKey().toString()));
    TextField textfield = new TextField();
    enterBtn.setOnAction(e->{
      variables.put(variableText.getText(), textfield.getText());
      valText.setText(textfield.getText());
    });
    this.getChildren().addAll(variableText, valText, textfield, enterBtn);
  }

  protected String getVar(){
    return variableText.getText();
  }

}
