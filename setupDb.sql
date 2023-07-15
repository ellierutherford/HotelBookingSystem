CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';
CREATE DATABASE `hotelbookingsystem`;
GRANT ALL PRIVILEGES ON hotelbookingsystem.* TO 'springuser'@'localhost';
FLUSH PRIVILEGES;