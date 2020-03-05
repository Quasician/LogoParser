package slogo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.View.Triplet;

public class CustomCommandMapNonstatix {
    private HashMap<String, String[]> variableMap;
    private HashMap<String,String> commandsMap;
//    private ObservableList<List<String>> customCommandObsList = FXCollections.observableArrayList(); //idk if this is bad practice
//    private List<Triplet<String, String, String>> listOfTriplets = new ArrayList<>();
    private ObservableList<Triplet<String, String, String>> customCommandObsList; //idk if this is bad practice


    public CustomCommandMapNonstatix(){
        variableMap = new HashMap<>();
        commandsMap = new HashMap<>();
        customCommandObsList = FXCollections.observableArrayList();

    }

    public String[] getVariables(String customCommand) {
        return variableMap.get(customCommand);
    }

    public String getCommands(String customCommand) {
        return commandsMap.get(customCommand);
    }

    public void addCustomCommand(String name, String[] variables, String commands)
    {
        variableMap.putIfAbsent(name,variables);
        commandsMap.putIfAbsent(name,commands);
        commandsMap.put(name,commands);


        String varString = "";
        for(String var : variables){
            varString = (varString + " " + var);
        }
        Triplet<String, String, String> triplet = new Triplet<>(name, varString, commands);
        customCommandObsList.add(triplet);

    }

    public boolean isACustomCommand(String name)
    {
        return variableMap.containsKey(name) && commandsMap.containsKey(name);
    }

    public Iterable<Map.Entry<String, String>> getAllCustomCommands() {

        return commandsMap.entrySet();
    }

    public Set<String> getKeySet() {

        return commandsMap.keySet();
    }

//    public get

}
