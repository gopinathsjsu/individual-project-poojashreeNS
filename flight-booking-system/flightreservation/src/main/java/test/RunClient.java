package test;

import java.io.IOException;

public class RunClient {
	static String bookingFile, flightDB, bookingCsv, bookingError;

	public static void main(String[] args) throws IOException {
		// Takes Booking Info, Database, Booking confirmation file and Error file as an arguments and performs flight booking operation
		if (args.length == 4) {
			bookingFile = args[0];
			flightDB = args[1];
			bookingCsv = args[2];
			bookingError = args[3];

			Booking booking = new Booking(flightDB, bookingCsv, bookingError);
			booking.bookFlight(bookingFile);
		} else {
			System.out.println("Please provide proper arguments");
		}
	}

}