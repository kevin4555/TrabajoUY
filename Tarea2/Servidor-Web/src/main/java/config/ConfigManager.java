package config;

import java.io.IOException;
import java.io.InputStream;
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
    try {
      InputStream input = ConfigManager.class
            .getResourceAsStream("/config.properties");

      properties.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }
}
