-- Create the database for the airline web application
CREATE DATABASE IF NOT EXISTS AIRLINE_WEB_APPLICATION;

-- Use the newly created database
USE AIRLINE_WEB_APPLICATION;

-- Drop tables if they exist
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Seat;
DROP TABLE IF EXISTS Ticket;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Payment;
DROP TABLE IF EXISTS Aircraft;
DROP TABLE IF EXISTS Crew;
DROP TABLE IF EXISTS Membership;

-- Create tables within the AIRLINE_WEB_APPLICATION database
CREATE TABLE Flight (
    flight_id INT PRIMARY KEY,
    departure_datetime DATETIME,
    arrival_datetime DATETIME,
    source VARCHAR(50),     --COMPOSITE ATTRIBUTE INCLUDIONG AIRPORT_NAME, CITY, COUNTRY
    destination VARCHAR(50),  --COMPOSITE ATTRIBUTE INCLUDING AIRPORT_NAME, CITY, COUNTRY
    aircraft_id INT,
    FOREIGN KEY (aircraft_id) REFERENCES Aircraft(aircraft_id)
);

CREATE TABLE Seat (
    (seat_id INT, aircraft_id INT) PRIMARY KEY,
    seat_number VARCHAR(10),
    aircraft_id INT,
    FOREIGN KEY (aircraft_id) REFERENCES Aircraft(aircraft_id)
);

CREATE TABLE Ticket (
    ticket_id INT PRIMARY KEY,
    customer_id INT,
    customer_namn VARCHAR(100),
    seat_id INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (seat_id) REFERENCES Seat(seat_id)
);

CREATE TABLE Customer (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone_number VARCHAR(20)
);

CREATE TABLE Payment (
    credit_card INT PRIMARY KEY,
    cvv INT,
    Expiry_Date DATETIME,
    ticket_id INT,
    amount DECIMAL(10, 2),
    payment_date DATETIME,
    FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id)
);

CREATE TABLE Aircraft (
    aircraft_id INT PRIMARY KEY,
    model VARCHAR(100),
    capacity INT
);

CREATE TABLE Crew (
    crew_id INT PRIMARY KEY,
    name VARCHAR(100),
    role VARCHAR(50),
    flight_id INT,
    FOREIGN KEY (flight_id) REFERENCES Flight(flight_id)
);

CREATE TABLE Membership (
    membership_id INT PRIMARY KEY,
    customer_id INT,
    start_date DATE,
    member_name VARCHAR(100),
    --COMPOSITE ATTRIBUTE FOR ADDRESS HERE. ###############s
    end_date DATE,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

-- Create roles for different types of users
CREATE ROLE tourism_agent;
CREATE ROLE airline_agent;
CREATE ROLE system_admin;

-- Create users and assign roles with respective privileges

-- Tourism User
CREATE USER 'Tourism_User'@'localhost' IDENTIFIED BY 'tourism_password';
GRANT SELECT ON AIRLINE_WEB_APPLICATION.* TO 'Tourism_User'@'localhost';
GRANT INSERT, UPDATE, DELETE ON AIRLINE_WEB_APPLICATION.* TO 'Tourism_User'@'localhost';
GRANT tourism_agent TO 'Tourism_User'@'localhost';

-- Airline Agent
CREATE USER 'AirlineAgent'@'localhost' IDENTIFIED BY 'airline_agent_password';
GRANT SELECT ON AIRLINE_WEB_APPLICATION.* TO 'AirlineAgent'@'localhost';
GRANT INSERT, UPDATE, DELETE ON AIRLINE_WEB_APPLICATION.* TO 'AirlineAgent'@'localhost';
GRANT airline_agent TO 'AirlineAgent'@'localhost';

-- System Admin
CREATE USER 'SysAdmin'@'localhost' IDENTIFIED BY 'sysadmin_password';
GRANT ALL PRIVILEGES ON AIRLINE_WEB_APPLICATION.* TO 'SysAdmin'@'localhost';
GRANT system_admin TO 'SysAdmin'@'localhost';
