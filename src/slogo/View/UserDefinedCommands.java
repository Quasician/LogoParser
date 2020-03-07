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
    private ResourceBundle myResources = Main.MY_RESOURCES;
    private TableView ucdTable;
    private VBox udcVBox;
    private ObservableList<Triplet<String, String, String>> customCommandObsList;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final int SPACING = 300;
    private static final int BOX_SPACING = 300;

    private ArrayList<String> commandValues;
    private static final String STYLE = "-fx-background-color: rgba(0, 0,0, 0.5);";

    public UserDefinedCommands(ObservableList<Triplet<String, String, String>> customCommandObsList) {
      commandValues = new ArrayList<>();
      udcVBox = new VBox();

      TableColumn<Triplet<String, String, String>, String> column1 = new TableColumn<>("User-Defined Command Name");
      column1.setPrefWidth(100);
      column1.setCellValueFactory(e -> e.getValue().getFirstStringProperty());


      TableColumn<Triplet<String, String, String>, String> column2 = new TableColumn<>("Variables");
      column2.setPrefWidth(50);
      column2.setCellValueFactory(e ->e.getValue().getSecondStringProperty());

      TableColumn<Triplet<String, String, String>, String> column3 = new TableColumn<>("Commands");
//      column3.setPrefWidth(100);
      column3.setCellValueFactory(e -> e.getValue().getThirdStringProperty());

      final TableView<Triplet<String, String, String>> table = new TableView<>(customCommandObsList);
      table.setPrefHeight(310.0);
      table.setPrefWidth(300.0);
      table.getColumns().setAll(column1, column2, column3);

      udcVBox.getChildren().add(table);
    }

    public Node returnScene(){
        return udcVBox;
    }

}







