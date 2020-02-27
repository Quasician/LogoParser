package slogo.model;

public interface Node {
    public void addChild(Node child);
    public void setData(String data);
    public void setResult(String data);
    public String getName();
    public String getResult();
    public Node getChild();
}
