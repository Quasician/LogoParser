package slogo.model;

import java.util.HashMap;

// Class maps all instructions to their operation type so we can split our commands folder into subdirectories to make it cleaner
public class CommandTypeHashMap {

  private static HashMap<String, String> commandTypeMap = new HashMap<>();


  private static void addCommands() {
   // commandTypeMap = new HashMap<>();
    addTurtleCommands();
    addTurtleQueries();
    addMathCommands();
    addBooleanCommands();
    addVCUCommands();
    addDisplayCommands();
    addMultipleTurtleCommands();
  }

  private static void addTurtleCommands() {
    String s = "TurtleCommands";
    commandTypeMap.putIfAbsent("Forward", s);
    commandTypeMap.putIfAbsent("Backward", s);
    commandTypeMap.putIfAbsent("Left", s);
    commandTypeMap.putIfAbsent("Right", s);
    commandTypeMap.putIfAbsent("SetHeading", s);
    commandTypeMap.putIfAbsent("SetTowards", s);
    commandTypeMap.putIfAbsent("SetPosition", s);
    commandTypeMap.putIfAbsent("PenDown", s);
    commandTypeMap.putIfAbsent("PenUp", s);
    commandTypeMap.putIfAbsent("ShowTurtle", s);
    commandTypeMap.putIfAbsent("HideTurtle", s);
    commandTypeMap.putIfAbsent("Home", s);
    commandTypeMap.putIfAbsent("ClearScreen", s);
  }

  private static void addTurtleQueries() {
    String s = "TurtleQueries";
    commandTypeMap.putIfAbsent("XCoordinate", s);
    commandTypeMap.putIfAbsent("YCoordinate", s);
    commandTypeMap.putIfAbsent("Heading", s);
    commandTypeMap.putIfAbsent("IsPenDown", s);
    commandTypeMap.putIfAbsent("IsShowing", s);
  }

  private static void addMathCommands() {
    String s = "MathCommands";
    commandTypeMap.putIfAbsent("Sum", s);
    commandTypeMap.putIfAbsent("Difference", s);
    commandTypeMap.putIfAbsent("Product", s);
    commandTypeMap.putIfAbsent("Quotient", s);
    commandTypeMap.putIfAbsent("Remainder", s);
    commandTypeMap.putIfAbsent("Minus", s);
    commandTypeMap.putIfAbsent("Random", s);
    commandTypeMap.putIfAbsent("Sine", s);
    commandTypeMap.putIfAbsent("Cosine", s);
    commandTypeMap.putIfAbsent("Tangent", s);
    commandTypeMap.putIfAbsent("ArcTangent", s);
    commandTypeMap.putIfAbsent("NaturalLog", s);
    commandTypeMap.putIfAbsent("Power", s);
    commandTypeMap.putIfAbsent("Pi", s);
  }


  private static void addBooleanCommands() {
    String s = "BooleanCommands";
    commandTypeMap.putIfAbsent("LessThan", s);
    commandTypeMap.putIfAbsent("GreaterThan", s);
    commandTypeMap.putIfAbsent("Equal", s);
    commandTypeMap.putIfAbsent("NotEqual", s);
    commandTypeMap.putIfAbsent("And", s);
    commandTypeMap.putIfAbsent("Or", s);
    commandTypeMap.putIfAbsent("Not", s);
  }

  private static void addVCUCommands() {
    String s = "VCUCommands";
    commandTypeMap.putIfAbsent("MakeVariable", s);
    commandTypeMap.putIfAbsent("Repeat", s);
    commandTypeMap.putIfAbsent("DoTimes", s);
    commandTypeMap.putIfAbsent("For", s);
    commandTypeMap.putIfAbsent("If", s);
    commandTypeMap.putIfAbsent("IfElse", s);
    commandTypeMap.putIfAbsent("MakeUserInstruction", s);
  }

  private static void addDisplayCommands() {
    String s = "DisplayCommands";
    commandTypeMap.putIfAbsent("SetBackground", s);
    commandTypeMap.putIfAbsent("SetPenColor", s);
    commandTypeMap.putIfAbsent("SetPenSize", s);
    commandTypeMap.putIfAbsent("SetShape", s);
    commandTypeMap.putIfAbsent("SetPalette", s);
    commandTypeMap.putIfAbsent("GetPenColor", s);
    commandTypeMap.putIfAbsent("GetShape", s);
    commandTypeMap.putIfAbsent("Stamp", s);
    commandTypeMap.putIfAbsent("ClearStamps", s);
  }

  private static void addMultipleTurtleCommands() {
    String s = "MultipleTurtleCommands";
    commandTypeMap.putIfAbsent("ID", s);
    commandTypeMap.putIfAbsent("Turtles", s);
    commandTypeMap.putIfAbsent("Tell", s);
    commandTypeMap.putIfAbsent("Ask", s);
    commandTypeMap.putIfAbsent("AskWith", s);
  }

  public static String getCommandType(String s) {
    addCommands();
    return commandTypeMap.get(s);
  }
}
