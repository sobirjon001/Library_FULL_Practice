Feature: 6. As a Student I'm able to see borrowed books and DB updated

  @db
  Scenario Outline: Students should have access to 2 modules
    Given User is on Library login "qa1_url" page
    And User login with credentials "<user name>" and "<password>":
    When User finds book by name "<bookName>"
    And User picks book by Author "<bookAuthor>"
    Then Book name "<bookName>" Author "<bookAuthor>" is added to Data Base of user "<email>"
    Examples:
      | email              | password | bookName            | bookAuthor    |
      | student133@library | jrlJEWT0 | I Will Fear No Evil | Nicky DuBuque |