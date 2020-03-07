package slogo.View;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.model.DisplayOption;
import slogo.model.Turtle;
import java.util.ResourceBundle;

public class Configuration {
    private ListView variablesTable;
    private static final String THIS_PACKAGE = Configuration.class.getPackageName();
    private static final String MY_RESOURCE_FOLDER = THIS_PACKAGE + ".visualProperty.";
    private static final ResourceBundle TURTLE_IMAGES = ResourceBundle.getBundle(MY_RESOURCE_FOLDER + "PropertiesWindow");
    private static final String TURTLE_NUMBER_TEXT= TURTLE_IMAGES.getString("TurtlesNumber");
    private static final String PEN_THICKNESS= TURTLE_IMAGES.getString("PenThicknessText");
    private static final String PEN_DOWN_PROP= TURTLE_IMAGES.getString("PenDownText");
    private static final String BACKGROUND_COLOR_TEXT= TURTLE_IMAGES.getString("BackgroundColorText");
    private static final String PEN_COLOR_TEXT= TURTLE_IMAGES.getString("PenColorText");
    private static final String IMAGE_INDEX_TEXT= TURTLE_IMAGES.getString("ImageIndexText");
    private static final String TURT_COORD_TEXT= TURTLE_IMAGES.getString("TurtleCoordText");
    private static final String TURT_STATUS_TEXT= TURTLE_IMAGES.getString("TurtStatusText");
    private static final String IS_STRING =" is ";
    private static final String COMMA_STRING =",";
    private Label PropertyName;
    private Label number;
    private Label color;
    private Label size;
    private Label penDown;
    private Label penColor;
    private Label turtleStatus;
    private Label turtleCoord;
    private Label imageIndex;

    public Configuration(ObservableList<Turtle> turtles, DisplayOption displayOption) {
        variablesTable = new ListView<>();
        PropertyName=new Label();
        number= new Label();
        color= new Label();
        size= new Label();
        penColor= new Label();
        imageIndex= new Label();
        penDown= new Label();
        turtleStatus= new Label();
        turtleCoord= new Label();
        addRowListener(turtles);
        sendDisplay(displayOption);
        initialDisplay(displayOption,turtles);
    }

    public void initialDisplay(DisplayOption displayOption,ObservableList<Turtle> turtles){
        color.setText(PEN_THICKNESS +displayOption.getPenWidthProperty().getValue());
        size.setText(BACKGROUND_COLOR_TEXT+displayOption.getBgIndex());
        penColor.setText(PEN_COLOR_TEXT+ displayOption.getPenIndex());
        imageIndex.setText(IMAGE_INDEX_TEXT+ displayOption.getImageIndex());
        int count=0;
        for(Turtle t: turtles){
            count++;
        }
        if(variablesTable.getItems().contains(number)){
            variablesTable.getItems().remove(number);
        }
        number.setText(TURTLE_NUMBER_TEXT + count);
        penDown.setText(PEN_DOWN_PROP+ true);
        variablesTable.getItems().addAll(number,color,size,penColor,imageIndex,penDown, turtleStatus, turtleCoord);


    }

    public void addRowListener(ObservableList<Turtle> turtles) {
        turtles.addListener((ListChangeListener<? super Turtle>) e-> {  int count=0;
            for(Turtle t: turtles){
                    count++;
            }
            number.setText(PEN_THICKNESS+ count);
            });
    }

    protected Node getScene(){
        return variablesTable;
    }

    public void sendDisplay(DisplayOption displayOption) {
        displayOption.getPenWidthProperty().addListener(e->{
            color.setText(PEN_THICKNESS+displayOption.getPenWidthProperty().getValue());
        });

        displayOption.getBgIndex().addListener(e->{
            size.setText(BACKGROUND_COLOR_TEXT+displayOption.getBgIndex());

        });

        displayOption.getPenIndex().addListener(e->{
            penColor.setText(PEN_COLOR_TEXT+ displayOption.getPenIndex());
        });

        displayOption.getImageIndex().addListener(e->{
            imageIndex.setText(IMAGE_INDEX_TEXT+ displayOption.getImageIndex());
        });
    }


    public void sendPenColor(Paint penColorIndex) {
        penColor.setText(PEN_COLOR_TEXT+ penColorIndex);
    }

    public void setTurtleIndex(Object value) {
       imageIndex.setText(IMAGE_INDEX_TEXT + value);
    }

    public void changeBackground(Color color) {
        size.setText(BACKGROUND_COLOR_TEXT+ color);
    }

    public void changePenDown(Boolean isPenDown) {
        penDown.setText(PEN_DOWN_PROP+ isPenDown);
    }

    public void changeActive(Turtle viewTurtle) {
        turtleStatus.setText(TURT_STATUS_TEXT+viewTurtle.getId()+ IS_STRING + viewTurtle.isActivatedProperty().getValue());
    }

    public void makeCoord(Turtle viewTurtle) {
        turtleCoord.setText(TURT_COORD_TEXT+ viewTurtle.getX()+ COMMA_STRING + viewTurtle.getY());
    }
}













