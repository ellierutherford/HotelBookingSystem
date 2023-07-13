Starting diary again on fresh branch.

So far;

- Set up bare bones template project in repo
- Created entity relationship diagram to get sense of data model
- Created queries and repository methods for retrieving only available rooms 
- Added search page so user can search for available rooms for given date range and number of guests;
  - TRY IT OUT: navigate to http://localhost:8080/search. Note you will have to populate the room type and room asset tables first!
- Allow a user to book a room from list of available rooms and use provided customer details to create customer object.
  (Verified that if a room has already been booked using this flow, it won't show up as an available room within that date range for the next user.)
- Added register functionality to allow people to register as Starwood users
  - TRY IT OUT: navigate to http://localhost:8080/register
- Added login functionality. Now a user can register and then login to the web app (using Spring security in the background)
  - TRY IT OUT: navigate to http://localhost:8080/register and then to http://localhost:8080/login and login using the credentials you just created.
  - I have set up the project such that every page is accessible regardless of whether the user is logged in or not.
- Added logout functionality. TRY IT OUT: navigate to http://localhost:8080/logout. Use the code in the "test" endpoint to see what user is logged in during the session (if any).
- Conditionally displayed room price depending on whether user is logged in or not. TRY IT OUT: search for a room as logged in user vs as anonymous user and see the difference in price

So what have I covered from the assignment brief?

- Show available rooms between specific dates and for a certain number of guests - DONE
- Guest users can book one or more available rooms, provide details + credit card:
  - Guest users can only book ONE room. Will not get a chance to extend this to more than one room.
  - Guest users must provide name, surname, address, phone number, email address: DONE. Nice to have would be to add validation for all this input
  - Guest users must provide credit card details - DONE
- Users can register as Starwood members;
  - Users can register by providing name, username and password - DONE. 
  - split the flow into two - step 1: user provides ALL details (phone etc as well), step 2: user creates username and password - DONE
  - TODO: this is currently horribly insecure. Need to encrypt password/use hash + salt
- Starwood users can login and logout from the system
  - Registered users can login: DONE
  - users can logout: DONE
- Guest users can retrieve their past reservations 
  - Guest users should be able to retrieve their reservations (by ID) and cancel them - DONE 
  - within 24 hours from the check-in date - DONE 
  - Reservation should indicate the rooms booked, the customer names (if necessary) and the dates for which the room was booked. - DONE except for customer names (which sounds like a nice to have)
 

NOTE: I had to make changes to the db schema to implement the above, so now some of Matt's code doesn't work. I commented out for now, but will tidy up and fix tomorrow. 
We need to start rationalising and tidying up the code, I've added so much mess to it!! I'll try unify our approaches and write up a summary tomorrow. I WILL NOT MERGE THIS BRANCH INTO MAIN UNTIL THAT'S DONE or otherwise I will break all of Matt's stuff!
I also want to work on the TODOs listed above, plus starting on the login functionality. 

Update 09/07 - didn't get a chance to do any tidy up really, I just focussed on login functionality today. Was a bit of a slog, but finally have the login working. Next step for me - get logout working. 

Update 10/07 - logout now working, plus created a home page with links to search, login and register pages. Also added conditional display of room price depending on whether user is logged in or not. Hacky, but works for now.

Update 11/07 - updated end point for retrieving existing reservation to fulfill brief.

Update 12/07 - split booking + registration flows into two steps, plus added credit card support

Update 13/07 - allow logged in users to book rooms, fix cancel logic

NEXT - 
make sure you can't register if already logged in
show credit cards of existing user, or allow them add a new card
make the logged in UI better
make sure a logged in user can add a new card if they don't already have one saved
