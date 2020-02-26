package slogo.View;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import slogo.Main;

public class Toolbar {

  private ColorPicker backgroundColor;
  private ColorPicker penColor;
  private Button helpButton;
  private ResourceBundle myResources = Main.myResources;
  private ComboBox setTurtleImage;
  private ComboBox<String> changeLanguageBox;
  private static final List<String> LANGUAGES = Arrays
      .asList("English", "Chinese", "French", "German", "Italian", "Portuguese", "Russian",
          "Spanish", "Urdu");
  private static final List<String> TURTLES = Arrays
      .asList("TurtleImage", "BlackTurtle", "BlueTurtle", "CuteTurtle", "Duvall");
  private StringProperty currentLanguage = new SimpleStringProperty();
  private HBox toolBar;
  private static final Paint BUTTON_FONT_COLOR = Color.BLACK;
  private static final int BUTTON_FONT_SIZE = 13;
  private static final String helpURI= "https://www2.cs.duke.edu/courses/compsci308/spring20/assign/03_parser/commands.php";
  private static final String CHANGE_BACKGROUND = "ChangeBackgroundColor";
  private static final String CHANGE_PEN = "ChangePenColor";
  private static final String BUTTON_HELP = "Help";
  private static final String CHANGE_TURTLE = "ChangeTurtleImage";
  private static final String STYLE_COLOR = "lightgray";
  private static final int PADDING = 10;
  private static final int BUTTON_WIDTH = 200;
  private static final int BUTTON_HEIGHT = 25;
  private static final int COLOR_PICKER_WIDTH = 50;
  private HBox colorChooser, colorChooser2;
  private Button backgroundColorPicker, penColorPicker;
  private TurtleGrid turtleGrid;
  private Desktop forHelp;
  private Language language;

  public Toolbar(TurtleGrid grid, Language language) {
    changeLanguageBox = new ComboBox<>();
    setTurtleImage = new ComboBox<>();
    forHelp= Desktop.getDesktop();
    turtleGrid = grid;
    colorChooser = new HBox();
    setUpBackgroundColorChooser(grid);
    colorChooser.getChildren().addAll(backgroundColorPicker, backgroundColor);
    colorChooser2 = new HBox();
    setUpPenColorChooser(grid);
    colorChooser2.getChildren().addAll(penColorPicker, penColor);
    helpButton = new ViewButton(Main.myResources.getString(BUTTON_HELP), BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_FONT_SIZE);
//    setTurtleImage = new ViewButton(Main.myResources.getString(CHANGE_TURTLE), BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_FONT_SIZE);
    setUpChangeLanguageChooser();
    setUpTurtleChooser();
    setUpHelpButton();
    this.language = language;
    currentLanguage.set("English");
  }

  private void setUpTurtleChooser() {
    setTurtleImage.setPrefWidth(BUTTON_WIDTH);
    for (String turtle : TURTLES) {
      setTurtleImage.getItems().add(turtle);
    }
    setTurtleImage.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
      @Override
      public ListCell<String> call(ListView<String> p) {
        final ListCell<String> cell =  new ListCell<String>() {
          @Override
          protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(item);
            if (item == null || empty) {
              setGraphic(null);
            } else {
              Image icon = new Image(myResources.getString(item));
              ImageView iconImageView = new ImageView(icon);
              iconImageView.setFitHeight(30);
              iconImageView.setPreserveRatio(true);
              setGraphic(iconImageView);
            }
          }
        };
        return cell;
      }
    });
    setTurtleImage.getSelectionModel().selectFirst();
   // setTurtleImage.setOnAction(e -> language.setLanguage(changeLanguageBox.getValue()));

    //when clicked set turtle image to what ever was clicked
   // setTurtleImage.setOnAction(e -> System.out.println(setTurtleImage.getValue()));

    setTurtleImage.setOnAction(e -> turtleGrid.updateTurtleImage((String) setTurtleImage.getValue()));

  }

  private void setUpHelpButton() {
    helpButton.setOnAction(e->{
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

  }



  public HBox getToolBar() {
    toolBar = new HBox(PADDING);
    toolBar.getChildren().addAll(colorChooser, colorChooser2, setTurtleImage, changeLanguageBox,
        helpButton);
    return toolBar;
  }

  private void setUpChangeLanguageChooser() {
    changeLanguageBox.setPrefWidth(BUTTON_WIDTH);
    for (String lang : LANGUAGES) {
      changeLanguageBox.getItems().add(lang);
    }
    changeLanguageBox.getSelectionModel().selectFirst();
    changeLanguageBox.setOnAction(e -> language.setLanguage(changeLanguageBox.getValue()));
  }

  private void setUpPenColorChooser(TurtleGrid grid) {
    penColorPicker = fakeButton(Main.myResources.getString(CHANGE_PEN), STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    penColor = makeColorPicker();
    penColor.setOnAction(e -> grid.setPenColor(penColor.getValue()));
  }

  private void setUpBackgroundColorChooser(TurtleGrid grid) {
    backgroundColorPicker = fakeButton(Main.myResources.getString(CHANGE_BACKGROUND), STYLE_COLOR, BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    backgroundColor = makeColorPicker();
    backgroundColor.setOnAction(e -> grid.setBackground(backgroundColor.getValue()));
  }

//   public HBox getToolBar() {
//     toolBar = new HBox(10);
//     toolBar.getChildren()
//         .addAll(colorChooser, colorChooser2, setTurtleImage, changeLanguageBox, help);
//     return toolBar;
//   }

  private ColorPicker makeColorPicker(){
    ColorPicker cp = new ColorPicker();
    cp.setPrefWidth(COLOR_PICKER_WIDTH);
    return cp;
  }

  private Button fakeButton(String text, String styleColor, Paint fontColor, int fontSize){
    Button button = new Button(text);
    button.setTextFill(fontColor);
    button.setFont(Font.font("Calibri"));
    button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
    button.setPrefWidth(200);
    return button;

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
