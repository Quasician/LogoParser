package slogo.View;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import slogo.model.VariableHashMap;

public class VariableHistory implements HistoryView{
    private ListView variablesHolder;
    private static final double VAR_BOX_HEIGHT = 310.0;
    private static final double VAR_BOX_WIDTH = 300.0;
    private VBox variableHist;
    private static ObservableMap<String, String> myObservableMap;

    public VariableHistory(){
        variableHist = new VBox();
        variableHist.setPrefWidth(VAR_BOX_WIDTH);
        variableHist.setPrefHeight(VAR_BOX_HEIGHT);
        myObservableMap = VariableHashMap.getObservableMap();
        variablesHolder = new ListView();
        variablesHolder.setPrefHeight(VAR_BOX_HEIGHT);
        variablesHolder.setPrefWidth(VAR_BOX_WIDTH);

        myObservableMap.addListener(new MapChangeListener() {
            @Override
            public void onChanged(MapChangeListener.Change change) {
                if(change.wasAdded() && change.wasRemoved()){
                    ObservableList<VariableHistoryRow> listOfRows2 = variablesHolder.getItems();
                    for(int i = 0; i < listOfRows2.size(); i++) {
                        String key = change.getKey().toString();
                        String rowVar = listOfRows2.get(i).getVar().toString();
                        if (key.equals(rowVar)) {
                            listOfRows2.set(i, new VariableHistoryRow(change));
                            break;
                        }
                    }
                }
                else if(change.wasAdded() && !change.wasRemoved()){
                    VariableHistoryRow row = new VariableHistoryRow(change);
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
