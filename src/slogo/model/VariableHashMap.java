package slogo.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import java.util.HashMap;
import java.util.Map;


public class VariableHashMap {
  private static Map<String, String> varHashMap = new HashMap<String, String>();
  private static ObservableMap<String, String> observableMap;
  private static ObservableList<Entry<String, String>> observableEntryList = FXCollections.observableList(new ArrayList<>());

  public static void createMap(ObservableMap newMap){
    observableMap = newMap;
    seeChange();
  }

  public static void seeChange(){
    observableMap.addListener(new MapChangeListener() {
      @Override
      public void onChanged(MapChangeListener.Change change) {
        System.out.println("hello"+change.getValueAdded().toString());
        Map.Entry<String, String> entry = new SimpleEntry<>(change.getKey().toString(), change.getValueAdded().toString());
        observableEntryList.add(entry);
        for(Entry huh : observableEntryList){
          System.out.println(huh);
        }
      }
    });
    System.out.println("listener added");

  }

  public static void addToMap(String name, String expression) {
//    seeChange();
    if (observableMap.containsKey(name)) {
      observableMap.put(name, expression);
    } else {
      observableMap.putIfAbsent(name, expression);
    }
  }

  public static String getVarValue(String name) {
    return observableMap.get(name);
  }

//  public static List<Entry<String, String>> getAllVariables() {
//    Set<Entry<String, String>> set = observableMap.entrySet();
//    List<Entry<String, String>> ret = new ArrayList<Entry<String, String>>(set);
//    return ret;
//  }

  public static ObservableList<Entry<String, String>> getAllVariables() {
    return observableEntryList;
  }

}