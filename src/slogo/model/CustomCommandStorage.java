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

public class CustomCommandStorage {
    private HashMap<String, String[]> variableMap = new HashMap<>();
    private HashMap<String,String> commandsMap = new HashMap<>();
    private ObservableList<Triplet<String, String, String>> customCommandObsList; //idk if this is bad practice
    private static HashMap<String, Integer> customCommandParamNumberMap = new HashMap<>();

    public CustomCommandStorage()
    {
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

    public ObservableList getCommandTriplets(){
        return customCommandObsList;
    }

    public int getCustomCommandParamNumber(String s) {
        //commandParamNumberMap = new HashMap<>();
        //System.out.println("GETTING PARAM NUMBER FOR:" + s);
        return customCommandParamNumberMap.get(s);
    }

    public void addCustomCommandParamNumber(String s, int paramNum) {
        //commandParamNumberMap = new HashMap<>();
        customCommandParamNumberMap.putIfAbsent(s, paramNum);
        //System.out.println("Adding custom command: " + s);

    }
}
