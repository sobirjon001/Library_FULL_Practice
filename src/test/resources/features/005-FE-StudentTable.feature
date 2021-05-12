Feature: 5. As a students, I should be able to see tables with default info

  Background: User is already on Library login page and Users module
    Given User is on Library login "qa1_url" page
    And User login as a "student"

  Scenario: Table columns names
    Then User should see the following table column names:
      | Actions     |
      | ISBN        |
      | Name        |
      | Author      |
      | Category    |
      | Year        |
      | Borrowed By |

  Scenario Outline: testing search by category
    When User selects a "<category>" from search by category dropdown
    Then Books table should show books by "<category>"
    Examples: some books categories to try our search:
      | category                |
      | Anthology               |
      | Action and Adventure    |
      | Classic                 |
      | Biography/Autobiography |
      | Poetry                  |
      | Historical Fiction      |

  Scenario: borrowing books table columns names
    When User is on "Borrowing Books" module
    Then User should see the following table column names:
      | Action              |
      | Book Name           |
      | Borrowed Date       |
      | Planned Return Date |
      | Return Date         |
      | Is Returned ?       |