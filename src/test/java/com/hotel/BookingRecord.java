package com.hotel;

import java.util.Calendar;

import static org.apache.commons.lang3.time.DateFormatUtils.format;

public class BookingRecord {
    private String firstName;
    private String lastName;
    private String price = "100";
    private String depositPaid = "true";
    private String checkInDate;
    private String checkOutDate;

    public BookingRecord() {
        String uniqueId = generateUniqueId();
        firstName = "Ann" + uniqueId;
        lastName = "Smith" + uniqueId;
        int checkInDaysFromToday = 1;
        checkInDate = getDate(checkInDaysFromToday);
        int checkOutDaysFromToday = 2;
        checkOutDate = getDate(checkOutDaysFromToday);
    }

    private String generateUniqueId() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String getDate(int daysFromToday) {
        String DATE_FORMAT = "yyyy-MM-dd";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysFromToday);
        return format(calendar.getTime(), DATE_FORMAT);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTotalPrice() {
        return price;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public String getDepositPaid() {
        return depositPaid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDepositPaid(String depositPaid) {
        this.depositPaid = depositPaid;
    }

    public void setCheckIn(int daysFromToday) {
        this.checkInDate = getDate(daysFromToday);
    }

    public void setCheckOut(int daysFromToday) {
        this.checkOutDate = getDate(daysFromToday);
    }
}

