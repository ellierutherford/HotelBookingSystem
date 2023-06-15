package com.marriott.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name= "bookings")
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String guestFirstName;

    @NotBlank
    private String guestLastName;


    public Booking() {
        super();
    }

    public Booking(Long id, String guestFirstName, String guestLastName) {
        this.id = id;
        this.guestFirstName = guestFirstName;
        this.guestLastName = guestLastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGuestFirstName(String guestFirstName) {
        this.guestFirstName = guestFirstName;
    }

    public void setGuestLastName(String guestLastName) {
        this.guestLastName = guestLastName;
    }

    public Long getId() {
        return id;
    }

    public String getGuestFirstName() {
        return guestFirstName;
    }

    public String getGuestLastName() {
        return guestLastName;
    }
}
