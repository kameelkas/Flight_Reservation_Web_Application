-- Create the database for the airline web application
DROP DATABASE IF EXISTS FLIGHT_DB;
CREATE DATABASE FLIGHT_DB;
USE FLIGHT_DB;

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
    departure_date DATE,
    departure_time TIME,
    arrival_date DATE,
    arrival_time TIME,
    source_city VARCHAR(50),     
    source_airport VARCHAR(50),
    source_country VARCHAR(%0),
    destination_city VARCHAR(50),
    destination_airport VARCHAR(50),
    destination_country VARCHAR(50),
    aircraft_id INT,
    FOREIGN KEY (aircraft_id) REFERENCES Aircraft(aircraft_id)
);

CREATE TABLE Seat (
    seat_number VARCHAR(10),
    aircraft_id INT,
    seat_type INT,  --using integer for seat type, 1 for economy/regular, 2 for business, 3 for first-class
    (seat_id, aircraft_id) PRIMARY KEY,  --check syntax for whether or not this is right
    FOREIGN KEY (aircraft_id) REFERENCES Aircraft(aircraft_id),
);

CREATE TABLE Ticket (
    ticket_id INT PRIMARY KEY,
    customer_id INT,
    customer_name VARCHAR(100),
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
    Expiry_date DATE,
    ticket_id INT,
    amount DECIMAL(10, 2),
    payment_date DATE,
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
    customer_SA VARCHAR(50),  --street address
    customer_PC VARCHAR(50), --postal code
    customer_city VARCHAR(20),
    customer_country VARCHAR(25),
    end_date DATE,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

-- Create roles for different types of users
DROP ROLE IF EXISTS tourism_agent, airline_agent, system_admin;
CREATE ROLE tourism_agent;
CREATE ROLE airline_agent;
CREATE ROLE system_admin;

-- Create users and assign roles with respective privileges

-- Tourism User
CREATE USER 'Tourism_User'@'localhost' IDENTIFIED BY 'tourism_password'; 
GRANT SELECT ON AIRLINE_WEB_APPLICATION.* TO 'Tourism_User'@'localhost';
GRANT INSERT, UPDATE ON AIRLINE_WEB_APPLICATION.* TO 'Tourism_User'@'localhost';
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

SET DEFAULT ROLE ALL TO Tourism_User@'localhost';
SET DEFAULT ROLE ALL TO AirlineAgent@'localhost';
SET DEFAULT ROLE ALL TO SysAdmin@'localhost';

FLUSH PRIVILEGES;

USE FLIGHT_DB;

----Populating the Database-----
USE FLIGHT_DB;

-- Inserting data into the Flight table
INSERT INTO Flight (flight_id, departure_date, departure_time, arrival_date, arrival_time, source_city, source_airport, source_country, destination_city, destination_airport, destination_country, aircraft_id)
VALUES 
(1, '2023-11-25', '08:00:00', '2023-11-25', '10:30:00', 'City A', 'Airport A', 'Country A', 'City B', 'Airport B', 'Country B', 101),
(2, '2023-11-26', '09:30:00', '2023-11-26', '12:00:00', 'City C', 'Airport C', 'Country C', 'City D', 'Airport D', 'Country D', 102);

-- Inserting data into the Aircraft table
INSERT INTO Aircraft (aircraft_id, model, capacity)
VALUES 
(101, 'Boeing 737', 150),
(102, 'Airbus A320', 180);

-- Inserting data into the Customer table
INSERT INTO Customer (customer_id, name, email, phone_number)
VALUES 
(1, 'John Doe', 'john@example.com', '123-456-7890'),
(2, 'Jane Smith', 'jane@example.com', '987-654-3210');

-- Inserting data into the Seat table
INSERT INTO Seat (seat_number, aircraft_id, seat_type)
VALUES 
('A1', 101, 1),
('A2', 101, 1),
('B1', 102, 2),
('B2', 102, 2),
('C1', 101, 3),
('C2', 101, 3),
('D1', 102, 1),
('D2', 102, 1);

-- Inserting data into the Ticket table
INSERT INTO Ticket (ticket_id, customer_id, customer_name, seat_number, aircraft_id, price)
VALUES 
(1, 1, 'John Doe', 'A1', 101, 150.00),
(2, 2, 'Jane Smith', 'B1', 102, 200.00);

USE FLIGHT_DB;

-- Inserting data into the Payment table
INSERT INTO Payment (credit_card, cvv, Expiry_date, ticket_id, amount, payment_date)
VALUES 
(1234567890123456, 123, '2023-12-01', 1, 150.00, '2023-11-25'),
(9876543210987654, 456, '2023-12-01', 2, 200.00, '2023-11-26'),
(1111222233334444, 789, '2023-12-01', 3, 120.00, '2023-11-27'),
(4444333322221111, 987, '2023-12-01', 4, 250.00, '2023-11-28');

-- Inserting data into the Crew table
INSERT INTO Crew (crew_id, name, role, flight_id)
VALUES 
(1, 'Captain Smith', 'Captain', 1),
(2, 'Co-pilot Johnson', 'Co-pilot', 1),
(3, 'Stewardess Baker', 'Stewardess', 2),
(4, 'Flight Attendant Smith', 'Flight Attendant', 2);

-- Inserting data into the Membership table
INSERT INTO Membership (membership_id, customer_id, start_date, member_name, customer_SA, customer_PC, customer_city, customer_country, end_date)
VALUES 
(1, 1, '2023-01-01', 'John Doe', '123 Main St', '12345', 'Anytown', 'Country X', '2023-12-31'),
(2, 2, '2023-02-01', 'Jane Smith', '456 Park Ave', '54321', 'Otherville', 'Country Y', '2023-12-31'),
(3, 1, '2023-03-01', 'John Doe', '789 Elm St', '67890', 'Another Town', 'Country Z', '2023-12-31'),
(4, 2, '2023-04-01', 'Jane Smith', '012 Oak St', '09876', 'Somewhere', 'Country W', '2023-12-31');


