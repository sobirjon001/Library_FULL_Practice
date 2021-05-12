package com.cybertek.library.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

  private static WebDriver driver;
  private static final String browser;

  static {
    browser = ConfigurationReader.getProperty("browser");
  }

  public static WebDriver getDriver() {

    if(driver == null) {

      switch (browser) {
        case "remote-chrome":
          try {
            URL url = new URL("http:/host.docker.internal:4444/wd/hub");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName("chrome");
            driver = new RemoteWebDriver(url, desiredCapabilities);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        case "chrome":
          WebDriverManager.chromedriver().setup();
          driver = new  ChromeDriver();
          break;
      }
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    return driver;
  }

  public static void close() {
    if(driver != null) {
      driver.close();
      driver = null;
    }
  }
}
