package com.marriott.booking.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String booking_name;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    Set<Reservation> guests;
    @Column(name = "bookingRef")
    private String bookingRef;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToMany(mappedBy = "booking")
    private List<Reservation> reservations;

    public Booking() {
        super();
    }

    public Booking(Long id, String booking_name, String bookingRef, LocalDate startDate, LocalDate endDate, BookingStatus status) {
        this.id = id;
        this.booking_name = booking_name;
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

    public String getBooking_name() {
        return booking_name;
    }

    public void setBooking_name(String booking_name) {
        this.booking_name = booking_name;
    }

    // Modify the setter for guests to accept a Set<Reservation>
    public void setGuests(Set<Reservation> guests) {
        this.guests = guests;
    }




}