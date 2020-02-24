package slogo.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
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

    private TurtleGrid grid;

    public Toolbar(Drawing drawer, TurtleGrid grid){
        this.grid = grid;
        changeProperties=new Drawing();
        help= CustomButton.CustomButton(BUTTON_HELP, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        setTurtleImage= CustomButton.CustomButton(BUTTON_TURTLE, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        changeLanguage= CustomButton.CustomButton(BUTTON_LANG, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    }

    public HBox ToolBar(){
        VBox background = backgroundPickerBox();
        VBox pen = penColorPickerBox();
        toolBar=new HBox();
        toolBar.getChildren().addAll(background,pen,setTurtleImage,changeLanguage,help);
        toolBar.setPadding(new Insets(PADDING));
        toolBar.setAlignment(Pos.BOTTOM_CENTER);
        return toolBar;
    }

    private VBox backgroundPickerBox(){
        backgroundColor= CustomButton.pickColor(BUTTON_BACKGROUND);
        backgroundColor.setOnAction(e->grid.setBackground(backgroundColor.getValue()));
        Label label = new Label(BUTTON_BACKGROUND);
        VBox backgroundBox = new VBox();
        backgroundBox.getChildren().addAll(label, backgroundColor);
        return backgroundBox;
    }
    private VBox penColorPickerBox(){
        penColor= CustomButton.pickColor(BUTTON_PEN);
        penColor.setOnAction(e->changeProperties.changePen(penColor.getValue()));
        Label label = new Label(BUTTON_PEN);
        VBox colorBox = new VBox();
        colorBox.getChildren().addAll(label, penColor);
        return colorBox;
    }



}
