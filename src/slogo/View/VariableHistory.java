package slogo.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.HashMap;

public class VariableHistory {
    private HashMap<String, String> varMap;
    private ListView variablesHolder;
    private static final Paint TEXT_COLOR = Color.BLACK;
    private Label varHistLabel;
    private static final String LISTVIEW_STYLE = "-fx-background-color: rgba(255, 255, 255, 0.5);";
    private static final double VAR_BOX_HEIGHT = 310.0;
    private static final double VAR_BOX_WIDTH = 300.0;
    private static final int VAR_LABEL_HEIGHT = 10;
    private TableColumn nameCol;
    private TableColumn valCol;
    private VBox variableHist;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;


    public VariableHistory(String name, String value){
            this.firstName = new SimpleStringProperty(name);
            this.lastName = new SimpleStringProperty(value);
    }


    public VariableHistory(){
        variableHist = new VBox();
        varMap = new HashMap<>();
        variablesHolder= new ListView();
        variablesHolder.setPrefHeight(VAR_BOX_HEIGHT);
        variablesHolder.setPrefWidth(VAR_BOX_WIDTH);
        // TODO: make these strings read from properties files
        nameCol= new TableColumn("Name");
        valCol= new TableColumn("Value");
        varHistLabel = new Label("varMap History");
        varHistLabel.setTextFill(TEXT_COLOR);
        varHistLabel.setMaxHeight(VAR_LABEL_HEIGHT);
        varHistLabel.setMaxWidth(VAR_LABEL_HEIGHT);
        variablesHolder.setBackground(Background.EMPTY);
        variablesHolder.setStyle(LISTVIEW_STYLE);
        variableHist.getChildren().addAll(varHistLabel,variablesHolder);
    }

    public void addVariable(String name, String value){
        Label newNameBar= new Label(name);
        Label newValueBar= new Label(value);
        HBox vari= new HBox();
        vari.getChildren().addAll(newNameBar,newValueBar);
        variablesHolder.getItems().add(vari);
    }


    public String getVariable(String name){
             if(varMap.containsKey(name)){
                return varMap.get(name);
            }
             return null;
    }

    protected Node getScene(){
      return variablesHolder;
    }

}
