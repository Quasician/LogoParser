package slogo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomCommandMap {
    private static HashMap<String, String[]> variableMap = new HashMap<>();
    private static HashMap<String,String> commandsMap = new HashMap<>();

    public static String[] getVariables(String customCommand) {
        return variableMap.get(customCommand);
    }

    public static String getCommands(String customCommand) {
        return commandsMap.get(customCommand);
    }

    public static void addCustomCommand(String name, String[] variables, String commands)
    {
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

}
