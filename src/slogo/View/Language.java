package slogo.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Language {

  public enum PossibleLanguage {
    ENGLISH ("English"),
    GERMAN ("German"),
    CHINESE ("Chinese"),
    FRENCH ("French"),
    ITALIAN ("Italian"),
    PORTUGUESE ("Portuguese"),
    SPANISH ("Spanish"),
    RUSSIAN ("Russian"),
    URDU ("Urdu");

    private final String language;

    private PossibleLanguage(String language) {
      this.language = language;
    }

    public String getLanguage() {
      return language;
    }
  }

  private StringProperty languageProperty = new SimpleStringProperty();

  public Language() {
    String currentLanguage = PossibleLanguage.ENGLISH.getLanguage();
    languageProperty.set(currentLanguage);
  }

  public StringProperty getLanguageProperty() {
    return languageProperty;
  }

  public String getCurrentLanguage() {
    return languageProperty.get();
  }

  protected void setLanguage(String language) {
    PossibleLanguage[] languages = PossibleLanguage.values();

    for (PossibleLanguage lang : languages) {
      if (lang.getLanguage().equals(language)) {
        languageProperty.set(language);
        return;
      }
    }

    //make this error better later
    throw new RuntimeException("Not a valid language.");
  }
}
