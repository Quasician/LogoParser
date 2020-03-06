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

  public TurtleList(ObservableList<Turtle> modelList, ObservableList<Turtle> viewList) {
    modelTurtleList = modelList;
    viewTurtleList = viewList;
    addSizeListener();
    for (Turtle turtle : modelTurtleList) {
      addActivatedPropertyListener(turtle);
    }
  }

  private void addSizeListener() {
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

  private void addActivatedPropertyListener(Turtle turtle) {
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

  public void addTurtleToModelList(Turtle modelTurtle) {
    modelTurtle.setId(modelTurtleList.size()+1);
    modelTurtleList.add(modelTurtle);
    addActivatedPropertyListener(modelTurtle);
  }

  public ObservableList<Turtle> getModelTurtleList() {
    return modelTurtleList;
  }

  public ObservableList<Turtle> getViewTurtleList() {
    return viewTurtleList;
  }

  public ObservableList<Turtle> getActiveTurtleList() {
    ObservableList<Turtle> activeTurtles = FXCollections.observableArrayList();
    for (Turtle turtle : viewTurtleList) {
      if (turtle.isActivatedProperty().getValue()) {
        activeTurtles.add(turtle);
        System.out.println("Turtle "+ turtle.getId()+ " is activated");
      }
    }
    System.out.println("ACTIVE SIZE: " + activeTurtles.size());
    return activeTurtles;
  }

  public void makeModelTurtleActivated(int id) {
    changeActivation(id, true);
  }

  public void makeModelTurtleDeactivated(int id) {
    changeActivation(id, false);
  }

  private void changeActivation(int id, boolean activate) {
   try {
    System.out.println(id);
      modelTurtleList.get(id-1).setActivated(activate);
   } catch (IndexOutOfBoundsException | NumberFormatException e) {
      //TODO: fix the error message later
      throw new CommandException("Please enter an integer greater than 0 for turtle index.");
    }
  }

  public int getTurtles() {
    return modelTurtleList.size();
  }
}
