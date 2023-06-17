package com.marriott.booking.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String booking_name;

    @NotBlank
    private String bookRef;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING) // Using EnumType.STRING to store the status as a string in the database
    private BookingStatus status; // New field for status

    @OneToMany(mappedBy = "booking")
    private List<Reservation> reservations;

    public Booking() {
        super();
    }

    public Booking(Long id, String bookingName, String bookRef, LocalDate startDate, LocalDate endDate, BookingStatus status) {
        this.id = id;
        this.booking_name = booking_name;
        this.bookRef = bookRef;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBooking_name(String booking_name) {
        this.booking_name = booking_name;
    }

    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getBooking_name() {
        return booking_name;
    }

    public String getBookRef() {
        return bookRef;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
