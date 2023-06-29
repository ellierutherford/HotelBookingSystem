# HotelBookingSystem
Web app for UCD COMP47910

You will need to install IntelliJ + MySQL.

Once you have these installed, you will need to create a database called "hotelBookingSystem" and a table called "bookings" in this database.
Use the following commands to connect to mysql, create the db, table and populate it with some information.
```

//No need for injection into table. THis will get you going with the current Application configuration.

CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';

CREATE DATABASE `hotelbookingsystem`;
-- Grant privileges to the user
GRANT ALL PRIVILEGES ON example.* TO 'springuser'@'localhost';
-- Flush privileges to apply the changes
FLUSH PRIVILEGES;


NOTE: 'Create' Database in application properties is a really good idea when doing structural data stuff :)
Only turn it down to update when sure nothings changing or see errors that look like this:

There was an unexpected error (type=Internal Server Error, status=500).
could not execute statement [Field 'id_booking' doesn't have a default value]
 [insert into reservation (booking,id_credit_card,guest,id) values (?,?,?,?)]

Once your DB is up and running, replace the password place holder in the "src/main/resources/application.properties" file to your MySQL password.

Now you are ready to run the project from IntelliJ and navigate to http://localhost:8080/ to try it out.
