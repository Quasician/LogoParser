package slogo.View;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import slogo.Main;
import slogo.model.DisplayOption;
import slogo.model.Turtle;

public class Toolbar {
  private ColorPicker backgroundColor, penColor;
  private ResourceBundle myResources = Main.myResources;
  private ComboBox setTurtleImage;
  private ComboBox<String> changeLanguageBox, changePenColor, changeBackgroundColor;

  private static final String CHANGE_BACKGROUND = "ChangeBackgroundColor";
  private static final String CHANGE_PEN = "ChangePenColor";
  private static final String BUTTON_HELP = "Help";

  private HBox toolBar, backgroundColorChooser, penColorChoosers;
  private Button backgroundColorPicker, penColorPicker, makeNew, helpButton;
  private TurtleGrid turtleGrid;
  private Desktop forHelp;
  private Language language;
  private ObservableList<Turtle> activatedTurtles;
  private Configuration myConfig;
  private ObservableList<String> colorOptions;

  private static final String THIS_PACKAGE = Toolbar.class.getPackageName();
  private static final String MY_RESOURCE_FOLDER = THIS_PACKAGE + ".visualProperty.";
  private static ResourceBundle turtleImages = ResourceBundle.getBundle(MY_RESOURCE_FOLDER + "CurrentTurtleImages");
  private static final Set<String> TURTLES = turtleImages.keySet();
  private static ResourceBundle myColors = ResourceBundle
      .getBundle(MY_RESOURCE_FOLDER + "Colors");
  private static ResourceBundle possibleLanguages = ResourceBundle.getBundle(MY_RESOURCE_FOLDER + "Languages");
  private static final Set<String> LANGUAGES = possibleLanguages.keySet();
  private static ResourceBundle setupProperties = ResourceBundle.getBundle(MY_RESOURCE_FOLDER + "Toolbar");
  private static final int PADDING = Integer.parseInt(setupProperties.getString("Padding"));
  private static final int BUTTON_WIDTH = Integer.parseInt(setupProperties.getString("ButtonWidth"));
  private static final int BUTTON_HEIGHT = Integer.parseInt(setupProperties.getString("ButtonHeight"));
  private static final int COLOR_PICKER_WIDTH = Integer.parseInt(setupProperties.getString("ColorPickerWidth"));;
  private static final String HELP_URI = setupProperties.getString("HelpURL");
  private static final Paint BUTTON_FONT_COLOR = Color.web(setupProperties.getString("ButtonFontColor"));
  private static final int BUTTON_FONT_SIZE = Integer.parseInt(setupProperties.getString("ButtonFontSize"));
  private static final String STYLE_COLOR = setupProperties.getString("StyleColor");
  private static final String BUTTON_FONT = setupProperties.getString("ButtonFont");
  private static final int ALERT_WIDTH = Integer.parseInt(setupProperties.getString("AlertWidth"));
  private static final int ALERT_HEIGHT = Integer.parseInt(setupProperties.getString("AlertHeight"));

  private IntegerProperty penColorIndex = new SimpleIntegerProperty();
  private IntegerProperty bgColorIndex = new SimpleIntegerProperty();
  private DoubleProperty penWidth = new SimpleDoubleProperty();
  private IntegerProperty imageIndex = new SimpleIntegerProperty();
  private IntegerProperty changedIndex = new SimpleIntegerProperty(-1);
  private StringProperty newColor = new SimpleStringProperty();

  public Toolbar(TurtleGrid grid, Language language, ObservableList<Turtle> activatedTurtles) {
    this.activatedTurtles = activatedTurtles;
    initializeColors();
    makeComboBoxes();
    turtleGrid = grid;
    myConfig= grid.getConfig();
    setupButtons();
    setupListeners();
    this.language = language;
  }

  private void setupButtons() {
    makeNew = new ViewButton(Main.myResources.getString("New"), BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_FONT_SIZE);
    uploadSim();
    setTurtleImage = new ComboBox<>();
    forHelp = Desktop.getDesktop();

    setUpColorChoosers();
    helpButton = new ViewButton(Main.myResources.getString("Help"), BUTTON_HEIGHT,
        BUTTON_WIDTH, BUTTON_FONT_SIZE);
    setUpChangeLanguageChooser();
    setUpTurtleChooser();
    setUpHelpButton();
    setUpPenColorDropdown(turtleGrid);
    setUpBackgroundColorDropdown(turtleGrid);
  }

  private void setupListeners() {
    addBgIndexListener();
    addPenIndexListener();
    addPenWidthListener();
    addImageListener();
    addChangedIndexListener();
  }

