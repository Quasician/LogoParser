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
  private static ObservableMap<String, String> observableMap = FXCollections.observableHashMap();
  private static ObservableList<Entry<String, String>> observableEntryList = FXCollections.observableList(new ArrayList<>());
  private static ObservableList<String> keys = FXCollections.observableArrayList();
  private static ObservableList<String> vals = FXCollections.observableArrayList();

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


    observableMap.addListener((MapChangeListener.Change<? extends String, ? extends String> change) -> {
      boolean removed = change.wasRemoved();
      if (removed != change.wasAdded()) {
        // no put for existing key
        if (removed) {
          keys.remove(change.getKey());
        } else {
          keys.add(change.getKey());
        }
      }
    });

  }

  public static void addToMap(String name, String expression) {
//    seeChange();
    if (observableMap.containsKey(name)) {
      observableMap.put(name, expression);
    } else {
      observableMap.putIfAbsent(name, expression);
      keys.add(name);
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

  public static ObservableList<String> getKeysList(){
    return keys;
  }

  public static ObservableList<String> getValsList(){
    return vals;
  }

  public static ObservableMap<String, String> getObservableMap(){
    return observableMap;
  }

}