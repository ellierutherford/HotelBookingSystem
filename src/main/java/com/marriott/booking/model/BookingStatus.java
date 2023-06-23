/*Starwood members can visualize a history of their reservations: after a user performs the
login successfully, Starwood members can visualize their reservations and their
status (Active, Cancelled, Inactive). If an active reservation is selected,
the user can cancel it. This is a type safe way of doing things which gives us some security*/

package com.marriott.booking.model;

public enum BookingStatus {
    ACTIVE,
    CANCELLED,
    INACTIVE
}