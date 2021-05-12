@smoke
Feature: 2. As a user, I should be able to  see several modules once I login.

  Background: User is already on Library login page
    Given User is on Library login "qa1_url" page

  @student
  Scenario Outline: Students should have access to 2 modules
    Given User login with credentials "<user name>" and "<password>":
    Then User should see following modules:
      | Books           |
      | Borrowing Books |
    Examples: user name and passwords examples:
      | user name          | password |
      | student133@library | jrlJEWT0 |
      | student134@library | jVR0Ne0G |
      | student135@library | bNHbXc6f |

  @librarian
  Scenario Outline: Librarians  should have access to 3 modules
    Given User login with credentials "<user name>" and "<password>":
    Then User should see following modules:
      | Dashboard |
      | Users     |
      | Books     |
    Examples: user name and passwords examples:
      | user name           | password |
      | librarian67@library | 5ktpB2e5 |
      | librarian66@library | OPhunod4 |