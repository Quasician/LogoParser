package slogo.View;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import slogo.model.Turtle;
import slogo.model.VariableHashMap;

import java.util.Map;

public class Configuration {
    private ListView variablesHolder;
    private ListView variablesTable;
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
    private Label PropertyName;
    private Label number= new Label();
    private HBox rowForSize;
    private HBox CoordForSize;
    public Configuration(String name, String value){
        this.firstName = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(value);
    }

    public Configuration(ObservableList<Turtle> turtles) {
        rowForSize= new HBox();
        CoordForSize= new HBox();
        variableHist = new VBox();
        variablesTable = new ListView<>();
        PropertyName=new Label("Number of Turtles ");
        number= new Label();
        addRowListener(turtles);

    }

    public void addRowListener(ObservableList<Turtle> turtles) {
        turtles.addListener((ListChangeListener<? super Turtle>) e-> {  int count=0;
            for(Turtle t: turtles){
                    count++;
            }
            number.setText(String.valueOf(count));
            rowForSize.getChildren().addAll(PropertyName,number);
            variablesTable.getItems().add(rowForSize);});
    }

    protected Node getScene(){
        return variablesTable;
    }
}












