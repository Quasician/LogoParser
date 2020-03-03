package slogo.model;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class UIOption {

  private static final String COMMA = ", ";
  private static final String SPACE = " ";
  private static Map<String, String> varHashMap = new HashMap<String, String>();
  private static ObservableList<String> colorOptions;
  private static IntegerProperty penIndex = new SimpleIntegerProperty();
  private static IntegerProperty bgIndex = new SimpleIntegerProperty();
  private static DoubleProperty penWidth = new SimpleDoubleProperty();
  private static IntegerProperty imageIndex = new SimpleIntegerProperty();

  public static IntegerProperty getImageIndex() {
    return imageIndex;
  }

  public static IntegerProperty getPenIndex() {
    return penIndex;
  }

  public static IntegerProperty getBgIndex() {
    return bgIndex;
  }

  public static DoubleProperty getPenWidthProperty() {
    return penWidth;
  }


  public static double getPenWidth() {
    return penWidth.get();
  }

  public static void createList(ObservableList list) {
    colorOptions = list;
    seeChange();
  }

  public static void seeChange() {
    colorOptions.addListener(new ListChangeListener<String>() {
      @Override
      public void onChanged(Change<? extends String> c) {
        System.out.println("the color list has changed");
        for (String s : colorOptions) {
          System.out.println(s);
        }
      }
    });
  }

  public static String getColorAt(int index) {
    return colorOptions.get(index);
  }

  public static int getLargestIndex() {
    return colorOptions.size() - 1;
  }

  public static void setCurrentChoicePen(int index) {
    penIndex.set(index);
  }

  public static int getCurrentChoicePen() {
    return penIndex.get();
  }

  public static void setImageIndex(int index) {
    imageIndex.set(index);
  }

  public static void setCurrentBackground(int index) {
    bgIndex.set(index);
  }

  public static void setPenWidth(double width) {
    penWidth.set(width);
  }

  public static int getCurrentBackground() {
    return bgIndex.get();
  }

  public static void addToList(String c) {
    String[] colorarray = c.split(COMMA);
    String color = colorarray[0];

    if (!colorOptions.contains(color)) {
      colorOptions.add(color + COMMA + colorOptions.size());
    }
  }

  public static void setColorAt(int index, int[] rgb) {
    String[] rgbStrings = new String[rgb.length];
    for (int i = 0; i < rgbStrings.length; i++) {
      rgbStrings[i] = "" + rgb[i];
    }
    String rgbToString = String.join(SPACE, rgbStrings);

    if (index < colorOptions.size()) {
      colorOptions.set(index, rgbToString + COMMA + index);
    } else {
      addToList(rgbToString);
    }
  }
}