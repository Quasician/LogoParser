package slogo.model;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Node {

  private String name;
  private String result;
  private ArrayList<TreeNode> children;

  public TreeNode() {
    children = new ArrayList<>();
  }

  public TreeNode(String name) {
    this.name = name;
    children = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public String getResult() {
    return result;
  }

  @Override
  public Node getChild() {
    return children.get(0);
  }

  public void setResult(String result) {
    this.result = result;
  }

  //TODO: MUST REFACTOR THIS
  public List<TreeNode> getChildren() {
    return  children;
  }

  @Override
  public void addChild(Node child) {
    children.add((TreeNode) child);
  }

  public int getChildrenNumber(TreeNode child) {
    return children.size();
  }

  @Override
  public void setData(String data) {
    this.name = data;
  }
}
