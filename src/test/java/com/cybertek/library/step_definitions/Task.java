package com.cybertek.library.step_definitions;

import com.cybertek.library.utils.Browser_Utilities;
import com.cybertek.library.utils.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class Task extends Browser_Utilities{

  @Test
  public void test() {
    // getting driver to open page
    Driver.getDriver().get("https://www.noon.com/uae-en/");

    // global variables
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    Map<String, Set<String>> productsMap = new LinkedHashMap<>();

    // 1 Scroll the hole page to load all DOM elements
    List<WebElement> lasyLoads = Driver.getDriver().findElements(By.xpath(
            "//div[contains(@class, 'iGMThe')]/div[@class='lazyload-wrapper']"
    ));
    System.out.println("lasyLoads.size() = " + lasyLoads.size());
    for (int i = 0; i < lasyLoads.size(); i+=3) {
      js.executeScript("arguments[0].scrollIntoView(true)", lasyLoads.get(i));
      sleep(1);
    }

    // 2 getting all carousels <- only available after hole DOM is loaded
    List<WebElement> productCarousels =
            Driver.getDriver().findElements(By.xpath(
                    "//div[@id='__next']//div[contains(@class, 'jqTxQZ')]"
            ));
    System.out.println("productCarousels.size() = " + productCarousels.size());

    // 3 going through each carousel
    String key = "carousel number ";
    int count = 0;
    for (WebElement eachCarousel : productCarousels) {
      count++;
      System.out.println("step " + count);
      js.executeScript("arguments[0].scrollIntoView(true)", eachCarousel);
      Set<String> productNames = new LinkedHashSet<>();
      WebElement nextButton = eachCarousel.findElement(By.xpath(
              "./div[contains(@class, 'swiper-button-next')]"
      ));

      // 4 collecting products inside each carousel
      int count2 = 0;
      boolean isCarouselNotFinished = true;
      while (isCarouselNotFinished){
        sleep(1);
        List<WebElement> products = eachCarousel.findElements(By.xpath(
                ".//div[contains(@class, 'liZdac')]"
        ));
        for (WebElement product : products) {
          if(product.isDisplayed()){
            count2++;
            productNames.add("\t\t" + count2 + " | " + product.getText());
          }
        }
        if(!nextButton.isDisplayed()) isCarouselNotFinished = false;
        else nextButton.click();
      }

      // 5 storing each carousel as key and products as List<String>
      productsMap.put(key + count, productNames);
    }

    // 6 printing all carousels with it's own products
    productsMap.forEach((k, strings) -> {
      System.out.println("printing " + k);
      strings.forEach(System.out::println);
    });

    // closing driver
    Driver.close();
  }

}
