package slogo.model;

public abstract class Command {

  protected String name;

  public Command(String name) {
    this.name = name;
  }

  public abstract void doCommand();
}
