package slogo.model;

import java.util.HashMap;

// Class maps all instructions to their operation type so we can split our commands folder into subdirectories to make it cleaner
public class CommandParamNumberHashMap {
    private static HashMap<String,Integer> commandParamNumberMap;
    private static void addCommands()
    {
        commandParamNumberMap = new HashMap<>();
        addTurtleCommands();
        addTurtleQueries();
        addMathCommands();
        addBooleanCommands();
        addVCUCommands();
        addDisplayCommands();
        addMultipleTurtleCommands();
    }

    private static void addTurtleCommands()
    {
        commandParamNumberMap.putIfAbsent("Forward",1);
        commandParamNumberMap.putIfAbsent("Backward",1);
        commandParamNumberMap.putIfAbsent("Left",1);
        commandParamNumberMap.putIfAbsent("Right",1);
        commandParamNumberMap.putIfAbsent("SetHeading",1);
        commandParamNumberMap.putIfAbsent("SetTowards",2);
        commandParamNumberMap.putIfAbsent("SetPosition",2);
        commandParamNumberMap.putIfAbsent("PenDown",0);
        commandParamNumberMap.putIfAbsent("PenUp",0);
        commandParamNumberMap.putIfAbsent("ShowTurtle",0);
        commandParamNumberMap.putIfAbsent("HideTurtle",0);
        commandParamNumberMap.putIfAbsent("Home",0);
        commandParamNumberMap.putIfAbsent("ClearScreen",0);
    }

    private static void  addTurtleQueries()
    {
        String s = "TurtleQueries";
        commandParamNumberMap.putIfAbsent("XCoordinate",0);
        commandParamNumberMap.putIfAbsent("YCoordinate",0);
        commandParamNumberMap.putIfAbsent("Heading",0);
        commandParamNumberMap.putIfAbsent("IsPenDown",0);
        commandParamNumberMap.putIfAbsent("IsShowing",0);
    }

    private static void addMathCommands()
    {
        String s = "MathCommands";
        commandParamNumberMap.putIfAbsent("Sum",2);
        commandParamNumberMap.putIfAbsent("Difference",2);
        commandParamNumberMap.putIfAbsent("Product",2);
        commandParamNumberMap.putIfAbsent("Quotient",2);
        commandParamNumberMap.putIfAbsent("Remainder",2);
        commandParamNumberMap.putIfAbsent("Minus",1);
        commandParamNumberMap.putIfAbsent("Random",1);
        commandParamNumberMap.putIfAbsent("Sine",1);
        commandParamNumberMap.putIfAbsent("Cosine",1);
        commandParamNumberMap.putIfAbsent("Tangent",1);
        commandParamNumberMap.putIfAbsent("ArcTangent",1);
        commandParamNumberMap.putIfAbsent("NaturalLog",1);
        commandParamNumberMap.putIfAbsent("Power",2);
        commandParamNumberMap.putIfAbsent("Pi",0);
    }


    private static void addBooleanCommands()
    {
        String s = "BooleanCommands";
        commandParamNumberMap.putIfAbsent("LessThan",2);
        commandParamNumberMap.putIfAbsent("GreaterThan",2);
        commandParamNumberMap.putIfAbsent("Equal",2);
        commandParamNumberMap.putIfAbsent("NotEqual",2);
        commandParamNumberMap.putIfAbsent("And",2);
        commandParamNumberMap.putIfAbsent("Or",2);
        commandParamNumberMap.putIfAbsent("Not",1);
    }

    private static void addVCUCommands()
    {
        String s = "VCUCommands";
        commandParamNumberMap.putIfAbsent("MakeVariable",2);
        commandParamNumberMap.putIfAbsent("Repeat",1);
        commandParamNumberMap.putIfAbsent("DoTimes",1);
        commandParamNumberMap.putIfAbsent("For",2);
        commandParamNumberMap.putIfAbsent("If",1);
        commandParamNumberMap.putIfAbsent("IfElse",1);
        commandParamNumberMap.putIfAbsent("MakeUserInstruction",0);
    }

    private static void addDisplayCommands()
    {
        String s = "DisplayCommands";
        commandParamNumberMap.putIfAbsent("SetBackground",1);
        commandParamNumberMap.putIfAbsent("SetPenColor",1);
        commandParamNumberMap.putIfAbsent("SetPenSize",1);
        commandParamNumberMap.putIfAbsent("SetShape",1);
        commandParamNumberMap.putIfAbsent("SetPalette",1);
        commandParamNumberMap.putIfAbsent("GetPenColor",1);
        commandParamNumberMap.putIfAbsent("GetShape",1);
        commandParamNumberMap.putIfAbsent("Stamp",1);
        commandParamNumberMap.putIfAbsent("ClearStamps",1);
    }

    private static void addMultipleTurtleCommands()
    {
        String s = "MultipleTurtleCommands";
        commandParamNumberMap.putIfAbsent("ID",1);
        commandParamNumberMap.putIfAbsent("Turtles",1);
        commandParamNumberMap.putIfAbsent("Tell",1);
        commandParamNumberMap.putIfAbsent("Ask",1);
        commandParamNumberMap.putIfAbsent("AskWith",1);
    }
    public static int getCommandParamNumber(String s)
    {
        commandParamNumberMap = new HashMap<>();
        System.out.println("GETTING PARAM NUMBER FOR:" +s);
        addCommands();
        return commandParamNumberMap.get(s);
    }
}
