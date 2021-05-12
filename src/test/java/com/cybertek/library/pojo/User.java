package com.cybertek.library.pojo;

import com.cybertek.library.utils.Variables;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends UserWithoutID implements Variables {

  private String id;

  public static User user0;
  public static User user1;
  public static User user2;
  public static User user3;

  public static User getRandomUser() {
    User result = new User();
    result.setFullName(faker.name().fullName());
    result.setEmail(faker.internet().emailAddress());
    result.setPassword(faker.internet().password());
    result.setUserGroup("3");
    result.setStatus("ACTIVE");
    result.setStartDate(LocalDate.now().toString());
    result.setEndDate(LocalDate.now().plusMonths(4L).toString());
    result.setAddress(faker.address().fullAddress());
    return result;
  }

  public static void saveUser(String userNum, User user) {
    switch (userNum) {
      case "User0":
        user0 = user;
        break;
      case "User1":
        user1 = user;
        break;
      case "User2":
        user2 = user;
        break;
      case "User3":
        user3 = user;
        break;
    }
  }

  public static User getUserByNum(String userNum) {
    if(userNum.equals("User1")) return user1;
    if(userNum.equals("User2")) return user2;
    if(userNum.equals("User3")) return user3;
    return user0;
  }
}
