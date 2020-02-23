package slogo.View;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javax.lang.model.element.Element;
import java.util.HashMap;

public class VariableHistory {
    private HashMap<String, Element> Variables;
    private VBox variablesHolder;
    private static final Paint textColor= Color.WHITE;
    private Label variable;
    private static final String style = "-fx-background-color: rgba(0, 0, 0, 0.5);";
    private static final int spacing=10;

    public VariableHistory(){
        Variables=new HashMap<>();
        variablesHolder= new VBox(spacing);
        variable.setTextFill(textColor);
        variable.setMaxHeight(10);
        variable.setMaxWidth(10);
        variablesHolder.setBackground(Background.EMPTY);
        variablesHolder.setStyle(style);
    }

    public void addVariable(String name, Element value){
        variable=new Label();
           if(Variables.containsKey(name)){
                Variables.replace(name,value);

            }else {
                Variables.putIfAbsent(name, value);
            }
           variable.setText(name);
           variablesHolder.getChildren().add(variable);
        }


    public Element getVariable(String name){
             if(Variables.containsKey(name)){
                return Variables.get(name);
            }
             return null;
    }

}
