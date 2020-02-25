package slogo.View;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.Main;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class Toolbar {
    private ColorPicker backgroundColor;
    private ColorPicker penColor;
    private Button help;
    private ResourceBundle myResources = Main.myResources;
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
    private static final String helpURI= "https://www2.cs.duke.edu/courses/compsci308/spring20/assign/03_parser/commands.php";
    private HBox colorChooser;
    private Button colorPicker1;
    private HBox colorChooser2;
    private Desktop forHelp;
    private Button colorPicker2;
    public Toolbar(Drawing drawer, TurtleGrid grid){
        colorChooser= new HBox();
        forHelp= Desktop.getDesktop();
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
        help.setOnAction(e->{
            try {
                URL url = new URL(helpURI);
                URLConnection connection = url.openConnection();
                connection.connect();
                try{
                    forHelp.browse(new URI(helpURI));
                    } catch (Exception v){
                    showMessage(Alert.AlertType.ERROR, v.getMessage());
                    }
            }
            catch (Exception ex) {
                showMessage(Alert.AlertType.ERROR, ex.getMessage());
            }
        });
        setTurtleImage= CustomButton.CustomButton(BUTTON_TURTLE, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
        changeLanguage= CustomButton.CustomButton(BUTTON_LANG, STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    }

    public HBox ToolBar(){
        toolBar=new HBox(10);
        toolBar.getChildren().addAll(colorChooser,colorChooser2,setTurtleImage,changeLanguage,help);
        return toolBar;
    }
    // display given message to user using the given type of Alert dialog box
    private void showMessage (Alert.AlertType type, String message) {
        Alert alert=  new Alert(type);
        javafx.scene.image.Image img = new javafx.scene.image.Image(myResources.getString("Dinosaur"));
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(500);
        imageView.setFitHeight(400);
        alert.setGraphic(imageView);
        alert.showAndWait();
    }




}
