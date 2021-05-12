Feature: 1 Add user end point test

#this test case specifically for testing the add user service
  @api
  Scenario: add student using add user service
    Given new "User2" is added using API the add_user endpoint
    And User is on Library login "qa1_url" page
    When I login as the "User2" created using add_user endpoint
    Then User is on "#books" page

    #in this test case we test the books table
    # for this test we want to use API to generate new user information
    # so we are using api for test data generation

  Scenario: books table
    Given new "User3" is added using API the add_user endpoint
    And User is on Library login "qa1_url" page
    When I login as the "User3" created using add_user endpoint
    Then User is on "#books" page
    When User finds book by name "Loralee"
    And Create "Book0" Pojo from books table first row
    Then I verify "Book0" information
      | name   | Loralee      |
      | author | Ellis Kemmer |
      | year   | 2014         |



# UI: login as some one
# UI:  open any book may be this one: The kite runner
# DB: get book id from books DB with given book info
# UI, API:   verify that book information matches the response from  /get_book_by_id/{id}  endpoint
  @db @api
  Scenario: verify book information using get_book_by_id endpoint
    Given User is on Library login "qa1_url" page
    And User login as a "librarian"
    And  User is on "Books" module
    When User finds book by name "I Will Fear No Evil"
    And User picks book by Author "Nicky DuBuque"
    And Create "Book1" Pojo from books table first row
    Then New "Book1" is found in books Data Base update id
    And New "Book1" is match in books by id from API