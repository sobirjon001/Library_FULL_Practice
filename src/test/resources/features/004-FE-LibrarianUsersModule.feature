Feature: 4. As a librarian, I should be able to see book records on user page

  Background: User is already on Library login page and Users module
    Given User is on Library login "qa1_url" page
    And User login as a "librarian"
    When User is on "Users" module

  Scenario: verify that the default record is 10
    Then Default records dropdown value is 10

  Scenario: Show records for count options
    And User clicks "Show " dropdown
    Then User should see the following dropdown options:
      | 5   |
      | 10  |
      | 15  |
      | 50  |
      | 100 |
      | 200 |
      | 500 |

  Scenario: verify user categories
    And User clicks "User Group" dropdown
    Then User should see the following dropdown options:
      | ALL       |
      | Librarian |
      | Students  |

  Scenario: verify user status
    And User clicks "Status" dropdown
    Then User should see the following dropdown options:
      | ACTIVE   |
      | INACTIVE |

  Scenario: user management table columns names
    Then User should see the following table column names:
      | Actions   |
      | User ID   |
      | Full Name |
      | Email     |
      | Group     |
      | Status    |