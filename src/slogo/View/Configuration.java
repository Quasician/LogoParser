package slogo.View;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.model.DisplayOption;
import slogo.model.Turtle;

import java.util.concurrent.atomic.AtomicInteger;

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
    private Label color= new Label();
    private Label size= new Label();
    private Label penColor= new Label();
    private Label imageIndex= new Label();
    private HBox rowForSize;
    private HBox CoordForSize;
    public Configuration(String name, String value){
        this.firstName = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(value);
    }

    public Configuration(ObservableList<Turtle> turtles, DisplayOption displayOption) {
        variablesTable = new ListView<>();
        PropertyName=new Label("Number of Turtles ");
        number= new Label();
        color= new Label();
        size= new Label();
        penColor= new Label();
        imageIndex= new Label();
        addRowListener(turtles);
        sendDisplay(displayOption);
        initialDisplay(displayOption,turtles);
    }

    public void initialDisplay(DisplayOption displayOption,ObservableList<Turtle> turtles){
        color.setText(String.valueOf("Pen Thickness is: "+displayOption.getPenWidthProperty().getValue()));
        size.setText(String.valueOf("Background Color Index is: "+displayOption.getBgIndex()));
        penColor.setText("Pen Color Index is: "+ String.valueOf(displayOption.getPenIndex()));
        imageIndex.setText("Image Index is: "+ String.valueOf(displayOption.getImageIndex()));
        int count=0;
        for(Turtle t: turtles){
            count++;
        }
        if(variablesTable.getItems().contains(number)){
            variablesTable.getItems().remove(number);
        }
        number.setText("Number of Turtles: "+ String.valueOf(count));
        variablesTable.getItems().addAll(number,color,size,penColor,imageIndex);


    }

    public void addRowListener(ObservableList<Turtle> turtles) {
        turtles.addListener((ListChangeListener<? super Turtle>) e-> {  int count=0;
            for(Turtle t: turtles){
                    count++;
            }
            number.setText("Number of Turtles: "+ String.valueOf(count));
            });
    }

    protected Node getScene(){
        return variablesTable;
    }

    public void sendDisplay(DisplayOption displayOption) {
        displayOption.getPenWidthProperty().addListener(e->{
            System.out.println("GOT TO HERE SEND DISPLAY");
            color.setText(String.valueOf("Pen Thickness is: "+displayOption.getPenWidthProperty().getValue()));
        });

        displayOption.getBgIndex().addListener(e->{
            size.setText(String.valueOf("Background Color Index is: "+displayOption.getBgIndex()));

        });

        displayOption.getPenIndex().addListener(e->{
            penColor.setText("Pen Color Index is: "+ String.valueOf(displayOption.getPenIndex()));
        });

        displayOption.getImageIndex().addListener(e->{
            imageIndex.setText("Image Index is: "+ String.valueOf(displayOption.getImageIndex()));
        });
    }


    public void sendPenColor(Paint penColorIndex) {
        penColor.setText("Pen Color Index is: "+ String.valueOf(penColorIndex));
    }

    public void setTurtleIndex(Object value) {
       imageIndex.setText("Image of Turtles is: "+ String.valueOf(value));
    }

    public void changeBackground(Color color) {
        size.setText(String.valueOf("Background Color Index is: "+ color));
    }
}













