package slogo.View;

import java.util.ResourceBundle;

public class Visaulizer {

  public static final String RESOURCES = "resources";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
//  public static final String LANGUAGE = "English";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES + "/";
//  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);
  public static ResourceBundle SIMULATION_RESOURCE = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "DisplayEnglish");
}