  public void bindWithDisplayOption(DisplayOption displayOption) {
    penWidth.bindBidirectional(displayOption.getPenWidthProperty());
    penColorIndex.bindBidirectional(displayOption.getPenIndex());
    bgColorIndex.bindBidirectional(displayOption.getBgIndex());
    imageIndex.bindBidirectional(displayOption.getImageIndex());
    newColor.bindBidirectional(displayOption.getNewColor());
    changedIndex.bindBidirectional(displayOption.getChangedIndex());
    List<String> colorCopy = new ArrayList<>(colorOptions);
    displayOption.createList(colorCopy);
  }

  private void setUpColorChoosers() {
    backgroundColorChooser = new HBox();
    setUpBackgroundColorChooser(turtleGrid);
    backgroundColorChooser.getChildren().addAll(backgroundColorPicker, backgroundColor);
    penColorChoosers = new HBox();
    setUpPenColorChooser(turtleGrid);
    penColorChoosers.getChildren().addAll(penColorPicker, penColor);
  }

  private void makeComboBoxes() {
    changeLanguageBox = new ComboBox<>();
    changePenColor = new ComboBox<>();
    changeBackgroundColor = new ComboBox<>();
  }

  private void initializeColors() {
    colorOptions = FXCollections.observableArrayList();
    int index = 0;
    for (String color : myColors.keySet()) {
      String rgb = myColors.getString(color);
      colorOptions.add(rgb + ", " + index);
      index++;
    }
  }

  private Color getColorRGB(String[] rgb) {
    int r = Integer.parseInt(rgb[0]);
    int g = Integer.parseInt(rgb[1]);
    int b = Integer.parseInt(rgb[2]);
    return Color.rgb(r, g, b);
  }

