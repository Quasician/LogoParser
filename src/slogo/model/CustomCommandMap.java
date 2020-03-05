package slogo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import slogo.View.Triplet;

public class CustomCommandMap {
    private static HashMap<String, String[]> variableMap = new HashMap<>();
    private static HashMap<String,String> commandsMap = new HashMap<>();
//    private ObservableList<List<String>> customCommandObsList = FXCollections.observableArrayList(); //idk if this is bad practice
    private static List<Triplet<String, String, String>> listOfTriplets = new ArrayList<>();
    private static ObservableList<Triplet<String, String, String>> customCommandObsList = FXCollections.observableArrayList(); //idk if this is bad practice


    public static String[] getVariables(String customCommand) {
        return variableMap.get(customCommand);
    }

    public static String getCommands(String customCommand) {
        return commandsMap.get(customCommand);
    }

    public static void addCustomCommand(String name, String[] variables, String commands)
    {
        if(!commandsMap.containsKey(name)){
            String varString = "";
            for(String var : variables){
                varString = (varString + " " + var);
            }
            Triplet<String, String, String> triplet = new Triplet<>(name, varString, commands);
            customCommandObsList.add(triplet);
        }

        variableMap.putIfAbsent(name,variables);
        commandsMap.putIfAbsent(name,commands);
        commandsMap.put(name,commands);
    }

    public static boolean isACustomCommand(String name)
    {
        return variableMap.containsKey(name) && commandsMap.containsKey(name);
    }

    public static Iterable<Map.Entry<String, String>> getAllCustomCommands() {

        return commandsMap.entrySet();
    }

    public static Set<String> getKeySet() {

        return commandsMap.keySet();
    }

    public static ObservableList getCommandTriplets(){
        return customCommandObsList;
    }

}
