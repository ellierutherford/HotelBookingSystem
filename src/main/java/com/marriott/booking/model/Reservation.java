package com.marriott.booking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;
@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE) /*Need this for deleting the Reservation references*/
    @JoinColumn(name = "booking")
    private Booking booking;

    @ManyToOne()
    @JoinColumn(name = "guest")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "id_creditCard")
    private CreditCard creditCard;




    public Reservation(){
        super();
    }
    public Reservation(Booking booking, Guest guest) {
        super();
        this.guest = guest;
        this.booking = booking;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }






}
