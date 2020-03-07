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
  private ObservableMap<String, String> modelObservableMap = FXCollections.observableHashMap();
  private ObservableMap<String, String> viewObservableMap = FXCollections.observableHashMap();

  public VariableStorage(ObservableMap newMap){
    modelObservableMap = newMap;
    viewObservableMap = FXCollections.observableHashMap();
    Bindings.bindContentBidirectional(modelObservableMap,viewObservableMap);
  }

  public ObservableMap<String, String> getModelObservableMap(){
    return modelObservableMap;
  }

  public ObservableMap<String, String> getViewObservableMap(){
    return viewObservableMap;
  }

}