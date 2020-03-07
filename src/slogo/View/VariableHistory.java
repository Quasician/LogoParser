package slogo.View;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.Map;


public class VariableHistory implements HistoryView{
    private ListView variablesHolder;
    private static final double VAR_BOX_HEIGHT = 310.0;
    private static final double VAR_BOX_WIDTH = 300.0;
    private VBox variableHist;
    private ObservableMap<String, String> variables;

    public VariableHistory(ObservableMap<String,String> variables){
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
                for(Map.Entry entry : variables.entrySet())
                {
                    VariableHistoryRow row = new VariableHistoryRow(entry, variables);
                    variablesHolder.getItems().add(row);
                }
            }
        });

        variableHist.getChildren().addAll(variablesHolder);
    }

    public Node returnScene(){
      return variableHist;
    }

}
