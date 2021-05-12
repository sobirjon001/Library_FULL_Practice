package com.cybertek.library.utils;

import com.cybertek.library.pages.Landing_Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class Browser_Utilities implements Variables {

  protected Landing_Page page = new Landing_Page();

  protected List<String> dropdownOptions;

  public void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void login(String email, String password) {
    page.inputLoginEmail.sendKeys(email);
    page.inputLoginPassword.sendKeys(password);
    page.buttonLogin.click();
  }

  public String getUrlEnd() {
    String currentUrl = Driver.getDriver().getCurrentUrl();
    return currentUrl.substring(currentUrl.lastIndexOf("/"));
  }

  public void waitForVisibilityOf(WebElement webElement) {
    wait.until(ExpectedConditions.visibilityOf(webElement));
  }

  public List<String> getWebElementsTexts(List<WebElement> webElements) {
    List<String> result = new ArrayList<>();
    for (WebElement each : webElements) {
      result.add(each.getText());
    }
    return result;
  }

  public void waitForTitleToEqual(String expectedTitle) {
    wait.until(ExpectedConditions.titleIs(expectedTitle));
  }

  public void waitForInvisibilityOf(WebElement webElement) {
    wait.until(ExpectedConditions.invisibilityOf(webElement));
  }

}
