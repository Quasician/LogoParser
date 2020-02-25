package slogo.View;

import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.Main;

public class Toolbar {
  private ColorPicker backgroundColor;
  private ColorPicker penColor;
  private Button helpButton;
  private Button setTurtleImage;
  private ComboBox<String> changeLanguageBox;
  private static final List<String> LANGUAGES = Arrays.asList("English", "Chinese", "French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu");
  private HBox toolBar;
  private static final Paint BUTTON_FONT_COLOR = Color.BLACK;
  private static final int BUTTON_FONT_SIZE = 13;
  private static final String CHANGE_BACKGROUND = "ChangeBackgroundColor";
  private static final String CHANGE_PEN = "ChangePenColor";
  private static final String BUTTON_HELP = "Help";
  private static final String CHANGE_TURTLE = "ChangeTurtleImage";
  private static final String STYLE_COLOR = "lightgray";
  private static final int PADDING = 10;
  private static final int BUTTON_WIDTH = 200;
  private HBox colorChooser, colorChooser2;
  private Button backgroundColorPicker, penColorPicker;

  public Toolbar(TurtleGrid grid) {
    changeLanguageBox = new ComboBox<>();
    colorChooser = new HBox();
    setUpBackgroundColorChooser(grid);
    colorChooser.getChildren().addAll(backgroundColorPicker, backgroundColor);
    colorChooser2 = new HBox();
    setUpPenColorChooser(grid);
    colorChooser2.getChildren().addAll(penColorPicker, penColor);
    helpButton = CustomButton.CustomButton(Main.myResources.getString(BUTTON_HELP), STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    setTurtleImage = CustomButton
        .CustomButton(Main.myResources.getString(CHANGE_TURTLE), STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    setUpChangeLanguageChooser();
    }

  private void setUpChangeLanguageChooser() {
    changeLanguageBox.setPrefWidth(BUTTON_WIDTH);
    for(String lang : LANGUAGES){
      changeLanguageBox.getItems().add(lang);
    }
    changeLanguageBox.getSelectionModel().selectFirst();
    changeLanguageBox.setOnAction(e-> System.out.println(changeLanguageBox.getValue()));
  }


  private void setUpPenColorChooser(TurtleGrid grid) {
    penColorPicker = CustomButton
        .CustomButton(Main.myResources.getString(CHANGE_PEN), STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    penColor = CustomButton.pickColor();
    penColor.setOnAction(e -> grid.setPenColor(penColor.getValue()));
  }

  private void setUpBackgroundColorChooser(TurtleGrid grid) {
    backgroundColorPicker = CustomButton
        .CustomButton(Main.myResources.getString(CHANGE_BACKGROUND), STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    backgroundColor = CustomButton.pickColor();
    backgroundColor.setOnAction(e -> grid.setBackground(backgroundColor.getValue()));
  }

  public HBox getToolBar() {
    toolBar = new HBox(PADDING);
    toolBar.getChildren().addAll(colorChooser, colorChooser2, setTurtleImage, changeLanguageBox,
        helpButton);
    return toolBar;
  }

}
