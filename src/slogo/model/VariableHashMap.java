package slogo.model;

import java.util.HashMap;
import java.util.Map;

public class VariableHashMap {

  private static HashMap<String, String> varHashMap = new HashMap<>();

  public static void addToMap(String name, String expression) {
    varHashMap.putIfAbsent(name, expression);
  }

  public static String getVarValue(String name) {
    return varHashMap.get(name);
  }

  public static Iterable<Map.Entry<String, String>> getAllVariables() {
    return varHashMap.entrySet();
  }
}
