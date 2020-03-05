package slogo.model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;
import slogo.View.UserException;


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
        viewTurtleList.get(turtle.getId()-1).setActivated(newValue);
        System.out.println(
            "New value of turtle " + turtle.getId() + " : + " + turtle.isActivatedProperty()
                .getValue() + " VIEW: + " + viewTurtleList.get(turtle.getId()-1).isActivatedProperty()
                .getValue());
      }
    });
  }

  public static void addTurtleToModelList(Turtle modelTurtle) {
    modelTurtle.setId(modelTurtleList.size()+1);
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
        System.out.println("TURTLE Y" + turtle.getY());
        //System.out.println("Turtle "+ turtle.getId()+ " is activated");
      }
    }
    System.out.println("ACTIVE SIZE: " + activeTurtles.size());
    return activeTurtles;
  }

  public static void makeModelTurtleActivated(int id) {
    changeActivation(id, true);
  }

  public static void makeModelTurtleDeactivated(int id) {
    changeActivation(id, false);
  }

  private static void changeActivation(int id, boolean activate) {
    try {
      modelTurtleList.get(id - 1).setActivated(activate);
    } catch (IndexOutOfBoundsException e) {
      //TODO: fix the error message later
      throw new CommandException("Please enter an integer greater than 0 for turtle index.");
    }
  }

  public int getTurtles() {
    return modelTurtleList.size();
  }
}
