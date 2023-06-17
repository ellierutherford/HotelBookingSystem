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

Taking a look at the question and relating to data model - for discussion
Matt - 11:30 Sat 17/06/2023

//I have written in some design notes into the assignment, and breaking out the DB model so we can discuss it.

The Marriot Web Application should include the following functionalities:
Show available rooms: users should be able to view available rooms 
between specific dates   /*In DB and front end, to be done in  controller next*/
and for a certain number of guests.

Guest users can book one or more available rooms.  
// See DB COmment 1, we need something to return the count of the available rooms

Allow users to book available rooms as guests. 
//no login, email field is compulsory
To do so, each user should provide their details (name, surname, address, phone number, email address). When booking a room, a user must provide their credit card number to terminate the booking.
//LUHN validation on the front end


//must be provided, note we don't need to do any charging. need SSL, encrypted table, see how to do in spring.

Guest users can retrieve their past reservations. 
//Filter on reservation enum type inactive and user ID order by most recent first.

Guest users should be able to retrieve their reservations and cancel them within (//assume LP means 'up to' 24 hours from the check-in date. 
Change status to canceled WHERE utcnow +24hrs < reservation

Reservation should indicate the rooms booked, the guest names (if necessary) and the dates for which the room was booked.

Users can register as Starwood members: Users can register by providing their details (name, surname, address, phone number, email address). They will then
//then after being sent an email to finish off regitrations to verify.


create a username and password that will be used for future logins.


Starwood users can login and logout from the system.
//add logging, triple the respose time for every wrong password in the front end, log wrong passwords

Show available rooms to Starwood members:  users who successfully log in can book available rooms at discounted prices (10%).

Starwood users can book one or more available rooms. Allow users to book available rooms as Starwood members. To do so, the user will only have to select the credit card that’s/he wants to use for payment.

//implies a table of credit cards, active and inactive and also delete the card

Starwood members can save their credit card details and also modify their details. After a user successfully logs in, Starwood members can save their credit card details (more than ONE?(Clarify with LP) credit card can be saved).

Starwood members can delete their registration.

*** DB requirements for discuss structure

Your web application requires a database
to include user’s 
personal information,
       name, OK
       surname, OK
       address, TO DO
       phone number, TO DO
       email address TO DO
       credentials ??(on Guest record?) TO DO
credit card details, , 
reservations, 
hotel rooms
    number, room type
types of room?
    double, twin,suite........
    sleeps (number of people)
    hasCot
    FK rate
rate 
    room date per night

availability 
    rtnCountAvailable(typeroom) returns Int
(of types of rooms, not individual rooms numbers) DB comment one. I suggest an Int return because
        requirement:
        users should be able to view available room*s*
        between specific dates, they may want more than one type
      
Requirement: You can use MySQL as a database management system. DONE
Requirement: You are required to work on this assignment collaboratively in groups of 3.
uses a distributed version repository (e.g., Git) to submit the WebApp. USING
Inside your project, you should provide a diary documenting each team member's contribution.


