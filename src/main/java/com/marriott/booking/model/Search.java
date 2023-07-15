package com.marriott.booking.model;

import java.time.LocalDate;

public class Search {
    private LocalDate startDate;

    private LocalDate endDate;

    private int numGuests;

    public Search() {
        super();
    }

    public Search(LocalDate startDate, LocalDate endDate, int numGuests) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.numGuests = numGuests;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNumGuests() { return this.numGuests; }

    public void setNumGuests(int numGuests) { this.numGuests = numGuests;}


}
