package slogo.model;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ColorOptions {
  private static Map<String, String> varHashMap = new HashMap<String, String>();
  private static ObservableList<String> colorOptions;
  private static int currentChoicePen, currentChoiceBackground;

  public static void createList(ObservableList list) {
    currentChoicePen = 0;
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
    currentChoicePen = index;
  }

  public static int getCurrentChoicePen() {
    return currentChoicePen;
  }

  public static void setCurrentBackground(int index) {
    currentChoiceBackground = index;
  }

  public static int getCurrentBackground() {
    return currentChoiceBackground;
  }

  public static void addToList(String c) {
    String[] colorarray = c.split(", ");
    String color = colorarray[0];
    System.out.println(" the color is " + color);

    if (!colorOptions.contains(color)) {
      colorOptions.add(color + ", " + colorOptions.size());
    }
  }
}
