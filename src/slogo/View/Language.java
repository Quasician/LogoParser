package slogo.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Language {
  private final String ERROR="Not a valid language.";
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

    PossibleLanguage(String language) {
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

    throw new RuntimeException(ERROR);
  }
}
