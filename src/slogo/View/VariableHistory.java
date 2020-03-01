package slogo.View;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.HashMap;
import javafx.util.Callback;
import slogo.model.VariableHashMap;

public class VariableHistory {
    private ListView variablesHolder;
    private TableView variablesTable;
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
//        varMap = new HashMap<>();
//        variablesHolder= new ListView();
//        variablesHolder.setPrefHeight(VAR_BOX_HEIGHT);
//        variablesHolder.setPrefWidth(VAR_BOX_WIDTH);

        variablesTable = new TableView();


        TableColumn<Entry<String, String>, String> column1 = new TableColumn<>("Variable Name");
        column1.setPrefWidth(100);
        column1.setCellValueFactory(new Callback<CellDataFeatures<Entry<String, String>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                // this callback returns property for just one cell, you can't use a loop here
                // for first column we use key
                return new SimpleStringProperty(p.getValue().getKey());
            }
        });

        TableColumn<Map.Entry<String, String>, String> column2 = new TableColumn<>("Variable Value");
        column2.setPrefWidth(100);
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                // for second column we use value
                return new SimpleStringProperty(p.getValue().getValue());
            }
        });

        ObservableList<Entry<String, String>> items =  VariableHashMap.getAllVariables();

        final TableView<Entry<String,String>> table = new TableView<>(VariableHashMap.getAllVariables());

        table.getColumns().setAll(column1, column2);
        System.out.println("table made");


//        variablesHolder.setPrefHeight(VAR_BOX_HEIGHT);
//        variablesHolder.setPrefWidth(VAR_BOX_WIDTH);

        // TODO: make these strings read from properties files
//        nameCol= new TableColumn("Name");
//        valCol= new TableColumn("Value");
//        varHistLabel = new Label("varMap History");
//        varHistLabel.setTextFill(TEXT_COLOR);
//        varHistLabel.setMaxHeight(VAR_LABEL_HEIGHT);
//        varHistLabel.setMaxWidth(VAR_LABEL_HEIGHT);
//        variablesHolder.setBackground(Background.EMPTY);
//        variablesHolder.setStyle(LISTVIEW_STYLE);
        variableHist.getChildren().addAll(variablesTable);
    }

    public void addVariable(String name, String value){
//        Label newNameBar= new Label(name);
//        Label newValueBar= new Label(value);
//        HBox vari= new HBox();
//        vari.getChildren().addAll(newNameBar,newValueBar);
//        variablesHolder.getItems().add(vari);
        VariableHashMap.addToMap(name, value);
        }


//    public String getVariable(String name){
//             if(varMap.containsKey(name)){
//                return varMap.get(name);
//            }
//             return null;
//    }

    protected Node getScene(){
      return variableHist;
    }

}
