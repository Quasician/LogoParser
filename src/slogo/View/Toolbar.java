package slogo.View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.model.Turtle;

import static slogo.View.CommandLine.myResources;

public class Toolbar {
    private ColorPicker backgroundColor;
    private ColorPicker penColor;
    private Button help;
    private Button setTurtleImage;
    private Button changeLanguage;
    private HBox toolBar;
    private Drawing changeProperties;
    private static final Paint BUTTON_FONT_COLOR = Color.BLACK;
    private static final int BUTTON_FONT_SIZE = 13;
    private static final String BUTTON_BACKGROUND = "Change the Background Color";
    private static final String BUTTON_PEN = "Change the Pen Color";
    private static final String BUTTON_HELP ="Help";
    private static final String BUTTON_TURTLE = "Change the Turtle Image";
    private static final String BUTTON_LANG ="Change Language";
    private static final String STYLE_COLOR ="lightgray";
    private static final int PADDING = 20;
    private HBox colorChooser;
    private Button colorPicker1;
    private HBox colorChooser2;
    private Button colorPicker2;
    public Toolbar(Drawing drawer, TurtleGrid grid){
        colorChooser= new HBox();
        colorPicker1= CustomButton.CustomButton("Background Color", STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        backgroundColor= CustomButton.pickColor(BUTTON_BACKGROUND);
        backgroundColor.setOnAction(e->grid.setBackground(backgroundColor.getValue()));
        colorChooser.getChildren().addAll(colorPicker1,backgroundColor);
        colorChooser2= new HBox();
        colorPicker2= CustomButton.CustomButton("Pen Color", STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        penColor= CustomButton.pickColor(BUTTON_PEN);
        penColor.setOnAction(e->changeProperties.changePen(penColor.getValue()));
        colorChooser2.getChildren().addAll(colorPicker2,penColor);
        changeProperties=new Drawing();
        help= CustomButton.CustomButton(BUTTON_HELP, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        setTurtleImage= CustomButton.CustomButton(BUTTON_TURTLE, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        changeLanguage= CustomButton.CustomButton(BUTTON_LANG, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    }

    public HBox ToolBar(){
        toolBar=new HBox(10);
        toolBar.getChildren().addAll(colorChooser,colorChooser2,setTurtleImage,changeLanguage,help);
        return toolBar;
    }




}
