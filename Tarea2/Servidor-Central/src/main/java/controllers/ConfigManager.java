package main.java.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.Properties;

/**
 * Clase ConfigManager.
 */

public class ConfigManager {
  private static ConfigManager instancia = null;
  private Properties properties = new Properties();

  private ConfigManager() {
    loadProperties();
  }

  /**
   * Metodo getInstance.
   */

  public static ConfigManager getInstance() {
    if (instancia == null) {
      instancia = new ConfigManager();
    }
    return instancia;
  }

  private void loadProperties() {
    String userHome = System.getProperty("user.home");
    String separador =
          FileSystems.getDefault().getSeparator();
    String file =
          userHome + separador + "config.properties";
    try {
      InputStream input = new FileInputStream(file);
      properties.load(input);
    } catch (IOException e) {
      InputStream input = ConfigManager.class
            .getResourceAsStream("/config.properties");
      System.out.println("no encontro archivo");
      try {
        properties.load(input);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    }
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
