@smoke @wip
Feature: 1. As a user, I should be able to login and logout in the library app.
  As a User, I should be able to login

  Background: Both scenarios have same beginning step
    Given User is on Library login "qa1_url" page

  Scenario Outline: verify both Students and librarians  login
    And User login as a "<role>"
    Then User is on "<page>" page
    Examples:
      | role      | page       |
      | student   | #books     |
      | librarian | #dashboard |

  Scenario Outline: User is able to log out
    Given User login as a "<role>"
    When User loges out from app
    Then User is on "login" page
    Examples:
      | role      |
      | student   |
      | librarian |