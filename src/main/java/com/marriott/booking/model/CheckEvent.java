package com.marriott.booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "check_events")
public class CheckEvent {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    @Enumerated(EnumType.STRING)
    private CheckEventType eventType;

    @Column(name = "title")
    private String title;

    @Column(name = "client_site")
    private String clientSite;

    @Column(name = "activity_date")
    private LocalDateTime activityDate;

    @Column(name = "checkout_time")
    private LocalDateTime checkoutTime;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "location")
    private String location;

    // Constructors, getters, and setters

    public CheckEvent() {
    }

    public enum CheckEventType {
        CHECKIN,
        CHECKOUT
    }

    public CheckEvent(
            Booking booking,
            LocalDateTime eventTime,
            CheckEventType eventType,
            String title,
            String clientSite,
            LocalDateTime activityDate,
            LocalDateTime checkoutTime,
            String activityType,
            String location
    ) {
        this.booking = booking;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.title = title;
        this.clientSite = clientSite;
        this.activityDate = activityDate;
        this.checkoutTime = checkoutTime;
        this.activityType = activityType;
        this.location = location;
    }

    // Getters and setters for additional attributes

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClientSite() {
        return clientSite;
    }

    public void setClientSite(String clientSite) {
        this.clientSite = clientSite;
    }

    public LocalDateTime getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDateTime activityDate) {
        this.activityDate = activityDate;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventType(CheckEventType eventType) {
        this.eventType = eventType;
    }

    public CheckEventType getEventType() {
        return eventType;
    }

    // Rest of the class
}
