package com.marriott.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roomtypes")
public class RoomType {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String room_name;

    public RoomType(){
        super();
    }
    public RoomType(Long id, String room_name) {
        super();
        this.id = id;
        this.room_name = room_name;
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



}
