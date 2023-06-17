package com.marriott.booking.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "creditCards")
public class CreditCard {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String creditCard_name;

    @NotBlank
    private String bookRef;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "creditCard")
    private List<Reservation> reservations;

    @Enumerated(EnumType.STRING) // Using EnumType.STRING to store the status as a string in the database
    private CreditCardStatus status; // New field for status

    public CreditCard() {
        super();
    }

    public CreditCard(Long id, String creditCardName, String bookRef, LocalDate startDate, LocalDate endDate, CreditCardStatus status) {
        this.id = id;
        this.creditCard_name = creditCard_name;
        this.bookRef = bookRef;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreditCard_name(String creditCard_name) {
        this.creditCard_name = creditCard_name;
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

    public void setStatus(CreditCardStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getCreditCard_name() {
        return creditCard_name;
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

    public CreditCardStatus getStatus() {
        return status;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
