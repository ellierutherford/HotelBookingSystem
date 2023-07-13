package com.marriott.booking.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private Long guest_id;

    @Column(name = "bookingRef")
    private String bookingRef;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "num_guests")
    private int numGuests;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne() //is this needed?
    @JoinColumn(name = "roomasset")
    private RoomAsset roomasset;

    @ManyToOne()
    @JoinColumn(name = "card_id")
    private CreditCard card;

    public Booking() {
        super();
    }

    public Booking(Long id, Long guest_id, int numGuests, String bookingRef, LocalDate startDate, LocalDate endDate, BookingStatus status) {
        this.id = id;
        this.guest_id = guest_id;
        this.numGuests = numGuests;
        this.bookingRef = bookingRef;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public RoomAsset getRoomasset() {
        return roomasset;
    }

    public void setRoomAsset(RoomAsset roomAsset) {
        this.roomasset = roomAsset;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }

    public Long getGuest_id() { return this.guest_id; }
    public void setGuest_id(Long guest_id) { this.guest_id = guest_id; }

    public String getBookingRef(){ return this.bookingRef; }

    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }

    public BookingStatus getStatus() { return this.status; }

    public void setStatus(BookingStatus status){ this.status = status; }

    public CreditCard getCard(){ return this.card; }

    public void setCard(CreditCard card){ this.card = card;}

}