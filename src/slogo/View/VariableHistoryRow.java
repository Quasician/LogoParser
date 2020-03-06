package slogo.View;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Map;

public class VariableHistoryRow extends HBox{
  private Text variableText;
  private Text valText;
  private ViewButton enterBtn = new ViewButton("Enter", 25, 50, 13);


  public VariableHistoryRow(Map.Entry entry, ObservableMap<String,String> variables) {
    super.setSpacing(10);
    variableText = new Text(entry.getKey().toString());
    valText = new Text(variables.get(entry.getKey().toString()));
    TextField textfield = new TextField();
    enterBtn.setOnAction(e->{
      //variables.putIfAbsent(variableText.getText(), "");
      variables.put(variableText.getText(), textfield.getText());
      valText.setText(textfield.getText());
    });
    this.getChildren().addAll(variableText, valText, textfield, enterBtn);
  }

  protected String getVar(){
    return variableText.getText();
  }

}
