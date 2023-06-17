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
