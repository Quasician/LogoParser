package slogo.View;

import javax.lang.model.element.Element;
import java.util.HashMap;

public class VariableHistory {
    private HashMap<String, Element> Variables;

    public VariableHistory(){
        Variables=new HashMap<>();
    }

    public void addVariable(String name, Element value){
        for (String count:Variables.keySet()){
            if(count.equals(name)){
                Variables.replace(name,value);
            }else {
                Variables.putIfAbsent(name, value);
            }
        }
    }

    public Element getVariable(String name){
        for(String count:Variables.keySet()){
            if(count.equals(name)){
                return Variables.get(count);
            }
        }
        return null;
    }

}
