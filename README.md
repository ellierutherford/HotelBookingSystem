# HotelBookingSystem
Web app for UCD COMP47910

First, please install IntelliJ + MySQL.

Then, open a terminal/command line and run 'mysql -u <USERNAME> -p', and enter your SQL password when prompted.

Now, run the "setupDb.sql" file found in the root of this repo by running "source setupDb.sql" in the mysql prompt. (You will have to provide the full path to this sql file.) 
This file creates a new sql user, creates the database and grants the newly created user permissions on the database.

Next, run the "populateDb.sql" file, also found in the root of this repo by running "source populateDb.sql". This set of commands 
populates the database with some hotel rooms so you can start booking rooms!

Once these are run, please open the project in IntelliJ and run it. THERE IS NO NEED TO MAKE ANY UPDATES TO THE application.properties file. All necessary updates are already made.

All going well, the app will now be running at http://localhost:8080!

Troubleshooting;

1. Make sure you don't already have a user called 'springuser' set up. If you do, please delete it, or else run the commands found in setupDb.sql individually and leave out creation of the user again.
2. Likewise with the database - if you already have a database called 'hotelbookingsystem', just drop it and start again.
