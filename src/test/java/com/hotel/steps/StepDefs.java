package com.hotel.steps;

import com.hotel.BookingRecord;
import com.hotel.BrowserDriver;

import com.hotel.pageobjects.BookingForm;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

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


}
