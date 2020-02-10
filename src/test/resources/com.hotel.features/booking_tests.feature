Feature: Hotel booking scenarios

  Scenario: create valid booking
    Given a default booking with valid data is entered
    When the record is saved
    Then the booking record is displayed on the page


