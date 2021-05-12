package com.cybertek.library.utils;

import com.cybertek.library.pojo.User;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public interface Variables {

  Faker faker = new Faker();
  WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 2);

}