  private void addPenIndexListener() {
    penColorIndex.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        turtleGrid.setPenColor(getNewColor(penColorIndex));
      }
    });
  }

  private Color getNewColor(IntegerProperty property) {
    int index = property.get();
    String[] color = colorOptions.get(index).split(", ");
    String[] rgb = color[0].split(" ");
    return getColorRGB(rgb);
  }

  private void addBgIndexListener() {
    bgColorIndex.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        turtleGrid.setBackground(getNewColor(bgColorIndex));
      }
    });
  }

  private void addChangedIndexListener() {
    changedIndex.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        int index = changedIndex.get();
        if (index >= colorOptions.size()) {
          colorOptions.add(newColor.get());
        } else {
          colorOptions.set(index, newColor.get());
        }
      }
    });
  }

  private void addPenWidthListener() {
    penWidth.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        turtleGrid.setPenWidth(penWidth.get());
      }
    });
  }

  private void addImageListener() {
    imageIndex.addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue o, Object oldVal, Object newVal) {
        int index = imageIndex.get();
        String image = (String)setTurtleImage.getItems().get(index);
        String imageName = image.split(", ")[0];
        turtleGrid.updateTurtlesImage(imageName, activatedTurtles);
      }
    });
  }

  private void setUpPenColorDropdown(TurtleGrid grid) {
    changePenColor.itemsProperty().bind(new SimpleObjectProperty<>(colorOptions));
    changePenColor.setPrefWidth(BUTTON_WIDTH);
    setupCellBackgrounds(changePenColor);
    changePenColor.getSelectionModel().selectFirst();
    changePenColor.setOnAction(e -> {
     changePenColor();
    });
  }

  private void changePenColor() {
    String[] color = changePenColor.getValue().split(", ");
    Color c = getColorRGB(color[0].split(" "));
    turtleGrid.setPenColor(c);
    changePenColor
        .setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  private void setupCellBackgrounds(ComboBox<String> box) {
    box.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
      @Override
      public ListCell<String> call(ListView<String> p) {
        final ListCell<String> cell = new ListCell<String>() {
          @Override
          protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(item);
            if (item == null || empty) {
              // do nothing
            } else {
              String[] color = item.split(", ");
              String[] rgb = color[0].split(" ");
              Color c = getColorRGB(rgb);
              Background background = new Background(
                  new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY));
              setBackground(background);
            }
          }
        };
        return cell;
      }
    });
  }

  private void setUpBackgroundColorDropdown(TurtleGrid grid) {
    changeBackgroundColor.itemsProperty().bind(new SimpleObjectProperty<>(colorOptions));
    changeBackgroundColor.setPrefWidth(BUTTON_WIDTH);
    setupCellBackgrounds(changeBackgroundColor);
    changeBackgroundColor.getSelectionModel().selectFirst();
    changeBackgroundColor.setOnAction(e -> {
      changeBackground();
    });
  }

  private void changeBackground() {
    String[] color = changeBackgroundColor.getValue().split(", ");
    Color c = getColorRGB(color[0].split(" "));
    turtleGrid.setBackground(c);
    changeBackgroundColor
        .setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
    myConfig.changeBackground(c);
  }

  private void uploadSim() {
    makeNew.setOnAction(e -> {
      makeNewWorkspace();
    });
  }

  private void makeNewWorkspace(){
    Stage newScreen = new Stage();
    Main newSimulation = new Main();
    try {
      newSimulation.start(newScreen);
    } catch (Exception ex) {
      showMessage(Alert.AlertType.ERROR, ex.getMessage());
    }
  }

  private void setUpTurtleChooser() {
    setTurtleImage.setPrefWidth(BUTTON_WIDTH);

    int index = 0;
    for (String turtle : TURTLES) {
      setTurtleImage.getItems().add(turtle + ", " + index);
      index++;
    }
    setTurtleImage.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
      @Override
      public ListCell<String> call(ListView<String> p) {
        final ListCell<String> cell = new ListCell<String>() {
          @Override
          protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(item);
            if (item == null || empty) {
              setGraphic(null);
            } else {
              String turtleName = item.split(", ")[0];
              Image icon = new Image(myResources.getString(turtleName));
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
    setTurtleImage.setOnAction(e -> {
      setImage();
    });
  }

  private void setImage() {
    turtleGrid
        .updateTurtlesImage((String) setTurtleImage.getValue(), activatedTurtles);
    myConfig.setTurtleIndex(setTurtleImage.getValue());
  }

  private void setUpHelpButton() {
    helpButton.setOnAction(e -> {
      help();
    });
  }

  private void help(){
    try {
      URL url = new URL(HELP_URI);
      URLConnection connection = url.openConnection();
      connection.connect();
      try {
        forHelp.browse(new URI(HELP_URI));
      } catch (Exception v) {
        showMessage(Alert.AlertType.ERROR, v.getMessage());
      }
    } catch (Exception ex) {
      showMessage(Alert.AlertType.ERROR, ex.getMessage());
    }
  }

  public HBox getToolBar() {
    toolBar = new HBox(PADDING);
    toolBar.setPrefHeight(BUTTON_HEIGHT + PADDING);
    toolBar.setBackground(
        new Background(new BackgroundFill(Color.web(STYLE_COLOR), CornerRadii.EMPTY, Insets.EMPTY)));
    toolBar.getChildren()
        .addAll(makeNew, backgroundColorChooser, penColorChoosers, setTurtleImage, changeLanguageBox,
            changePenColor, changeBackgroundColor,
            helpButton);
    return toolBar;
  }

  private void setUpChangeLanguageChooser() {
    changeLanguageBox.setPrefWidth(BUTTON_WIDTH);
    for (String lang : LANGUAGES) {
      changeLanguageBox.getItems().add(lang);
    }
    changeLanguageBox.getSelectionModel().selectFirst();
    changeLanguageBox.setOnAction(e -> setLanguage());
  }

  private void setLanguage() {
    language.setLanguage(changeLanguageBox.getValue());
  }

  private void setUpPenColorChooser(TurtleGrid grid) {
    penColorPicker = fakeButton(Main.myResources.getString(CHANGE_PEN), STYLE_COLOR,
        BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    penColor = makeColorPicker();
    penColor.setOnAction(e -> setPenColor());
  }

  private void setPenColor() {
    turtleGrid.setPenColor(penColor.getValue());
  }

  private void setUpBackgroundColorChooser(TurtleGrid grid) {
    backgroundColorPicker = fakeButton(Main.myResources.getString(CHANGE_BACKGROUND), STYLE_COLOR,
        BUTTON_FONT_COLOR, BUTTON_FONT_SIZE);
    backgroundColor = makeColorPicker();
    backgroundColor.setOnAction(e -> grid.setBackground(backgroundColor.getValue()));
  }

  private ColorPicker makeColorPicker() {
    ColorPicker cp = new ColorPicker();
    cp.setPrefWidth(COLOR_PICKER_WIDTH);
    return cp;
  }

  private Button fakeButton(String text, String styleColor, Paint fontColor, int fontSize) {
    Button button = new Button(text);
    button.setTextFill(fontColor);
    button.setFont(Font.font(BUTTON_FONT));
    button.setStyle("-fx-background-color:" + styleColor + ";-fx-font-size:" + fontSize + " px;");
    button.setPrefWidth(BUTTON_WIDTH);
    return button;
  }

  private void showMessage(Alert.AlertType type, String message) {
    Alert alert = new Alert(type);
    javafx.scene.image.Image img = new javafx.scene.image.Image(myResources.getString("Dinosaur"));
    ImageView imageView = new ImageView(img);
    imageView.setFitWidth(ALERT_WIDTH);
    imageView.setFitHeight(ALERT_HEIGHT);
    alert.setGraphic(imageView);
    alert.showAndWait();
  }
}
