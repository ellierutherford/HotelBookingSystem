use hotelbookingsystem;
CREATE TABLE roomtypes (
    id bigint,
    room_name varchar(255),
    night_rate decimal,
    PRIMARY KEY (id)
);
INSERT INTO roomtypes (id, room_name, night_rate) values (1, "Suite", 200);
INSERT INTO roomtypes (id, room_name, night_rate) values (2, "Double", 150);
INSERT INTO roomtypes (id, room_name, night_rate) values (3, "Single", 100);
CREATE TABLE roomassets (
    id bigint,
    roomasset_name varchar(255),
    roomtype_id bigint,
    max_guests int,
    roomasset_number int,
    PRIMARY KEY (id),
    FOREIGN KEY (roomtype_id) REFERENCES roomtypes(id)
);
INSERT INTO roomassets (id, roomasset_name, roomtype_id, max_guests, roomasset_number)
values (1, "Forty Foot", 1, 3, 1);
INSERT INTO roomassets (id, roomasset_name, roomtype_id, max_guests, roomasset_number)
values (2, "Seapoint", 1, 4, 2);
INSERT INTO roomassets (id, roomasset_name, roomtype_id, max_guests, roomasset_number)
values (3, "Killiney", 2, 2, 3);
INSERT INTO roomassets (id, roomasset_name, roomtype_id, max_guests, roomasset_number)
values (4, "Vico Baths", 3, 1, 4);
INSERT INTO roomassets (id, roomasset_name, roomtype_id, max_guests, roomasset_number)
values (5, "Shankill", 2, 2, 5);