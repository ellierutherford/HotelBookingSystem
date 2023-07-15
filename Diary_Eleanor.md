My contributions to this project. 

In terms of first set up, I first created the bare bones template project in repo 
and created an entity relationship diagram to get sense of data model. Matt then did a lot of foundation work on data model which I leveraged and modified a bit to fit the brief.

The summary of functionality (backend + frontend) I completed is;

- Created queries and repository methods for retrieving only available rooms 
- Added search page so user can search for available rooms for given date range and number of guests
- Added functionality to allow an anon user to book a room from list of available rooms and use provided customer details to create customer object.
- Added register functionality to allow people to register as Starwood users
- Added login functionality using Spring security
- Added logout functionality using Spring security
- Conditionally displayed room price depending on whether user is logged in or not. 
- Enabled Starwood users to book a room by choosing an existing credit card (if they had one) or adding a new card if not.
- Added "find reservation" functionality for all users, allowing users cancel bookings if more than 24 hours ahead of checkin.
- Added a "members area" Starwood members where they can
  - visualize a history of their reservations, with the option to cancel active bookings.
  - cancel their registration if they wish.
  - add and modify credit card details outside of the booking flow.
- Locked down any admin only pages to users logged in with role "admin" using Spring security

I've tidied up this journal entry for the sake of brevity. For the unabridged version with my daily updates, TODO lists and notes, please see the git history.


