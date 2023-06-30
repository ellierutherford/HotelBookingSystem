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
24/6/2023
Start the build out of the new guest interface, based on the 'Add Booking' from Admin UI.
removing the menu items, and reseting the things I send to the view completely, because I want to be careful 
about what I put out there, and do it bit by bit. THis is an alternative to planning a whole load of stuff. Start with nothing
each time in the pres layer and add in as little as possible to make it meet the bare function.
Hackers were messing with my coding session, they have found point 8080 where I left it open the other day for the team meet and send
funny looking things that perhaps webgoat might explain later when we hack at it.

Enough diarising, back to it....


---------------------

working on edit booking but tired, so done, and edit not working.
http://localhost:8080/experiment
is working though, so an anon can make booking.
Lots of logging in the console now too. We can confidently build out the data model and continue in the 
way to build function, as handing the anon in the Controller layer has shown the decouping power of Dependency Injection. Note we not have a lot in the interfaces in today's work in this branch. I haven't really used it since the Composite ID phase, but I will need it in anything one controller needs to talk to another on, so have that to look forward to on a fresh brain tomorrow.

----------------------

I have going to work on building interfaces like yesterday over the basics of the CRUD 
and build function from tools in the lecture. I'm open to my work being refactoring, but I am just going 
to work in spring to see what I can get. It doesn't seem, to be a problem to try something out
in the data model and roll back, I'm going with 
the endpoints I need and trusting to the flexibility change anything I need to. Once familiarity 
with DI is built following MVC the feature velocity should build.

This MVC gives us options on what we put out and take in and put out and process and redirect. 
One solid datamodel, build out a bit a at time is what this is for, and it will give a 
working structure that is more complex that anything we can specify.

THe focus should be on what feature is possible next, and build it so it makes sense, refactor it when you need too
it's too small and low data volume to be any real trouble, the risk is we don't product enough basic function.


--------

working on roomassets, seems to be coming along. Will take a break and view the other branches for interest. Dev Team 2/3 up and running 
in the development env yet so it looks like testing might not be on the cards just yet.

---------

Setting myself the objective of getting a booking through the reservation process through the multistage screens, which involves CRUD operations at each step in the controller,
and is skillbuilding in terms of moving operations through the controller and views. I am relying on the Spring boot features to handle the data model, and I validate the
refined datamodel from the function I am getting. So far since there is DI from controller level and REST on the data structures, these are both my constraints and the reason
function can be built out. Once I have some basic pricing for roomstypes and availability behaviour across room assets I will stop to tidy up and make some tests

-------------
An architecture is a set of constraints according to the REST author, and these are helpful for building persistent
object state when CRUD is used to operate them. The DI means that things are easy to change and address in the controller
the main challenge for me has been to build familiarity with the operations. I'm looking at the the booking being 
inside the reservation and wondering how much do I need to worry about building into a corner. 

Here are the one I've made for myself Bookings are of assets that are of types, and I'm working thru marchalling them in an 
unsmelly way in the anon controller route. So far I haven't needed to leave spring, I'll know if the approach works if I can 
build the availability on assets presented as RoomType as a super-header. 

-----

THis is going great, the room Assets and types make sense now and we're ready to consider when they can be booked.
Getting a record down now of when the rooms are occupied. Had to make one more interface, but nothing too bad. Very happy 
with the day's progress.
-----

Ellie has done a query for availability, which I made an ID -> object tweak to for readability which tests well, see console output as we make 
one booking, and then do a query that doesn't cross the dates and then does. Note this testing was done with one room type and asset
in the system.

1 Welcome page List these bookings[] and this many registered guests 1
Setting bookingRoomAsset toOne as matches request and is believed to be available.
Added Reserved Booking Date: 2023-06-26
Added Reserved Booking Date: 2023-06-27
Added Reserved Booking Date: 2023-06-28
4 Added guest 1 to booking 1 on room assetcom.marriott.booking.model.RoomAsset@708e646b
5 I'm post save of booking G1_26_FirstName and guests 
1 Welcome page List these bookings and this many registered guests 1
1a createStrangerBooking Form displayed
New unknown booker with Name: G2_29_FN G2_30_LN .
Booking Startdate: 2023-06-29.
Booking Enddate: 2023-06-30.
Found this roomAsset One and type: com.marriott.booking.model.RoomType@77a146a as a potential for booking.
3a Save Created New Guest: 2With first name G2_29_FNWith last name .G2_30_LN
New unknown booker with Name: G3_29_FN G3_30_LN .
Booking Startdate: 2023-06-26.
Booking Enddate: 2023-06-28.
3a Save Created New Guest: 3With first name G3_29_FNWith last name .G3_30_LN

Availability only needs to work from booking at the moment although there will doubtless be
backend views that require it, but I will the endpoint leave this part as Ellie's scope, as she has indicated non working
but I find the query good so far. . Notes on git management,
the features in Intellj are excellent for git, and I am gaining confidence in group participation with this.
It is easy to put the decision about ID vs object on the backburner, it's a two min tweak to make
the transtion as it comes in.

Looking at the unit testing options which become more pressing as the complexity builds.

Need to check with Ellie re intent behind Ids, maybe is a security or performance thing I don't know about.


------

28/6 a few days to the orginal deadline, breaking out the problem of the availability. If Ellie's query can be made to work we 
don't need my table of assets and dates, it would be tidier, so forget the AssetBooking class and see what's happening with the 
query by breaking it into steps.

Successful in spotting other queries on same start date

------

Got a rough availability check going on ELlie's model of checking the booking table, rather than extra AssetBooking controller. When the 
terse elegant query is returning available rooms reliably, we can tidy up the bloated Booking Controller. 
Now it gives a v dirty error page when you try an existing resource.

Next - undo the bloat......

---------------

End of the day notes on 28/06, some good progress here now, going to look at adding other bookings to reservation in the morning, 
thinking there needs to be a option in a page to add another booking to the reservation before progressing to payment page.

I've left notes in the controller where I've spend time debugging as I shift from a list to an item on the next 
page, and it should help speed things up as the features are developed.

We also need to error handle the attempted bookings where a room is not available as well remove type options from the
dropdown where there are no available room assets of that type.


--------------

Ellie has rewritten the availability query with the roomtype parameter and it works, time to merge

--------------

Stepped back from development to work through zapping the Goat, and is it is fun.
Took a while to get going , but run it on another machine with a hostsfile entry and change the localhost entry to 0.0.0.0 on the server machine when starting the goat. On the workstation run the Windows manual Proxy server on 127.0.0.1 8100, and hook up Zap as per lecture. Point Firefox at the Windows manual entry and it works.
Clarified on json payload and how to constructe one and did the 1st 2 exercises. Need encryption on our sessions!