package slogo.View;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import slogo.model.Turtle;

public class Observables {

  private ObservableList<Turtle> viewTurtleList;
  private ObservableList<Turtle> activeTurtleList;
  private ObservableMap<String, String> viewObservableMap;

  public Observables(ObservableList<Turtle> viewTurtleList, ObservableList<Turtle> activeTurtleList,
      ObservableMap<String, String> viewObservableMap) {
    this.viewTurtleList = viewTurtleList;
    this.activeTurtleList = activeTurtleList;
    this.viewObservableMap = viewObservableMap;
  }

  public ObservableList<Turtle> getViewTurtleList() {
    return viewTurtleList;
  }

  public ObservableList<Turtle> getActiveTurtleList() {
    return activeTurtleList;
  }

  public ObservableMap<String, String> getViewObservableMap() {
    return viewObservableMap;
  }
}
