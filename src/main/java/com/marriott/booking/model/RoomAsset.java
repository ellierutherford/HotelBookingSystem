package com.marriott.booking.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "roomAssets")
public class RoomAsset {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String roomAsset_first_name;
    @NotBlank
    private String roomAsset_last_name;

    @OneToMany(mappedBy ="roomAsset", cascade = CascadeType.ALL)
    Set<Reservation> reservedRooms;

    @NotBlank
    private int roomAsset_number;

    public RoomAsset(){
        super();
    }
    public RoomAsset(Long id, int roomAsset_number) {
        super();
        this.id = id;
        this.roomAsset_number = roomAsset_number;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int roomAsset_number() {
        return roomAsset_number;
    }
    public void roomAsset_number(int roomAsset_number) {
        this.roomAsset_number = roomAsset_number;
    }





}
