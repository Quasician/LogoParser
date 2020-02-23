package slogo.View;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javax.lang.model.element.Element;
import java.util.HashMap;

public class VariableHistory {
    private HashMap<String, Integer> Variables;
    private VBox variablesHolder;
    private static final Paint textColor= Color.BLACK;
    private Label variable;
    private static final String style = "-fx-background-color: rgba(255, 255, 255, 0.5);";
    private static final int spacing=10;

    public VariableHistory(){
        Variables=new HashMap<>();
        variablesHolder= new VBox(spacing);
        variablesHolder.setPrefHeight(300.0);
        variablesHolder.setPrefWidth(300.0);
        variablesHolder.setMargin(variablesHolder,new Insets(10,5,10,0));
        variable = new Label();
        variable.setTextFill(textColor);
        variable.setMaxHeight(10);
        variable.setMaxWidth(10);
        variablesHolder.setBackground(Background.EMPTY);
        variablesHolder.setStyle(style);
    }

    public void addVariable(String name, int value){
        variable=new Label();
           if(Variables.containsKey(name)){
                Variables.replace(name,value);

            }else {
                Variables.putIfAbsent(name, value);
            }
           variable.setText(name);
           variablesHolder.getChildren().add(variable);
           variablesHolder.setPadding(new Insets(10));
           variablesHolder.setBorder(Border.EMPTY);
        }


    public Integer getVariable(String name){
             if(Variables.containsKey(name)){
                return Variables.get(name);
            }
             return null;
    }

    protected Node getScene(){
      return variablesHolder;
    }

}
