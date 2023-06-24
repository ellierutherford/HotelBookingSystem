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

    @OneToMany(mappedBy ="roomasset", cascade = CascadeType.ALL)


    @NotBlank
    private int roomasset_number;

    public RoomAsset(){
        super();
    }
    public RoomAsset(Long id, int roomasset_number, String roomasset_name) {
        super();
        this.id = id;
        this.roomasset_number = roomasset_number;
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
    public void setroomasset_name(String guest_first_name) {
        this.roomasset_name = roomasset_name;
    }


    public int getroomasset_number() {
        return roomasset_number;
    }
    public void setroomasset_number(int roomasset_number) {
        this.roomasset_number = roomasset_number;
    }








}
