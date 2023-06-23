package com.marriott.booking.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String guest_first_name;
    @NotBlank
    private String guest_last_name;

    @OneToMany(mappedBy ="guest", cascade = CascadeType.ALL)
    Set<Reservation> reservedRooms;

    @NotBlank
    private String guest_address;
    @NotBlank
    private String guest_phone_number;
    @NotBlank
    private String guest_email;

    public Guest(){
        super();
    }
    public Guest(Long id, String guest_first_name, String guest_last_name) {
        super();
        this.id = id;
        this.guest_first_name = guest_first_name;
        this.guest_last_name = guest_last_name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGuest_first_name() {
        return guest_first_name;
    }
    public void setGuest_first_name(String guest_first_name) {
        this.guest_first_name = guest_first_name;
    }
    public String getGuest_last_name() {
        return guest_last_name;
    }
    public void setGuest_last_name(String guest_last_name) {
        this.guest_last_name = guest_last_name;
    }


}
