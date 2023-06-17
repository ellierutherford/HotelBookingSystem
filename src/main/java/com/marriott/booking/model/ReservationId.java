package com.marriott.booking.model;

import java.io.Serializable;
import java.util.Objects;

public class ReservationId implements Serializable {

    Long id_booking;

    Long id_guest;

    public ReservationId(){}

    public ReservationId( Long id_booking, Long id_guest){
        this.id_guest = id_guest;
        this.id_booking = id_booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationId ReservationId = (ReservationId) o;
        return id_booking.equals(ReservationId.id_booking) &&
                id_guest.equals(ReservationId.id_guest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_booking, id_guest);
    }

    public Long getId_Guest(){
        return id_guest;
    }
    public Long getId_Booking(){
        return id_booking;
    }
}
