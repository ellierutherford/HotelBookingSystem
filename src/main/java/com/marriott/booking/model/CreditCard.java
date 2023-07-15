package com.marriott.booking.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "credit_cards")
public class CreditCard {

    @GeneratedValue
    @Id
    Long id;

    @NotBlank
    private String cardHolderName;

    @NotBlank
    private Long userId;

    @NotBlank
    @Column(nullable = false, unique = true, length = 16)
    private String cardNumber;

    @NotBlank
    private String expiry;

    @NotBlank String cvv;

    @Enumerated(EnumType.STRING) // Using EnumType.STRING to store the status as a string in the database
    private CreditCardStatus status;

    public CreditCard() {
        super();
    }

    public CreditCard(Long id, Long userId, String cardHolderName, String cardNumber, String expiry, String cvv, CreditCardStatus status) {
        this.id = id;
        this.userId = userId;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.status = status;
    }

    public Long getId(){ return this.id; }

    public void setId(Long id){ this.id = id; }

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

    public String getExpiry() { return this.expiry; }

    public void setExpiry(String expiry) { this.expiry = expiry; }

    public String getCardNumber() { return this.cardNumber; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public Long getUserId() { return this.userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}
