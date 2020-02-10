package com.hotel.pageobjects;

import com.hotel.BookingRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.hotel.steps.StepDefs.driver;

public class BookingForm {
    private static final String FIRST_NAME_TEXT_BOX_ID = "firstname";
    private static final String LAST_NAME_TEXT_BOX_ID = "lastname";
    private static final String PRICE_TEXT_BOX_ID = "totalprice";
    private static final String DEPOSIT_PAID_TEXT_BOX_ID = "depositpaid";
    private static final String CHECK_IN_TEXT_BOX_ID = "checkin";
    private static final String CHECK_OUT_TEXT_BOX_ID = "checkout";
    private static final String SAVE_BUTTON_ID = "input[value*='Save']";

    public void loadBookingRecord(WebDriver driver, BookingRecord bookingRecord) {
        enterBookingFormField(driver, bookingRecord.getFirstName(), By.id(FIRST_NAME_TEXT_BOX_ID));
        enterBookingFormField(driver, bookingRecord.getLastName(), By.id(LAST_NAME_TEXT_BOX_ID));
        enterBookingFormField(driver, bookingRecord.getTotalPrice(), By.id(PRICE_TEXT_BOX_ID));
        setDepositPaid(driver, bookingRecord.getDepositPaid());
        enterBookingFormField(driver, bookingRecord.getCheckInDate(), By.id(CHECK_IN_TEXT_BOX_ID));
        enterBookingFormField(driver, bookingRecord.getCheckOutDate(), By.id(CHECK_OUT_TEXT_BOX_ID));
    }

    private void enterBookingFormField(WebDriver driver, String firstName, By field) {
        WebElement firstNameField = driver.findElement(field);
        firstNameField.sendKeys(firstName);
    }

    private void setDepositPaid(WebDriver driver, String depositChoice) {
        WebElement depositPaid = driver.findElement(By.id(DEPOSIT_PAID_TEXT_BOX_ID));
        Select dropdown = new Select(depositPaid);
        dropdown.selectByVisibleText(depositChoice);
    }

    public void clickSaveButton() {
        WebElement saveButton = getWebElement(SAVE_BUTTON_ID);
        saveButton.click();
    }

    private WebElement getWebElement(String identifier) {
        return driver.findElement(By.cssSelector(identifier));
    }
}
