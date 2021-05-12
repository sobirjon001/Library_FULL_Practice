package com.cybertek.library.pages;

import com.cybertek.library.utils.Driver;
import io.cucumber.java.id.Diasumsikan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Landing_Page extends Base_Page{

  @FindBy(xpath = "//footer")
  public WebElement footer;

  @FindBy(id = "navbarDropdown")
  public WebElement louOutDropdown;

  @FindBy(xpath = "//a[.='Log Out']")
  public WebElement buttonLogOut;

  @FindBy(id = "menu_item")
  public WebElement navBarModules;

  public List<WebElement> getNavBarModulesLinksTitles() {
    return navBarModules.findElements(By.xpath(".//span[@class='title']"));
  }

  public WebElement getNavBarModuleByName(String module) {
    return navBarModules.findElement(By.xpath(
            ".//a[ .//span[.='" + module + "'] ]"
    ));
  }

  @FindBy(xpath = "//a[@href='tpl/add-user.html']")
  public WebElement buttonAddNewUser;

  @FindBy(id = "add_user_form")
  public WebElement formAddNewUser;

  @FindBy(xpath = "//form[@id='add_user_form']//input[@name='full_name']")
  public WebElement inputNewUserFullName;

  @FindBy(xpath = "//form[@id='add_user_form']//input[@name='password']")
  public WebElement inputNewUserPassword;

  @FindBy(xpath = "//form[@id='add_user_form']//input[@name='email']")
  public WebElement inputNewUserEmail;

  @FindBy(xpath = "//form[@id='add_user_form']//select[@id='user_group_id']")
  public WebElement selectNewUserUserGroup;

  @FindBy(xpath = "//form[@id='add_user_form']//select[@id='status']")
  public WebElement selectNewUserStatus;

  @FindBy(xpath = "//form[@id='add_user_form']//input[@name='start_date']")
  public WebElement inputNewUserStartDate;

  @FindBy(xpath = "//form[@id='add_user_form']//input[@name='end_date']")
  public WebElement inputNewUserEndDate;

  @FindBy(xpath = "//form[@id='add_user_form']//textarea[@id='address']")
  public WebElement textareaNewUserAddress;

  @FindBy(xpath = "//form[@id='add_user_form']//button[.='Save changes']")
  public WebElement buttonNewUserSaveChanges;

  @FindBy(xpath = "//form[@id='add_user_form']//button[.='Close']")
  public WebElement buttonNewUserClose;

  @FindBy(xpath = "//div[@id='tbl_users_filter']//input")
  public WebElement inputSearchUsers;

  @FindBy(xpath = "//table[@id='tbl_users']//tbody/tr[1]/td[position()>1]")
  public List<WebElement> firstRowUserTableInfo;

  @FindBy(xpath = "//table[@id='tbl_users']//tbody/tr[1]/td[1]/a")
  public WebElement buttonEditFirstUserFromUserTable;

  @FindBy(id = "edit_user_form")
  public WebElement formEditUser;

  @FindBy(xpath = "//form[@id='edit_user_form']//input[@name='full_name']")
  public WebElement inputEditUserFullName;

  @FindBy(xpath = "//form[@id='edit_user_form']//input[@name='password']")
  public WebElement inputEditUserPassword;

  @FindBy(xpath = "//form[@id='edit_user_form']//input[@name='email']")
  public WebElement inputEditUserEmail;

  @FindBy(xpath = "//form[@id='edit_user_form']//input[@name='start_date']")
  public WebElement inputEditUserStartDate;

  @FindBy(xpath = "//form[@id='edit_user_form']//input[@name='end_date']")
  public WebElement inputEditUserEndDate;

  @FindBy(xpath = "//form[@id='edit_user_form']//textarea[@name='address']")
  public WebElement textareaEditUserAddress;

  @FindBy(xpath = "//form[@id='edit_user_form']//button[.='Save changes']")
  public WebElement buttonEditUserSaveChanges;

  public WebElement dropdownAtLabelContains(String label) {
    return Driver.getDriver().findElement(By.xpath(
            "//label[contains(text(),'" + label + "')]/select"
    ));
  }

  @FindBy(xpath = "//table//thead/tr[1]/th")
  public List<WebElement> columnsUserTableInfo;

  @FindBy(xpath = "//select[@id='book_categories']")
  public WebElement selectBookCategory;

  @FindBy(xpath = "//table[@id='tbl_books']/tbody/tr[1]/td[5]")
  public WebElement categoryCellFirstRowBookTable;
}
