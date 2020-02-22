package slogo.View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Toolbar {
    private ColorPicker backgroundColor;
    private ColorPicker penColor;
    private Button help;
    private Button setTurtleImage;
    private Button changeLanguage;
    private Button setBackground;
    private Button setPen;
    private HBox toolBar;
    private VBox dropDown;
    private final ComboBox colorComboBox;
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

    public Toolbar(){
        backgroundColor= CustomButton.pickColor(BUTTON_BACKGROUND);
        backgroundColor.setOnAction(e->changeProperties.changeBackground(backgroundColor.getValue()));
        penColor= CustomButton.pickColor(BUTTON_PEN);
        penColor.setOnAction(e->changeProperties.changePen(penColor.getValue()));
        setBackground= CustomButton.CustomButton(BUTTON_BACKGROUND, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        setPen= CustomButton.CustomButton(BUTTON_PEN, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        changeProperties=new Drawing();
         colorComboBox = new ComboBox();
        colorComboBox.getItems().addAll(
                setBackground,setPen
        );

        help= CustomButton.CustomButton(BUTTON_HELP, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        setTurtleImage= CustomButton.CustomButton(BUTTON_TURTLE, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        changeLanguage= CustomButton.CustomButton(BUTTON_LANG, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    }

    public HBox ToolBar(){
        toolBar=new HBox();
        toolBar.getChildren().addAll(help,setTurtleImage,changeLanguage,setPen,setBackground,colorComboBox);
        toolBar.setPadding(new Insets(PADDING));
        return toolBar;
    }




}
