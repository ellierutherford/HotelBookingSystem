package com.marriott.booking.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String leadguest_first_name;

    @NotBlank
    private String leadguest_last_name;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    Set<Reservation> guests;
    @Column(name = "bookingRef")
    private String bookingRef;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne()
    @JoinColumn(name = "roomasset")
    private RoomAsset roomasset;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToMany(mappedBy = "booking")
    private List<Reservation> reservations;

    public Booking() {
        super();
    }

    public Booking(Long id, String leadguest_first_name, String leadguest_last_name, String bookingRef, LocalDate startDate, LocalDate endDate, BookingStatus status) {
        this.id = id;
        this.leadguest_first_name = leadguest_first_name;
        this.leadguest_last_name = leadguest_last_name;
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

    public String getleadguest_first_name() {
        return leadguest_first_name;
    }
    public String getleadguest_last_name() {
        return leadguest_last_name;
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




    public void setleadguest_first_name(String leadguest_first_name) {
        this.leadguest_first_name = leadguest_first_name;
    }

    public void setleadguest_last_name(String leadguest_last_name) {
        this.leadguest_last_name = leadguest_last_name;
    }



    // Modify the setter for guests to accept a Set<Reservation>
    public void setGuests(Set<Reservation> guests) {
        this.guests = guests;
    }


}