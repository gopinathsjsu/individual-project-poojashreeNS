package test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Booking extends ValidateRequest {

	FlightDatabase flightDatabase;
	Flight flight;
	ValidateRequest validateRequestObject;
	int avaliableSeats, updatedSeats;
	VisaCard visaCard;
	MasterCard masterCard;
	DiscoverCard discoverCard;
	AmexCard amexCard;
	Data data;
	File csv, text, flightData;
	List<String> csvlist;

	// Booking Constructor : Takes Flight database file, output files as its input
	// and creates flight object.
	public Booking(String flightDB, String bookingCsv, String bookingError) {
		visaCard = new VisaCard();
		masterCard = new MasterCard();
		discoverCard = new DiscoverCard();
		amexCard = new AmexCard();
		flightData = new File(flightDB);
		csv = new File(bookingCsv);
		text = new File(bookingError);

		flightDatabase = FlightDatabase.getInstance();
		flightDatabase.createFlight(flightDB);
	}

	// bookFlight : Main flow of the application where in it uses validation handlers to perform the booking given the booking information
	public void bookFlight(String bookingData) throws IOException {
		FileReader fReader = new FileReader(bookingData);

		CSVReader csvReader = new CSVReaderBuilder(fReader).withSkipLines(1).build();
		List<String[]> allData = csvReader.readAll();

		for (String[] row : allData) {
			csvlist = new ArrayList<String>();
			try {
				data = new Data(row[0], row[1], row[2], row[3], row[4], csv, text, csvlist);

				validateRequest(data);

				if (data.paymentCardNumber.length() < 19 || data.paymentCardNumber.length() > 12) {
					if (data.paymentCardNumber.startsWith("4")) {
						validateRequestObject = setNextRequest(visaCard);
						validateRequestObject.validateRequest(data);
					} else if (data.paymentCardNumber.startsWith("5")) {
						validateRequestObject = setNextRequest(masterCard);
						validateRequestObject.validateRequest(data);
					} else if (data.paymentCardNumber.substring(0, 4) == "6011") {
						validateRequestObject = setNextRequest(discoverCard);
						validateRequestObject.validateRequest(data);
					} else if (data.paymentCardNumber.startsWith("3")) {
						validateRequestObject = setNextRequest(amexCard);
						validateRequestObject.validateRequest(data);
					} else {
						data.printErrorToFile(data, " : invalid card" + "\n");
						throw new Exception();
					}
				} else {
					data.printErrorToFile(data, " : invalid card" + "\n");
					throw new Exception();
				}

				flight = flightDatabase.getFlightInfo(data.flightNumber);
				avaliableSeats = flight.getAvaliableSeats(data.seatCategory);
				updatedSeats = avaliableSeats - Integer.parseInt(data.numberOfSeats);
				flight.setAvaliableSeats(data.seatCategory, updatedSeats);

				if (flightDatabase.updateFlightInfo(data.flightNumber, flight)) {
					data.printListToCsv(data, csvlist);
				} else {
					throw new Exception("Booking is not successful");
				}
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void validateRequest(Data data) throws Exception {
		if (flightDatabase != null) {
			data.csvList.add(data.bookingName);
			validateRequestObject = setNextRequest(flightDatabase);
			validateRequestObject.validateRequest(data);
		} else {
			data.printErrorToFile(data, " : invalid flight number" + "\n");
			throw new Exception();
		}
	}

}
