-- Create the database for the airline web application
DROP DATABASE IF EXISTS TEST_DB;
CREATE DATABASE TEST_DB;

-- Use the newly created database
USE TEST_DB;

-- Create roles for different types of users
DROP ROLE IF EXISTS tourism_agent, airline_agent, system_admin;
CREATE ROLE tourism_agent;
CREATE ROLE airline_agent;
CREATE ROLE system_admin;

-- Create users and assign roles with respective privileges
DROP USER IF EXISTS 'Tourism_User'@localhost;
DROP USER IF EXISTS 'AirlineAgent'@localhost;
DROP USER IF EXISTS 'SysAdmin'@localhost;

-- Tourism User
CREATE USER 'Tourism_User'@'localhost' IDENTIFIED WITH mysql_native_password BY '123'; 
GRANT SELECT ON TEST_DB.* TO 'Tourism_User'@'localhost';
GRANT INSERT, UPDATE ON TEST_DB.* TO 'Tourism_User'@'localhost';
GRANT tourism_agent TO 'Tourism_User'@'localhost';

-- Airline Agent
CREATE USER 'AirlineAgent'@'localhost' IDENTIFIED WITH mysql_native_password BY '456';
GRANT SELECT ON TEST_DB.* TO 'AirlineAgent'@'localhost';
GRANT INSERT, UPDATE, DELETE ON TEST_DB.* TO 'AirlineAgent'@'localhost';
GRANT airline_agent TO 'AirlineAgent'@'localhost';

-- System Admin
CREATE USER 'SysAdmin'@'localhost' IDENTIFIED WITH mysql_native_password BY '789';
GRANT ALL PRIVILEGES ON TEST_DB.* TO 'SysAdmin'@'localhost';
GRANT system_admin TO 'SysAdmin'@'localhost';

SET DEFAULT ROLE ALL TO Tourism_User@'localhost';
SET DEFAULT ROLE ALL TO AirlineAgent@'localhost';
SET DEFAULT ROLE ALL TO SysAdmin@'localhost';

FLUSH PRIVILEGES;








