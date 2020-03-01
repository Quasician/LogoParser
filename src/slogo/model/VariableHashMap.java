package slogo.model;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import java.util.HashMap;
import java.util.Map;


public class VariableHashMap {
  private static Map<String, String> varHashMap = new HashMap<String, String>();
  private static ObservableMap<String, String> observableMap;

  public static void createMap(ObservableMap newMap){
    observableMap = newMap;
  }

  public static void seeChange(){
    observableMap.addListener(new MapChangeListener() {
      @Override
      public void onChanged(MapChangeListener.Change change) {
        System.out.println("hello"+change.getValueAdded().toString());
      }
    });

  }

  public static void addToMap(String name, String expression) {
    seeChange();
    if (observableMap.containsKey(name)) {
      observableMap.put(name, expression);
    } else {
      observableMap.putIfAbsent(name, expression);
    }
  }

  public static String getVarValue(String name) {
    return observableMap.get(name);
  }

  public static Iterable<Map.Entry<String, String>> getAllVariables() {
    return observableMap.entrySet();
  }

}