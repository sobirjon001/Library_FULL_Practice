package com.cybertek.library.step_definitions;

import com.cybertek.library.pojo.User;
import com.cybertek.library.utils.Browser_Utilities;
import com.cybertek.library.utils.ConfigurationReader;
import com.cybertek.library.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Browser_Step_Definitions extends Browser_Utilities {

  @Given("User is on Library login {string} page")
  public void user_is_on_library_login_page(String loginUrl) {
    Driver.getDriver().get(ConfigurationReader.getProperty(loginUrl));
  }
  @Given("User login as a {string}")
  public void user_login_as_a(String role) {
    String email = "";
    String password = "";
    switch (role) {
      case "student":
        email = ConfigurationReader.getProperty("student133");
        password = ConfigurationReader.getProperty("student133Password");
        break;
      case "librarian":
        email = ConfigurationReader.getProperty("librarian66");
        password = ConfigurationReader.getProperty("librarian66Password");
        break;
    }
    login(email, password);
  }
  @Then("User is on {string} page")
  public void user_is_on_page(String expectedPage) {
    if(!expectedPage.equals("login")) waitForVisibilityOf(page.footer);
    String urlEnding = getUrlEnd();
    Assert.assertTrue(urlEnding.contains(expectedPage));
  }

  @When("User loges out from app")
  public void user_loges_out_from_app() {
    page.louOutDropdown.click();
    waitForVisibilityOf(page.buttonLogOut);
    page.buttonLogOut.click();
  }

  @Given("User login with credentials {string} and {string}:")
  public void user_login_with_credentials_and(String email, String password) {
    login(email, password);
  }
  @Then("User should see following modules:")
  public void user_should_see_following_modules(List<String> expectedModules) {
    List<WebElement> actualNavBarModule = page.getNavBarModulesLinksTitles();
    List<String> actualModules = getWebElementsTexts(actualNavBarModule);
    Assert.assertEquals(expectedModules, actualModules);
  }

  @When("User is on {string} module")
  public void user_is_on_module(String module) {
    page.getNavBarModuleByName(module).click();
  }
  @When("User opened add new user form")
  public void user_opened_add_new_user_form() {
    page.buttonAddNewUser.click();
  }
  @When("User adds new {string} with valid info")
  public void user_adds_new_with_valid_info(String userNum) {
    waitForVisibilityOf(page.formAddNewUser);
    User random = User.getRandomUser();
    page.inputNewUserFullName.sendKeys(random.getFullName());
    page.inputNewUserEmail.sendKeys(random.getEmail());
    page.inputNewUserPassword.sendKeys(random.getPassword());
    Select selectUserGroup = new Select(page.selectNewUserUserGroup);
    String userGroup = random.getUserGroup().equals("2") ? "Librarian" : "Students";
    selectUserGroup.selectByVisibleText(userGroup);
    Select selectStatus = new Select(page.selectNewUserStatus);
    selectStatus.selectByVisibleText(random.getStatus());
    page.inputNewUserStartDate.clear();
    page.inputNewUserStartDate.sendKeys(random.getStartDate());
    page.inputNewUserEndDate.clear();
    page.inputNewUserEndDate.sendKeys(random.getEndDate());
    page.textareaNewUserAddress.sendKeys(random.getAddress());
    page.buttonNewUserSaveChanges.click();
    waitForInvisibilityOf(page.formAddNewUser);
    User.saveUser(userNum, random);
  }
  @Then("New {string} is added or updated to records table")
  public void new_is_added_or_updated_to_records_table(String userNum) {
    User user = User.getUserByNum(userNum);
    List<WebElement> actualUser = page.firstRowUserTableInfo;
    List<String> actualUserData = getWebElementsTexts(actualUser);
    Assert.assertEquals(user.getFullName(), actualUserData.get(1));
    Assert.assertEquals(user.getEmail(), actualUserData.get(2));
    String userGroup = user.getUserGroup().equals("2") ? "Librarian" : "Students";
    Assert.assertEquals(userGroup, actualUserData.get(3));
    Assert.assertEquals(user.getStatus(), actualUserData.get(4));

    user.setId(actualUserData.get(0));

    User.saveUser(userNum, user);
  }

  @When("User cancels adding new user")
  public void user_cancels_adding_new_user() {
    waitForVisibilityOf(page.formAddNewUser);
    page.buttonNewUserClose.click();
    waitForInvisibilityOf(page.formAddNewUser);
  }

  @Then("Add new User form is closed")
  public void add_new_user_form_is_closed() {
    Assert.assertFalse(page.formAddNewUser.isDisplayed());
  }

  @When("User tries to edit {string} info")
  public void user_tries_to_edit_info(String userNum) {
    List<WebElement> actualUser = page.firstRowUserTableInfo;
    User user = User.getRandomUser();
    user.setId(actualUser.get(0).getText());
    page.buttonEditFirstUserFromUserTable.click();
    waitForVisibilityOf(page.formEditUser);
    page.inputEditUserFullName.clear();
    page.inputEditUserFullName.sendKeys(user.getFullName());
    page.inputEditUserEmail.clear();
    page.inputEditUserEmail.sendKeys(user.getEmail());
    page.inputEditUserPassword.clear();
    page.inputEditUserPassword.sendKeys(user.getPassword());
    page.inputEditUserStartDate.clear();
    page.inputEditUserStartDate.sendKeys(user.getStartDate());
    page.inputEditUserEndDate.clear();
    page.inputEditUserEndDate.sendKeys(user.getEndDate());
    page.textareaEditUserAddress.clear();
    page.textareaEditUserAddress.sendKeys(user.getAddress());
    page.buttonEditUserSaveChanges.click();
    waitForInvisibilityOf(page.formEditUser);
    User.saveUser(userNum, user);
  }

  @Then("Default records dropdown value is {int}")
  public void default_records_dropdown_value_is(Integer num) {
    Select select = new Select(page.dropdownAtLabelContains("Show "));
    Assert.assertEquals(num + "", select.getFirstSelectedOption().getText());
  }

  @When("User clicks {string} dropdown")
  public void user_clicks_dropdown(String label) {

    Select select = new Select(page.dropdownAtLabelContains(label));
    dropdownOptions = getWebElementsTexts(select.getOptions());
  }

  @Then("User should see the following dropdown options:")
  public void user_should_see_the_following_dropdown_options(List<String> expectedOptions) {
    Assert.assertEquals(expectedOptions, dropdownOptions);
  }

  @Then("User should see the following table column names:")
  public void user_should_see_the_following_table_column_names(List<String> expectedColumns) {
    Assert.assertEquals(expectedColumns, getWebElementsTexts(page.columnsUserTableInfo));
  }

  @When("User selects a {string} from search by category dropdown")
  public void user_selects_a_from_search_by_category_dropdown(String category) {
    Select select = new Select(page.selectBookCategory);
    select.selectByVisibleText(category);
  }
  @Then("Books table should show books by {string}")
  public void books_table_should_show_books_by(String expectedCategory) {
    sleep(1);
    Assert.assertEquals(expectedCategory, page.categoryCellFirstRowBookTable.getText());
  }
}
