package com.engineer.library.service;

import java.time.LocalDate;

public class GuestBookCreateRequest {
    private LocalDate dateToReturn;

    public GuestBookCreateRequest() {
    }

    public LocalDate getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(LocalDate dateToReturn) {
        this.dateToReturn = dateToReturn;
    }
}
