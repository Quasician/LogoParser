package slogo.model;

public interface Node {
    public void addChild(Node child);
    public void setData(String data);
    public String getData();
}
