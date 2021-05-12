package com.cybertek.library.step_definitions;

import com.cybertek.library.pojo.User;
import com.cybertek.library.utils.DB_Utilities;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class DB_Step_Definition extends DB_Utilities {

  @Then("New {string} is found in users Data Base update id")
  public void new_is_found_in_users_data_base_update_id(String userNum) {
    User user = User.getUserByNum(userNum);
    String sql = "select id, user_group_id, status, start_date, end_date, address from users "+
            "where full_name = '" + user.getFullName() + "' and " +
            "email = '" + user.getEmail() + "';";
    runQuery(sql);
    List<String> user_db = getRowDataAsListByRowNumber(1);
    Assert.assertEquals(user.getId(), user_db.get(0));
    Assert.assertEquals(user.getUserGroup(), user_db.get(1));
    Assert.assertEquals(user.getStatus(), user_db.get(2));
    Assert.assertEquals(user.getStartDate(), user_db.get(3));
    Assert.assertEquals(user.getEndDate(), user_db.get(4));
    Assert.assertEquals(user.getAddress(), user_db.get(5));
  }
}
