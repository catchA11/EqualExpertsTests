Feature: Hotel booking scenarios

  Scenario: create valid booking
    Given a default booking with valid data is entered
    When the record is saved
    Then the booking record is displayed on the page

  Scenario: delete booking
    Given a booking record has been created
    When the record is deleted
#    Then the booking record is not displayed on the page

