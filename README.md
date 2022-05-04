# Flight Booking System

## Problem Statement

You will build a flight booking application using at least three design patterns. The application should maintain an internal, static database (inventory of flight details) (this may be developed using HashMap and/or other built-in Java Data structures). This means once we re-run the program, the changes to the data would not persist. We will provide the data that has to be maintained. The data will contain the following tables and fields:


Table 1: Flights
● Category (Economy, Premium Economy, Business)
● Flight number
● The available Seats Of each category
● Price of each seat
● Arrival City
● Departure City


1. Input CSV file will contain booking details including booking name, flight number, seat category, number of seats, and the payment card number.

2. Input file should be processed as follows:
● Validate if the requested flight exists.
● If the flight exists, validate the number of seats requested for the category.
● After this validation, if the booking is valid, calculate the total price (NoOfSeats *
price)
● Take the card number of the user and validate it using the given rules:
○ Visa card: has length either 13 or 16. It begins with a 4
○ Mastercard: has length 16. Begins with 5 and the 2nd digit begins from 1
to 5 inclusive
○ Discover: length 16, and the first 4 digits begins from 6011
○ Amex: has length 15 and starts with 3. 2nd digit must be 4 or 7
○ Any card greater than 19 or not satisfying above conditions is considered
invalid.
● If the card is valid then modify the available seats for that category and flight number
● Then output the CSV list with booking name, flight number, Category, number of
seats booked, total price.
● In case, it is an incorrect request at any of the steps, generate and output TXT file
with the message "Please enter correct booking details for
<booking_name>:<reason>" and include the information with incorrect information.
For example, Please enter correct booking details for John: invalid flight number.
  
## Class Diagram

![flightreservation](https://user-images.githubusercontent.com/89234077/166617721-dac2c7d5-dbb4-4f75-ac13-28ef18755408.gif)
