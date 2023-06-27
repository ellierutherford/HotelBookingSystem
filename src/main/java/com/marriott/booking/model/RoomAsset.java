package com.marriott.booking.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "roomassets")
public class RoomAsset {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String roomasset_name;

    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private RoomType roomType;


    @NotBlank
    private int roomasset_number;

    public RoomAsset(){
        super();
    }
    public RoomAsset(Long id, int roomasset_number, String roomasset_name, RoomType roomType) {
        super();
        this.id = id;
        this.roomasset_number = roomasset_number;
        this.roomType = roomType;
        this.roomasset_name = roomasset_name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getroomasset_name() {
        return roomasset_name;
    }
    public void setroomasset_name(String roomasset_name) {
        this.roomasset_name = roomasset_name;
    }


    public int getroomasset_number() {
        return roomasset_number;
    }
    public void setroomasset_number(int roomasset_number) {
        this.roomasset_number = roomasset_number;
    }

    public RoomType getroomType() {
        return roomType;
    }
    public void setroomasset_type(RoomType roomType) {
        this.roomType = roomType;
    }
}
