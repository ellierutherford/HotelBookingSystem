package com.marriott.booking.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
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
    //one book has one or more guests
    @OneToMany(mappedBy = "booking")
    private List<Reservation> Reservations;

    public Booking() {
        super();
    }

    public Booking(Long id, String booking_name, String bookRef) {
        this.id = id;
        this.booking_name = booking_name;
        this.bookRef = bookRef;
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

    public Long getId() {
        return id;
    }

    public String getBooking_name() {
        return booking_name;
    }

    public String getBookRef() {
        return bookRef;
    }

    public List<Reservation> getReservations() {
        return Reservations;
    }



    public void setReservations(List<Reservation> Reservations) {
        this.Reservations = Reservations;
    }
}
