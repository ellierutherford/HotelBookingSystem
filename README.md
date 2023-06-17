# HotelBookingSystem
Web app for UCD COMP47910

You will need to install IntelliJ + MySQL.

Once you have these installed, you will need to create a database called "hotelBookingSystem" and a table called "bookings" in this database. 
Use the following commands to connect to mysql, create the db, table and populate it with some information.
```
mysql -u root -p 
# When prompted, supply your password


CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';
GRANT ALL PRIVILEGES ON hotelBookingSystem.* TO 'springuser'@'localhost';


# create db + switch to it
CREATE DATABASE hotelBookingSystem;
USE hotelBookingSystem;

# create a table called bookings - doesn't have all necessary fields, it's just to get set up
CREATE TABLE bookings(id INT NOT NULL AUTO_INCREMENT, guestFirstName VARCHAR(20) NOT NULL, guestLastName VARCHAR(20) NOT NULL, PRIMARY KEY (id));

# populate with some data
INSERT INTO bookings(guestFirstName, guestLastName) VALUES ("Matt", "McGee");
INSERT INTO bookings(guestFirstName, guestLastName) VALUES ("Clodagh", "Durkan");
INSERT INTO bookings(guestFirstName, guestLastName) VALUES ("Eleanor", "Rutherford");

# check it's populated correctly
select * from bookings;

# commit if happy
commit;
```

Once your DB is up and running, replace the password place holder in the "src/main/resources/application.properties" file to your MySQL password.

Now you are ready to run the project from IntelliJ and navigate to http://localhost:8080/ to try it out.


----
Web Application Development
Marriott International is an American multinational diversified hospitality company that manages and franchises a broad portfolio of hotels and related lodging facilities.

Marriott plans to provide a new web reservation system to increase its business revenues. Marriott has recently hired your team to work on the new reservation system that will be released on July 2nd.

The Marriot Web Application should include the following functionalities:
Show available rooms: users should be able to view available rooms between specific dates and for a certain number of guests.

Guest users can book one or more available rooms. Allow users to book available rooms as guests. To do so, each user should provide their details (name, surname, address, phone number, email address). When booking a room, a user must provide their credit card number to terminate the booking.

Guest users can retrieve their past reservations. Guest users should be able to retrieve their reservations and cancel them within 24 hours from the check-in date. Reservation should indicate the rooms booked, the guest names (if necessary) and the dates for which the room was booked.

Users can register as Starwood members: Users can register by providing their details (name, surname, address, phone number, email address). They will then create a username and password that will be used for future logins.

Starwood users can login and logout from the system.

Show available rooms to Starwood members:  users who successfully log in can book available rooms at discounted prices (10%).

Starwood users can book one or more available rooms. Allow users to book available rooms as Starwood members. To do so, the user will only have to select the credit card that’s/he wants to use for payment.

Starwood members can save their credit card details and also modify their details. After a user successfully logs in, Starwood members can save their credit card details (more than a credit card can be saved).

Starwood members can visualize a history of their reservations: after a user performs the login successfully, Starwood members can visualize their reservations and their status (Active, Cancelled, Inactive). If an active reservation is selected, the user can cancel it.

Starwood members can delete their registration.


Your web application requires a database to include user’s personal information, credit card details, credentials, reservations, hotel rooms, and availability. You can use MySQL as a database management system.

You are required to work on this assignment collaboratively in groups of 3.
I recommend that each group uses a distributed version repository (e.g., Git) to submit the WebApp. Inside your project, you should provide a diary documenting each team member's contribution.


