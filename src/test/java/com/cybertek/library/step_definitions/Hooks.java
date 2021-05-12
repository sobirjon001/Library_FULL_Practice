package com.cybertek.library.step_definitions;

import com.cybertek.library.utils.API_Utilities;
import com.cybertek.library.utils.ConfigurationReader;
import com.cybertek.library.utils.DB_Utilities;
import com.cybertek.library.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
  @Before(value = "@db")
  public void connectDB() {
    DB_Utilities.createConnection();
  }

  @After(value = "@db", order = 1)
  public void disconnectDb() {
    DB_Utilities.closeConnection();
  }

  @Before(value = "@api")
  public void initAPI() {
    RestAssured.baseURI = ConfigurationReader.getProperty("library1.api.base-url");
    RestAssured.basePath = ConfigurationReader.getProperty("library1.api.base_path");
    API_Utilities.librarianToken = API_Utilities.getToken(
            ConfigurationReader.getProperty("librarian66"),
            ConfigurationReader.getProperty("librarian66Password")
    );
  }

  @After(value = "@api", order = 1)
  public void closeAPI() {
    RestAssured.reset();
  }

  @After(order = 2)
  public void closeWebDriver() {
    Driver.close();
  }

}
