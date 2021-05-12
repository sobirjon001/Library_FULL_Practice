package com.cybertek.library.pages;

import com.cybertek.library.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Base_Page {

  public Base_Page() {
    PageFactory.initElements(Driver.getDriver(), this);
  }

  @FindBy(id = "inputEmail")
  public WebElement inputLoginEmail;

  @FindBy(id = "inputPassword")
  public WebElement inputLoginPassword;

  @FindBy(xpath = "//button[.='Sign in']")
  public WebElement buttonLogin;
}
