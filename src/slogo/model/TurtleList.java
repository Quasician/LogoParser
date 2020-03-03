package slogo.model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;


public class TurtleList {

  private static ObservableList<Turtle> modelTurtleList;
  private static ObservableList<Turtle> viewTurtleList;

  public static void createTurtleLists(ObservableList<Turtle> modelList,
      ObservableList<Turtle> viewList) {
    modelTurtleList = modelList;
    viewTurtleList = viewList;
    addSizeListener();
    for (Turtle turtle : modelTurtleList) {
      addActivatedPropertyListener(turtle);
    }
  }

  private static void addSizeListener() {
    modelTurtleList.addListener(new ListChangeListener<Turtle>() {
      @Override
      public void onChanged(Change<? extends Turtle> c) {
        c.next();
        List<Turtle> newTurtles = (List<Turtle>) c.getAddedSubList();
        for (Turtle changedTurtle : newTurtles) {
          Turtle vturtle = changedTurtle;
          System.out.println(
              "MODEL: + " + changedTurtle.isActivatedProperty().getValue() + " VIEW: + " + vturtle
                  .isActivatedProperty().getValue());
          viewTurtleList.add(vturtle);
        }
      }
    });
  }

  private static void addActivatedPropertyListener(Turtle turtle) {
    turtle.isActivatedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
          Boolean newValue) {
        viewTurtleList.get(turtle.getId()).setActivated(newValue);
        System.out.println(
            "New value of turtle " + turtle.getId() + " : + " + turtle.isActivatedProperty()
                .getValue() + " VIEW: + " + viewTurtleList.get(turtle.getId()).isActivatedProperty()
                .getValue());
      }
    });
  }

  public static void addTurtleToModelList(Turtle modelTurtle) {
    modelTurtle.setId(modelTurtleList.size());
    modelTurtleList.add(modelTurtle);
    addActivatedPropertyListener(modelTurtle);
  }

  public static ObservableList<Turtle> getModelTurtleList() {
    return modelTurtleList;
  }

  public static ObservableList<Turtle> getViewTurtleList() {
    return viewTurtleList;
  }

  public static ObservableList<Turtle> getActiveTurtleList() {
    ObservableList<Turtle> activeTurtles = FXCollections.observableArrayList();
    for (Turtle turtle : viewTurtleList) {
      if (turtle.isActivatedProperty().getValue()) {
        activeTurtles.add(turtle);
        //System.out.println("Turtle "+ turtle.getId()+ " is activated");
      }
    }
    System.out.println("ACTIVE SIZE: " + activeTurtles.size());
    return activeTurtles;
  }

  public static void makeModelTurtleActivated(int id) {
    modelTurtleList.get(id).setActivated(true);
  }

  public static void makeModelTurtleDeactivated(int id) {
    modelTurtleList.get(id).setActivated(false);
  }

  public int getTurtles() {
    return modelTurtleList.size();
  }
}
