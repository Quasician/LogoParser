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

/**
 * This is the panel that holds all the user defined commands. Each time a new command is made, a new cell is made in each
 * column corresponding to the command name, the variables needed, and the Turtle commands that make it up.
 *
 * Purpose: To create a view for the user to keep track what commands he/she has made so far.
 *
 * Assumptions: That the command will never be redefined. If it is, the command may be saved as the redefined command, but
 * that able will not update to reflect that.
 *
 * Dependencies: ObservableList of Triplets, HistoryView interface
 *
 * Example: You create an observable list of Triple objects, and when you add a new Triplet object, the first object within the Triplet is added
 * to the name column, the second object in the Triplet is added to the variables column, and the third object is added to the commands column.
 *
 * @author Michelle Tai
 */
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

  /**
   * Constructor for the UserDefinedCommands class that sets up the TableView that will hold all the custom commands
   * and the appropriate information.
   * @param customCommandObsList is an observable list of triplets that the TableView uses to update the table and add
   *        a new row when a new Triplet is added to the list
   * Note: Combed through a lot of different stack overflow posts to figure out how to use the cell factory, which may not be
   * written in a standard way
   */
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

  /**
   * @return the Node, which is the VBox names udcVBox, which holds the TableView
   */
  public Node returnScene(){
        return udcVBox;
    }

}







