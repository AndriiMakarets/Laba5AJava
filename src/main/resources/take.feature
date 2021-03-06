Feature: Customer took the book

  Scenario: Customer took the book
    Given a book with the title 'The Hobbit', written by 'J. R. R. Tolkien', published in '21-09-1937'
    And a book with the title 'A Confederacy of Dunces', written by 'John Kennedy Toole', published in '01-04-1980'
    And a book with the title 'A Game of Thrones (A Song of Ice and Fire)', written by 'George R.R. Martin', published in '01-08-1996'
    When the customer took the book with the title 'The Hobbit', written by 'J. R. R. Tolkien', published in '21-09-1937'
    Then 2 books should have been found
    And Book 1 should have the title 'A Confederacy of Dunces'
    And Book 2 should have the title 'A Game of Thrones (A Song of Ice and Fire)'