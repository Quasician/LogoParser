package slogo.View;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.Main;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserDefinedCommands implements HistoryView{
    private VBox udcVBox;
    private ObservableList<Triplet<String, String, String>> customCommandObsList;
    private static final double PREF_WIDTH = 300.0;
    private static final double PREF_HEIGHT = 310.0;
    private static final double COL2_PREF_WIDTH = 50;
    private static final double COL_PREF_WIDTH = 100;
    private static final String USER_DEFINED_TEXT= "User-Defined Command Name";
    private static final String VARIABLES= "Variables";
    private static final String COMMANDS= "Commands";
    private ArrayList<String> commandValues;

    public UserDefinedCommands(ObservableList<Triplet<String, String, String>> customCommandObsList) {
      commandValues = new ArrayList<>();
      udcVBox = new VBox();
      TableColumn<Triplet<String, String, String>, String> column1 = new TableColumn<>(USER_DEFINED_TEXT);
      column1.setPrefWidth(COL_PREF_WIDTH);
      column1.setCellValueFactory(e -> e.getValue().getFirstStringProperty());
      TableColumn<Triplet<String, String, String>, String> column2 = new TableColumn<>(VARIABLES);
      column2.setPrefWidth(COL2_PREF_WIDTH);
      column2.setCellValueFactory(e ->e.getValue().getSecondStringProperty());
      TableColumn<Triplet<String, String, String>, String> column3 = new TableColumn<>(COMMANDS);
      column3.setCellValueFactory(e -> e.getValue().getThirdStringProperty());
      final TableView<Triplet<String, String, String>> table = new TableView<>(customCommandObsList);
      table.setPrefHeight(PREF_HEIGHT);
      table.setPrefWidth(PREF_WIDTH);
      table.getColumns().setAll(column1, column2, column3);
      udcVBox.getChildren().add(table);
    }

    public Node returnScene(){
        return udcVBox;
    }

}







