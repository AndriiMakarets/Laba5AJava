Feature: Book search
  To allow customers to find books, the library must offer seek methods.

  Scenario: Search books by publication year
    Given a book with the title 'The Hobbit', written by 'J. R. R. Tolkien', published in '21-09-1937'
    And a book with the title 'A Confederacy of Dunces', written by 'John Kennedy Toole', published in '01-04-1980'
    And a book with the title 'A Game of Thrones (A Song of Ice and Fire)', written by 'George R.R. Martin', published in '01-08-1996'
    When the customer searches for books published between '1980' and '2000'
    Then 2 books should have been found
    And Book 1 should have the title 'A Game of Thrones (A Song of Ice and Fire)'
    And Book 2 should have the title 'A Confederacy of Dunces'

  Scenario: Search books by author
    Given a book with the title 'The Hobbit', written by 'J. R. R. Tolkien', published in '21-09-1937'
    And a book with the title 'A Confederacy of Dunces', written by 'John Kennedy Toole', published in '01-04-1980'
    And a book with the title 'A Game of Thrones (A Song of Ice and Fire)', written by 'George R.R. Martin', published in '01-08-1996'
    When the customer searches for books by 'J. R. R. Tolkien'
    Then 1 books should have been found
    And Book 1 should have the title 'The Hobbit'

  Scenario: Search books by title
    Given a book with the title 'The Hobbit', written by 'J. R. R. Tolkien', published in '21-09-1937'
    And a book with the title 'A Confederacy of Dunces', written by 'John Kennedy Toole', published in '01-04-1980'
    And a book with the title 'A Game of Thrones (A Song of Ice and Fire)', written by 'George R.R. Martin', published in '01-08-1996'
    When the customer searches for book 'A Confederacy of Dunces'
    Then 1 books should have been found
    And Book 1 should have the title 'A Confederacy of Dunces'



