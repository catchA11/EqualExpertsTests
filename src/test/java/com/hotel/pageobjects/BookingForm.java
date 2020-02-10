package com.hotel.pageobjects;

import com.hotel.BookingRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.hotel.steps.StepDefs.driver;

public class BookingForm {
    private static final String FIRST_NAME_TEXT_BOX_ID = "firstname";
    private static final String LAST_NAME_TEXT_BOX_ID = "lastname";
    private static final String PRICE_TEXT_BOX_ID = "totalprice";
    private static final String DEPOSIT_PAID_TEXT_BOX_ID = "depositpaid";
    private static final String CHECK_IN_TEXT_BOX_ID = "checkin";
    private static final String CHECK_OUT_TEXT_BOX_ID = "checkout";
    private static final String SAVE_BUTTON_ID = "input[value*='Save']";

    private static final Logger log = LoggerFactory.getLogger(BookingForm.class);

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

    public String getRecordId(String recordText) {
        String rowId = "";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            rowId = wait.until((ExpectedCondition<String>) webDriver -> findRecord(driver, recordText));
        } catch (TimeoutException e) {
            log.info("Record " + recordText + " not found on page");
        }

        return rowId;
    }

    private String findRecord(WebDriver driver, String recordText) {
        List<WebElement> rows = getAllDisplayedBookings(driver);

        return searchRowsForRecord(recordText, rows);
    }

    public List<WebElement> getAllDisplayedBookings(WebDriver driver) {
        List<WebElement> displayedBookings = driver.findElements(By.className("row"));
        int headerRow = 0;
        int dataEntryRow = displayedBookings.size() - 2;

        displayedBookings.remove(headerRow);
        displayedBookings.remove(dataEntryRow);
        return displayedBookings;
    }

    private String searchRowsForRecord(String recordText, List<WebElement> rows) {
        String rowId = null;
        for (WebElement item : rows) {
            String label = item.getText();

            if (label.equals(recordText)) {
                rowId = item.getAttribute("id");
                break;
            }
        }
        return rowId;
    }
}
