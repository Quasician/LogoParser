package slogo.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import java.util.HashMap;
import java.util.Map;


public class VariableStorage {
  private Map<String, String> varHashMap = new HashMap<String, String>();
  private ObservableMap<String, String> modelObservableMap = FXCollections.observableHashMap();
  private ObservableMap<String, String> viewObservableMap = FXCollections.observableHashMap();
  private ObservableList<Entry<String, String>> observableEntryList = FXCollections.observableList(new ArrayList<>());
  private ObservableList<String> keys = FXCollections.observableArrayList();
  private ObservableList<String> vals = FXCollections.observableArrayList();

  public VariableStorage(ObservableMap newMap){
    modelObservableMap = newMap;
    viewObservableMap = FXCollections.observableHashMap();
    Bindings.bindContentBidirectional(modelObservableMap,viewObservableMap);
    //seeChange();
  }

  public void seeChange(){
    modelObservableMap.addListener(new MapChangeListener() {
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


    modelObservableMap.addListener((MapChangeListener.Change<? extends String, ? extends String> change) -> {
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

  public void addToMap(String name, String expression) {
//    seeChange();
    if (modelObservableMap.containsKey(name)) {
      modelObservableMap.put(name, expression);
    } else {
      modelObservableMap.putIfAbsent(name, expression);
      keys.add(name);
    }
  }

  public String getVarValue(String name) {
    return modelObservableMap.get(name);
  }

//  public static List<Entry<String, String>> getAllVariables() {
//    Set<Entry<String, String>> set = observableMap.entrySet();
//    List<Entry<String, String>> ret = new ArrayList<Entry<String, String>>(set);
//    return ret;
//  }

  public ObservableList<Entry<String, String>> getAllVariables() {
    return observableEntryList;
  }

  public ObservableList<String> getKeysList(){
    return keys;
  }

  public ObservableList<String> getValsList(){
    return vals;
  }

  public ObservableMap<String, String> getModelObservableMap(){
    return modelObservableMap;
  }
  public ObservableMap<String, String> getViewObservableMap(){
    return viewObservableMap;
  }

}