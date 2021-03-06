package cmpe202.flightreservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.Booking;
import test.Flight;
import test.FlightDatabase;

public class AppTest {
	String errorFile = "src/test/resources/error.txt";
	String bookingConfirmation = "src/test/resources/bookingSuccess.csv";
	String flightDatabase = "src/test/resources/flightDatabase.csv";
	String invalidCategory = "src/test/resources/invalidCategory.csv";
	String invalidPayment = "src/test/resources/invalidPayment.csv";
	String invalidFlight = "src/test/resources/invalidFlight.csv";
	String invalidSeatNumber = "src/test/resources/invalidSeatNumber.csv";
	String validBooking = "src/test/resources/validBooking.csv";
	Scanner scanner;

	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void beforeMethod() throws FileNotFoundException {
		scanner = new Scanner(new File(errorFile));
	}

	@Test
	public void evaluateFlightObjectTest() {
		Flight flight = new Flight("123", "A", "B");
		flight.storeCategoryDetails("Economy", "5", "500.00");

		String flightnum = flight.getFlightNumber();
		assertEquals("123", flightnum);

		int seats = flight.getAvaliableSeats("Economy");
		assertEquals(5, seats);

		flight.setAvaliableSeats("Economy", 20);
		seats = flight.getAvaliableSeats("Economy");
		assertEquals(20, seats);
	}

	@Test
	public void evaluateSingletonClassTest() {
		FlightDatabase fDatabase = FlightDatabase.getInstance();
		FlightDatabase fDatabase1 = FlightDatabase.getInstance();
		System.out.println("fDatabase : " + fDatabase);
		System.out.println("fDatabase1 : " + fDatabase1);
		assertEquals(fDatabase, fDatabase1);
	}

	@Test
	public void validatePaymentStrategyHandlerTest() throws IOException {
		try {
			Booking booking = new Booking(flightDatabase, bookingConfirmation, errorFile);
			booking.bookFlight(invalidPayment);

			scanner.useDelimiter("\n");

			assertTrue(scanner.hasNext());
			assertEquals("Please enter correct booking details for Sierra1 : invalid card", scanner.next());
			assertEquals("Please enter correct booking details for Sierra2 : invalid card", scanner.next());
			assertEquals("Please enter correct booking details for Sierra3 : invalid card", scanner.next());
			assertEquals("Please enter correct booking details for Sierra4 : invalid card", scanner.next());
			assertEquals("Please enter correct booking details for Sierra5 : invalid card", scanner.next());
			assertEquals("Please enter correct booking details for Sierra6 : invalid card", scanner.next());
		} finally {
			scanner.close();
		}

	}

	@Test
	public void validateFlightHandlerTest() throws IOException {
		try {
			Booking booking = new Booking(flightDatabase, bookingConfirmation, errorFile);
			booking.bookFlight(invalidFlight);

			scanner.useDelimiter("\n");

			assertTrue(scanner.hasNext());
			assertEquals("Please enter correct booking details for Sierra1 : invalid flight number", scanner.next());
		} finally {
			scanner.close();
		}
	}

	@Test
	public void validateCategoryHandlerTest() throws IOException {
		try {
			Booking booking = new Booking(flightDatabase, bookingConfirmation, errorFile);
			booking.bookFlight(invalidCategory);

			scanner.useDelimiter("\n");

			assertTrue(scanner.hasNext());
			assertEquals("Please enter correct booking details for Sierra1 : invalid seat category", scanner.next());
		} finally {
			scanner.close();
		}
	}

	@Test
	public void validateSeatHandlerTest() throws IOException {
		try {
			Booking booking = new Booking(flightDatabase, bookingConfirmation, errorFile);
			booking.bookFlight(invalidSeatNumber);

			scanner.useDelimiter("\n");

			assertTrue(scanner.hasNext());
			assertEquals(
					"Please enter correct booking details for Sierra1 : Seats not avaliable or seat request invalid",
					scanner.next());
			assertEquals(
					"Please enter correct booking details for Sierra2 : Seats not avaliable or seat request invalid",
					scanner.next());
		} finally {
			scanner.close();
		}
	}

	@Test
	public void validateRunClientTest() throws IOException {
		Scanner scanner = new Scanner(new File(bookingConfirmation));
		try {
			Booking booking = new Booking(flightDatabase, bookingConfirmation, errorFile);
			booking.bookFlight(validBooking);

			scanner.useDelimiter("\n");

			assertTrue(scanner.hasNext());
			assertEquals("Anna1,CA453,Economy,1,300.0", scanner.next());
			assertEquals("Anna2,CA453,Premium Economy,1,500.0", scanner.next());
			assertEquals("Anna3,CA453,Business,1,2000.0", scanner.next());
		} finally {
			scanner.close();
			new FileWriter(bookingConfirmation, false).close();
		}
	}

	@After
	public void afterMethod() throws IOException {
		new FileWriter(errorFile, false).close();
	}

	@AfterClass
	public static void afterClass() {
	}
}
