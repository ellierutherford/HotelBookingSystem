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

    @NotBlank
    private String guest_address;
    @NotBlank
    private String guest_phone_number;
    @NotBlank
    private String guest_email;

    public Guest(){
        super();
    }
    public Guest(Long id, String guest_first_name, String guest_last_name,
                 String guest_address, String guest_phone_number, String guest_email) {
        super();
        this.id = id;
        this.guest_first_name = guest_first_name;
        this.guest_last_name = guest_last_name;
        this.guest_address = guest_address;
        this.guest_phone_number = guest_phone_number;
        this.guest_email = guest_email;
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

    public String getGuest_address(){ return guest_address; }
    public void setGuest_address(String guest_address) { this.guest_address = guest_address; }

    public String getGuest_phone_number(){ return guest_phone_number; }

    public void setGuest_phone_number(String guest_phone_number) {this.guest_phone_number = guest_phone_number; }

    public String getGuest_email() { return guest_email; }

    public void setGuest_email(String guest_email) { this.guest_email = guest_email; }

}
