package com.marriott.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Currency;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roomtypes")
public class RoomType {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String room_name;

    @NotBlank
    private BigDecimal night_rate;
    private static Currency currency;

    public RoomType(){
        super();
    }
    public RoomType(Long id, String room_name, BigDecimal night_rate) {
        super();
        this.id = id;
        this.room_name = room_name;
        this.night_rate = night_rate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRoom_name() {
        return room_name;
    }
    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public BigDecimal getNight_rate() {
        return night_rate;
    }

    public void setNight_rate(BigDecimal night_rate) {
        this.night_rate = night_rate;
    }

    public static Currency getCurrency() {
        return currency;
    }

    public static void setCurrency(Currency currency) {
        RoomType.currency = currency;
    }



}
