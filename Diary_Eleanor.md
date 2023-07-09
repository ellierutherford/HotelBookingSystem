Starting diary again on fresh branch.

So far;

- Set up bare bones template project in repo
- Created entity relationship diagram to get sense of data model
- Created queries and repository methods for retrieving only available rooms 
- Added search page so user can search for available rooms for given date range and number of guests;
  - TRY IT OUT: navigate to http://localhost:8080/search. Note you will have to populate the room type and room asset tables first!
- Allow a user to book a room from list of available rooms and use provided guest details to create guest object.
  (Verified that if a room has already been booked using this flow, it won't show up as an available room within that date range for the next user.)
- Added register functionality to allow people to register as Starwood users
  - TRY IT OUT: navigate to http://localhost:8080/register
- Added login functionality. Now a user can register and then login to the web app (using Spring security in the background)
  - TRY IT OUT: navigate to http://localhost:8080/register and then to http://localhost:8080/login and login using the credentials you just created.
  - I have set up the project such that every page is accessible regardless of whether the user is logged in or not.
  Next step is to change the jsp based on whether the user is logged in or not (e.g. show discounted prices if logged in)

So what have I covered from the assignment brief?

- Show available rooms between specific dates and for a certain number of guests - DONE (some UI work may be needed)
- Guest users can book one or more available rooms, provide details + credit card:
  - Guest users can only book ONE room. TODO: add capability to book more than one. Need to dig into Matt's implementation of Registration vs Booking to do this.
  - Guest users must provide name, surname, address, phone number, email address: DONE. Nice to have would be to add validation for all this input
  - TODO: Guest users must provide credit card details
- Users can register as Starwood members;
  - Users can register by providing name, username and password - DONE. TODO: split the flow into two - step 1: user provides ALL details (phone etc as well), step 2: user creates username and password
  - TODO: this is currently horribly insecure. Need to encrypt password/use hash + salt
- Starwood users can login and logout from the system
  - Registered users can login
  - TODO: users can logout

NOTE: I had to make changes to the db schema to implement the above, so now some of Matt's code doesn't work. I commented out for now, but will tidy up and fix tomorrow. 
We need to start rationalising and tidying up the code, I've added so much mess to it!! I'll try unify our approaches and write up a summary tomorrow. I WILL NOT MERGE THIS BRANCH INTO MAIN UNTIL THAT'S DONE or otherwise I will break all of Matt's stuff!
I also want to work on the TODOs listed above, plus starting on the login functionality. 

Update 09/07 - didn't get a chance to do any tidy up really, I just focussed on login functionality today. Was a bit of a slog, but finally have the login working. Next step for me - get logout working. 

