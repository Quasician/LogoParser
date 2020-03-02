package slogo.View;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.swing.text.View;
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
    private static ObservableMap<String, String> myObservableMap;

//    private Map<String, String>


    public VariableHistory(String name, String value){
            this.firstName = new SimpleStringProperty(name);
            this.lastName = new SimpleStringProperty(value);
    }


    public VariableHistory(){
        variableHist = new VBox();
        variableHist.setPrefWidth(VAR_BOX_WIDTH);
        variableHist.setPrefHeight(VAR_BOX_HEIGHT);
        myObservableMap = VariableHashMap.getObservableMap();
        List<VariableHistoryRow> listOfRows = new ArrayList<>();



//        varMap = new HashMap<>();
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
//                            System.out.println("found removed" + key);
                            listOfRows2.set(i, new VariableHistoryRow(change));
                            break;
                        }
                    }
                }
                if(change.wasAdded() && !change.wasRemoved()){
                    VariableHistoryRow row = new VariableHistoryRow(change);
                    variablesHolder.getItems().add(row);
                }
//                else{
//
//                    }
////                    listOfRows.set(i, new VariableHistoryRow(change));
//                }
            }
        });

        variableHist.getChildren().addAll(variablesHolder);
//        TableColumn<Entry<String, String>, String> column1 = getKeyTableColumn();
//
//        TableColumn<Entry<String, String>, String> column2 = getValueTableColumn();
//
//        ObservableList<String> keysList =  VariableHashMap.getKeysList();
//
//        VariableHashMap.addToMap("hi", "5");
//
//        variablesTable = new TableView<>(VariableHashMap.getAllVariables());
//        variablesTable.getColumns().setAll(column1, column2);
//        System.out.println("table made");
//
//        // single cell selection mode
//        //what is i had a list of keys and list of values and used indexing to get each
//        //when change value,
//        variablesTable.setEditable(true);
//        variablesTable.getSelectionModel().setCellSelectionEnabled(true);
//
//        Callback<TableColumn<Entry<String, String>, String>, TableCell<Entry<String, String>, String>> cellFactoryForMap = new Callback<TableColumn<Entry<String, String>, String>, TableCell<Entry<String, String>, String>>() {
//            @Override
//            public TableCell call(TableColumn p) {
//                return new TextFieldTableCell(new StringConverter() {
//                    @Override
//                    public String toString(Object t) {
//                        return t.toString();
//                    }
//                    @Override
//                    public Object fromString(String string) {
//                        return string;
//                    }
//                });
//            }
//        };
//        column1.setCellFactory(cellFactoryForMap);
//        column2.setCellFactory(cellFactoryForMap);
//
//
////        column1.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue()));
//
//        variableHist.getChildren().addAll(variablesTable);
//        variablesHolder.getItems().addAll(listOfRows);
    }



//    private TableColumn<Entry<String, String>, String> getValueTableColumn() {
//        TableColumn<Entry<String, String>, String> column2 = new TableColumn<>("Variable Value");
//        column2.setPrefWidth(100);
//        column2.setCellValueFactory(new Callback<CellDataFeatures<Entry<String, String>, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<Entry<String, String>, String> p) {
//                // for second column we use value
//                return new SimpleStringProperty(p.getValue().getValue());
//            }
//        });
//        return column2;
//    }

//    private TableColumn<Entry<String, String>, String> getKeyTableColumn() {
//        TableColumn<Entry<String, String>, String> column1 = new TableColumn<>("Variable Name");
//        column1.setPrefWidth(100);
//        column1.setCellValueFactory(new Callback<CellDataFeatures<Entry<String, String>, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(CellDataFeatures<Entry<String, String>, String> p) {
//                // this callback returns property for just one cell, you can't use a loop here
//                // for first column we use key
//                return new SimpleStringProperty(p.getValue().getKey());
//            }
//        });
//
//        return column1;
//    }

    public void addVariable(String name, String value){
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
