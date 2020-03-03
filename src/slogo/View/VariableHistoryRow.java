package slogo.View;

import javafx.collections.MapChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import slogo.model.VariableHashMap;

public class VariableHistoryRow extends HBox{
  private Text variableText;
  private Text valText;
  private ViewButton enterBtn = new ViewButton("Enter", 25, 50, 13);


  public VariableHistoryRow(MapChangeListener.Change change) {
    super.setSpacing(10);
    variableText = new Text(change.getKey().toString());
    valText = new Text(VariableHashMap.getVarValue(change.getKey().toString()));
    TextField textfield = new TextField();
    enterBtn.setOnAction(e->{
      VariableHashMap.addToMap(variableText.getText(), textfield.getText());
      valText.setText(textfield.getText());
    });
    this.getChildren().addAll(variableText, valText, textfield, enterBtn);
  }

  protected String getVar(){
    return variableText.getText();
  }

}
