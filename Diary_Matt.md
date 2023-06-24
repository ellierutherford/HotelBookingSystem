# Matt Diary

Incorporated the experiments I was doing with the original assignment and extending them into
the project. The books are now bookings and the Authors are guests. There is no need to work with SQL except to
setup the database and add the user.

//SQL
CREATE DATABASE hotelBookingSystem;
USE hotelBookingSystem;

-- Create a new user
/*CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';*/

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON hotelBookingSystem.* TO 'springuser'@'localhost';

-- Flush privileges to apply the changes
FLUSH PRIVILEGES;


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
//LUHN validation on the front end, or now credit card API sandbox? Stripe?


//must be provided, note we don't need to do any charging. need SSL, encrypted table, see how to do in spring.

Guest users can retrieve their past reservations. 
//Filter on reservation enum type inactive and user ID order by most recent first.

Guest users should be able to retrieve their reservations and cancel them within (//assume LP means 'up to' 24 hours from the check-in date). 
Change status to canceled WHERE utcnow +24hrs < reservation

Reservation should indicate the rooms booked, the guest names (if necessary) and the dates for which the room was booked.

Users can register as Starwood members: Users can register by providing their details (name, surname, address, phone number, email address). They will then
//then after being sent an email to finish off registrations to verify.

create a username and password that will be used for future logins.

Starwood users can login and logout from the system.
//add logging, triple the response time for every wrong password in the front end, log wrong passwords

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
types of room
    double, twin,suite........
    sleeps (number of people)
    hasCot
    FK rate
    FK Room Assets
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
//moved this to diary.


to 29th working through removing the Composite ID from my library App while preserving the 
extra features (front end validation etc). Building familiarity with moving Arrays in and out.
and noted more simplicity and is possible to remove my SQL statement
to deal with creating relations. Syntax becomes more terse and code easier to 
handle. Will bring this in to the hotel code and remove the composite ID, as per lecture technique, 
will keep to the sketched up details in the Ellie's data model documentation, until we can meet later.

----

Working on with renamed class Rooms to RoomAssets, I reflect on the dangers of not fully
addressing in my code, ambiguity and leaving related classed undefined. 

Clodagh is taking on automated testing, 
which will be handy as things get more complex.

I wonder what the data model given to the class will look like when compared to the one arrived at iteratively 
in this project, and if we'll end up changing things. 

My instinct is that the Dependency Injection Layer 
in the controller should decouple things so we have 
huge flexibility in building custom views.
I'll know if this is true after I attempt a build the strangers' interface, which should pull together
1. New Guest 
2. New Booking
3. Existing Room Types and corresponding number of free roomAssets of that Type
----
left out of this for now
Payments etc



----

