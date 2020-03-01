package slogo.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;


public class TurtleList {
    private static ObservableList<Turtle> modelTurtleList;
    private static ObservableList<Turtle> viewTurtleList;

    public void createTurtleLists(ObservableList<Turtle> modelList, ObservableList<Turtle> viewList)
    {
        modelTurtleList = modelList;
        viewTurtleList = viewList;
        addListener();
    }

    private static void addListener()
    {
        modelTurtleList.addListener(new ListChangeListener<Turtle>() {
            @Override
            public void onChanged(Change<? extends Turtle> c) {
                List<Turtle> newTurtles = (List<Turtle>) c.getAddedSubList();
                for(Turtle changedTurtle:newTurtles) {
                    Turtle vturtle = changedTurtle;
                    viewTurtleList.add(vturtle);
                }
            }
        });
    }

    public static void addTurtleToModelList(Turtle modelTurtle)
    {
        modelTurtle.setId(modelTurtleList.size());
        modelTurtleList.add(modelTurtle);
    }

    public static ObservableList<Turtle> getModelTurtleList()
    {
        return modelTurtleList;
    }

    public static ObservableList<Turtle> getViewTurtleList() {
        return viewTurtleList;
    }

    public int getTurtles() {
        return modelTurtleList.size();
    }
}
