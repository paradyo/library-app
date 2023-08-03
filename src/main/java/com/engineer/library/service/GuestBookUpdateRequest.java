package com.engineer.library.service;

import java.time.LocalDate;

public class GuestBookUpdateRequest {
    private LocalDate returnedDate;
    private LocalDate dateToReturn;

    public GuestBookUpdateRequest() {
    }

    public GuestBookUpdateRequest(LocalDate returnedDate, LocalDate dateToReturn) {
        this.returnedDate = returnedDate;
        this.dateToReturn = dateToReturn;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public LocalDate getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(LocalDate dateToReturn) {
        this.dateToReturn = dateToReturn;
    }
}
