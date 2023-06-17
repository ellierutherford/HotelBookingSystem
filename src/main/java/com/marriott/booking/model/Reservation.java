package com.marriott.booking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Reservation")
@IdClass(ReservationId.class)
public class Reservation {
    @Id  /*Note there are two and both marked ID*/
    @NotNull
    private Long id_booking;

    @Id /* The second*/
    @NotNull
    private Long id_guest;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE) /*Need this for deleting the Reservation references*/
    @JoinColumn(name = "id_booking")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "id_guest")
    private Guest guest;

    public Reservation(){
        super();
    }
    public Reservation(Long id_booking, Long id_guest) {
        super();
        this.id_booking = id_booking;
        this.id_guest = id_guest;
    }

    public Long getId_booking() {
        return id_booking;
    }
    public void setId_booking(Long id_booking) {
        this.id_booking = id_booking;
    }

    public Long getId_guest() {
        return id_guest;
    }
    public void setId_guest(Long id_guest) {
        this.id_guest = id_guest;
    }



}
