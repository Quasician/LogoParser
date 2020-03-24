package slogo.model;

import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Sanna
 *
 * This class was created as a bridge between the front end and back end,
 * particularly for the purpose of display commands. Display properties, including the pen
 * color, background color, and turtle image, can all be changed by both buttons and commands,
 * so they have to be linked to the front end and back end.
 *
 * The properties that are instance variables here are bound bidirectionally to properties in the Toolbar
 * so that they can be modified both by this class and by buttons in the Toolbar.
 *
 */
public class DisplayOption {
  private static final String COMMA = ", ";
  private static final String SPACE = " ";
  private List<String> colorOptions;
  private IntegerProperty penIndex = new SimpleIntegerProperty();
  private IntegerProperty bgIndex = new SimpleIntegerProperty();
  private DoubleProperty penWidth = new SimpleDoubleProperty();
  private IntegerProperty imageIndex = new SimpleIntegerProperty();
  private IntegerProperty changedIndex = new SimpleIntegerProperty(-1);
  private StringProperty newColor = new SimpleStringProperty();
  private int numImages;

  /**
   * Default constructor does nothing
   */
  public DisplayOption() {
  }

  /**
   * @return new color as a string
   */
  public StringProperty getNewColor() {
    return newColor;
  }

  /**
   * @return the index that was changed
   */
  public IntegerProperty getChangedIndex() {
    return changedIndex;
  }

  /**
   * @return the index of the selected image
   */
  public IntegerProperty getImageIndex() {
    return imageIndex;
  }

  /**
   * @return the index of the selected pen color
   */
  public IntegerProperty getPenIndex() {
    return penIndex;
  }

  /**
   * @return the index of the selected background color
   */
  public IntegerProperty getBgIndex() {
    return bgIndex;
  }

  /**
   * @return the current pen width
   */
  public DoubleProperty getPenWidthProperty() {
    return penWidth;
  }

  /**
   * Used by toolbar, initializes list of possible color options
   * @param list
   */
  public void createList(List list) {
    colorOptions = list;
  }

  /**
   * Get the largest index of the color options list, for checking if the user command
   * is in bounds
   * @return the largest index
   */
  public int getLargestIndex() {
    return colorOptions.size() - 1;
  }

  /**
   * Set the current pen color based on the input index
   * @param index of the pen color
   */
  public void setCurrentChoicePen(int index) {
    penIndex.set(index);
  }

  /**
   * Set the current turtle image based on the input index
   * @param index
   */
  public void setImageIndex(int index) {
    imageIndex.set(index);
  }

  /**
   * Set the current background color based on the input index
   * @param index
   */
  public void setCurrentBackground(int index) {
    bgIndex.set(index);
  }

  /**
   * Set the current pen width based on the input width
   * @param width
   */
  public void setPenWidth(double width) {
    penWidth.set(width);
  }

  /**
   * Set the number of images available for turtles
   * @param number
   */
  public void setNumImages(int number) {
    numImages = number;
  }

  /**
   * Get the number of images available for turtles, used in error checking
   */
  public int getNumImages() {
    return numImages;
  }

  /**
   * Add a new color to the list of color options. This is used in the SetPalette command, which
   * allows users to add their own color to the options for background and pen color.
   * @param c
   */
  public void addToList(String c) {
    String[] colorarray = c.split(COMMA);
    String color = colorarray[0];
    colorOptions.add(color + COMMA + colorOptions.size());
  }

  /**
   * Used in SetPalette command to change the color at the input index
   * @param index
   * @param rgb int array of red, green, blue colors
   */
  public void setColorAt(int index, int[] rgb) {
    String[] rgbStrings = new String[rgb.length];
    for (int i = 0; i < rgbStrings.length; i++) {
      rgbStrings[i] = "" + rgb[i];
    }
    String rgbToString = String.join(SPACE, rgbStrings);
    newColor.set(rgbToString + COMMA + index);
    changedIndex.set(index);
    if (index < colorOptions.size()) {
      colorOptions.set(index, newColor.get());
    } else {
      addToList(rgbToString);
    }
  }
}