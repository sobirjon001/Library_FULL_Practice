package com.cybertek.library.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

  private static final Properties properties = new Properties();

  public static String getProperty(String keyword) {
    try {
      FileInputStream file = new FileInputStream("configuration.properties");
      properties.load(file);
      file.close();
    } catch (IOException e) {
      System.out.println("Failed to read configuration.properties file");
    }
    return properties.getProperty(keyword);
  }
}
