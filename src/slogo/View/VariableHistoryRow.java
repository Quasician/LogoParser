package slogo.View;

import javafx.collections.ObservableMap;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Map;

public class VariableHistoryRow extends HBox{
  private Text variableText;
  private static final String ENTER_STRING ="Enter";
  private static final int BUTTON_HEIGHT = 25;
  private static final int BUTTON_WIDTH = 50;
  private Text valText;
  private static final int SPACING =10;
  private static final int FONT_SIZE = 13;
  private ViewButton enterBtn = new ViewButton(ENTER_STRING, BUTTON_HEIGHT, BUTTON_WIDTH, FONT_SIZE);


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
