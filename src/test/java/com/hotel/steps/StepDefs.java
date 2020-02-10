package com.hotel.steps;

import com.hotel.BookingRecord;
import com.hotel.BrowserDriver;

import com.hotel.pageobjects.BookingForm;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefs {
    BrowserDriver browserDriver = new BrowserDriver();
    BookingForm bookingForm = new BookingForm();
    public static WebDriver driver;
    private BookingRecord bookingRecord = new BookingRecord();

    @Before
    public void openHotelBookingForm() {
        String PATH = "http://hotel-test.equalexperts.io";

        driver = browserDriver.getBrowserDriver();
        driver.get(PATH);
    }

//    @After
    public void cleanUp() {
        browserDriver.closeBrowser();
    }

    @Given("^a default booking with valid data is entered$")
    public void createValidBookingRecord() {
        bookingForm.loadBookingRecord(driver, bookingRecord);
    }

    @When("^the record is saved$")
    public void saveRecord() {
        bookingForm.clickSaveButton();
    }

    @Then("^the booking record is displayed on the page$")
    public void verifyBookingRecordIsFoundOnThePage() {
        String expectedBookingRecord = assembleExpectedBookingRecord();
        assertThat(bookingForm.getRecordId(expectedBookingRecord))
                .withFailMessage("Expected booking record not found on page")
                .isNotNull();
    }

    private String assembleExpectedBookingRecord() {
        return this.bookingRecord.getFirstName() + "\n" + this.bookingRecord.getLastName() + "\n" + this.bookingRecord.getTotalPrice()
                + "\n" + this.bookingRecord.getDepositPaid() + "\n" + this.bookingRecord.getCheckInDate() + "\n" + this.bookingRecord.getCheckOutDate();
    }

    @Given("^a booking record has been created")
    public void createBooking() {
        bookingForm.loadBookingRecord(driver, bookingRecord);
        bookingForm.clickSaveButton();
        String rowId = bookingForm.getRecordId(assembleExpectedBookingRecord());
        if (rowId == null | rowId.isEmpty()) {
            throw new IllegalStateException("record not successfully created");
        }
    }

    @When("^the record is deleted$")
    public void deleteBooking() {
        String rowId = bookingForm.getRecordId(assembleExpectedBookingRecord());
        if (rowId != null) {
            bookingForm.clickDeleteButton(rowId);
        } else {
            throw new IllegalStateException("Cannot delete record, record not found on page");
        }
    }

    @Then("^the booking record is not displayed on the page$")
    public void verifyBookingRecordIsNotFoundOnThePage() {
        String expectedBookingRecord = assembleExpectedBookingRecord();
        assertThat(bookingForm.getRecordId(expectedBookingRecord))
                .withFailMessage("Invalid booking record was found on page")
                .isEmpty();
    }
}
