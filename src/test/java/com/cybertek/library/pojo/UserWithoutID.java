package com.cybertek.library.pojo;

import com.cybertek.library.utils.Variables;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWithoutID {

  @JsonProperty("full_name")
  private String fullName;
  private String password;
  private String email;
  @JsonProperty("user_group_id")
  private String userGroup;
  private String status;
  @JsonProperty("start_date")
  private String startDate;
  @JsonProperty("end_date")
  private String endDate;
  private String address;

}
