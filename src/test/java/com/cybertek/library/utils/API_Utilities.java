package com.cybertek.library.utils;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class API_Utilities {

  public static String librarianToken;
  public static String studentToken;

  public static String getToken(String email, String password) {
    return given()
            .contentType(ContentType.URLENC)
            .formParam("email", email)
            .formParam("password", password).
                    when()
            .post("/login").
                    then()
            .statusCode(200).
                    extract().path("token")
            ;
  }
}
