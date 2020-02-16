#noinspection NonAsciiCharacters
Feature: Hotel booking scenarios

  Scenario: create default valid booking
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

  Scenario Outline: create invalid bookings
    Given a default booking is entered with '<field>' set to '<value>'
    When the record is saved
    Then the booking record is not displayed on the page
    Examples:
      | field          | value |
      | Price          | ABC   |
      | Price          | !     |
      | Price          | 1 0   |
      | Check In Date  | ?     |
      | Check Out Date | -     |

  Scenario Outline: create invalid bookings with blank mandatory fields
    Given a default booking is entered with '<field>' left blank
    When the record is saved
    Then the booking record is not displayed on the page
    Examples:
      | field          |
      | First Name     |
      | Last Name      |
      | Price          |
      | Check In Date  |
      | Check Out Date |

  Scenario Outline: create backdated booking
    Given a default booking is entered with dates '<checkInDaysFromToday>' '<checkOutDaysFromToday>'
    When the record is saved
    Then the booking record is displayed on the page
    Examples:
      | checkInDaysFromToday | checkOutDaysFromToday |
      | -2                   | -1                    |
      | -1                   | 1                     |

  Scenario Outline: enter bookings with check-in date after check-out date
    Given a default booking is entered with dates '<checkInDaysFromToday>' '<checkOutDaysFromToday>'
    When the record is saved
    Then the booking record is not displayed on the page
    Examples:
      | checkInDaysFromToday | checkOutDaysFromToday |
      | 2                    | 1                     |