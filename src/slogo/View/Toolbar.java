package slogo.View;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    private HBox colorChooser, colorChooser2;
    private Button backgroundColorPicker, penColorPicker;
    public Toolbar(Drawing drawer, TurtleGrid grid){
        colorChooser= new HBox();
        backgroundColorPicker = CustomButton.CustomButton("Background Color", STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        backgroundColor= CustomButton.pickColor(BUTTON_BACKGROUND);
        backgroundColor.setOnAction(e->grid.setBackground(backgroundColor.getValue()));
        colorChooser.getChildren().addAll(backgroundColorPicker,backgroundColor);
        colorChooser2= new HBox();
        penColorPicker = CustomButton.CustomButton("Pen Color", STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        penColor= CustomButton.pickColor(BUTTON_PEN);
        penColor.setOnAction(e->changeProperties.changePen(penColor.getValue()));
        colorChooser2.getChildren().addAll(penColorPicker,penColor);
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
