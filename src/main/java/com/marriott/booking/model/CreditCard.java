package com.marriott.booking.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "creditCards")
public class CreditCard {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String cardHolderName;

    @NotBlank
    private Long userId;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private Date expiry;

    @NotBlank String cvv;

    @Enumerated(EnumType.STRING) // Using EnumType.STRING to store the status as a string in the database
    private CreditCardStatus status;

    public CreditCard() {
        super();
    }

    public CreditCard(Long id, Long userId, String cardHolderName, String cardNumber, Date expiry, String cvv, CreditCardStatus status) {
        this.id = id;
        this.userId = userId;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(CreditCardStatus status) {
        this.status = status;
    }

    public CreditCardStatus getStatus() {
        return status;
    }

    public String getCardHolderName(){ return this.cardHolderName; }

    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }

    public String getCvv() { return this.cvv; }

    public void setCvv(String cvv) { this.cvv = cvv; }

    public Date getExpiry() { return this.expiry; }

    public void setExpiry(Date expiry) { this.expiry = expiry; }

    public String getCardNumber() { return this.cardNumber; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public Long getUserId() { return this.userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}
