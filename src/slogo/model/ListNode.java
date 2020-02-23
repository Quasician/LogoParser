package slogo.model;

public class ListNode implements Node {

  private String data;
  private ListNode child;


  public ListNode(String data) {
    this.data = data;
  }

  public ListNode(String data, ListNode child) {
    this.data = data;
    this.child = child;
  }

  public ListNode() {

  }

  public String getData() {
    return data;
  }

  public ListNode getChild() {
    return child;
  }

  @Override
  public void addChild(Node child) {
    this.child = (ListNode) child;
  }

  public void setData(String data) {
    this.data = data;
  }
}
