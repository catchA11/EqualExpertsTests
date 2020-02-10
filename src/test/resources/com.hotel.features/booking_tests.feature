Feature: Hotel booking scenarios

  Scenario: create valid booking
    Given a default booking with valid data is entered
    When the record is saved
    Then the booking record is displayed on the page

  Scenario: delete booking
    Given a booking record has been created
    When the record is deleted
    Then the booking record is not displayed on the page

  Scenario Outline: create valid bookings
    Given a default booking is entered with '<field>' set to '<value>'
    When the record is saved
    Then the booking record is displayed on the page
    Examples:
      | field                    | value |
      | Deposit Paid             | true  |
      | Deposit Paid             | false |
      | Check In Days From Today | 0     |
      | Price                    | 0     |
      | Price                    | 99.99 |

