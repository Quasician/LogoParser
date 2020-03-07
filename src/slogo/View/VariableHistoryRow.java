package slogo.View;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Map;

public class VariableHistoryRow extends HBox{
  private Text variableText;
  private static final String enterString ="Enter";
  private static final int buttonHeight= 25;
  private static final int buttonWidth= 50;
  private Text valText;
  private static final int fontSize= 13;
  private ViewButton enterBtn = new ViewButton(enterString, buttonHeight, buttonWidth, fontSize);


  public VariableHistoryRow(Map.Entry entry, ObservableMap<String,String> variables) {
    super.setSpacing(10);
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
