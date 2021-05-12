package com.cybertek.library.step_definitions;

import com.cybertek.library.pojo.User;
import com.cybertek.library.utils.API_Utilities;
import io.cucumber.java.en.Then;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Assume;

public class API_Step_Definitions extends API_Utilities {

  @Then("New {string} is match in users by id from API")
  public void new_is_match_in_users_by_id_from_api(String userNum) {
    User user = User.getUserByNum(userNum);
    User user_api = given()
            .log().all()
            .contentType(ContentType.URLENC)
            .header("x-library-token", librarianToken)
            .pathParam("id", user.getId()).
    when()
            .get("/get_user_by_id/{id}").
    then()
            .log().all()
            .statusCode(200).
    extract()
            .jsonPath().getObject("", User.class);

    Assert.assertEquals(user.getId(), user_api.getId());
    Assert.assertEquals(user.getFullName(), user_api.getFullName());
    Assert.assertEquals(user.getEmail(), user_api.getEmail());
    Assert.assertEquals(user.getAddress(), user_api.getAddress());
    Assert.assertEquals(user.getStartDate(), user_api.getStartDate());
    Assert.assertEquals(user.getEndDate(), user_api.getEndDate());
  }
}
