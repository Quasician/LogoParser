package slogo.model;

import java.util.ArrayList;

public class TreeNode implements Node{
    private String data;
    private ArrayList<TreeNode> children;

    public TreeNode()
    {
        children = new ArrayList<>();
    }

    public TreeNode(String data)
    {
        this.data = data;
        children = new ArrayList<>();
    }

    public String getData() {
        return data;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    @Override
    public void addChild(Node child) {
        children.add((TreeNode)child);
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
