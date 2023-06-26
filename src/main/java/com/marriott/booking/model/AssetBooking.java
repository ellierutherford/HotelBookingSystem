package com.marriott.booking.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "assetbookings")
public class AssetBooking {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    // Many instances of AssetBooking can be associated with a single instance of RoomAsset
    @JoinColumn(name = "roomasset_id")
    private RoomAsset roomAsset;

    @NotBlank
    private LocalDate assetbooking_date;

    public AssetBooking() {
        super();
    }

    public AssetBooking(RoomAsset roomAsset, LocalDate assetbooking_date) {
        super();
        this.id = id;
        this.assetbooking_date = assetbooking_date;
        this.roomAsset = roomAsset;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAssetbooking_date() {
        return assetbooking_date;
    }

    public void setAssetbooking_date(LocalDate assetbooking_date) {
        this.assetbooking_date = assetbooking_date;
    }

    public RoomAsset getRoomAsset() {
        return roomAsset;
    }

    public void setRoomAsset(RoomAsset roomAsset) {
        this.roomAsset = roomAsset;
    }
}