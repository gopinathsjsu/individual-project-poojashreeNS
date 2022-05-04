package test;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class FlightDatabase extends ValidateRequest {

	Map<String, Flight> flightDatabase;
	private String flightNumber = null;
	ValidateRequest validateRequestObject;
	Flight flight;
public static FlightDatabase db_instance = null;

	//Singleton class where data is stored in the form of hash-table
	private FlightDatabase() {
		flightDatabase = new HashMap<String, Flight>();
	}

	//Getting instance of the singleton object instead of creating it again and again
	public static FlightDatabase getInstance() {
		if (db_instance == null)
			db_instance = new FlightDatabase();

		return db_instance;
	}
	// Given all details it creates a flight object.
	public void createFlight(String flightData) {
		try {
			FileReader fReader = new FileReader(flightData);

			CSVReader csvReader = new CSVReaderBuilder(fReader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll();

			for (String[] row : allData) {
				flightNumber = row[1];
				if(flightDatabase.containsKey(flightNumber)) {
					flight = getFlightInfo(flightNumber);
				} else {
					flight = new Flight(row[1], row[4], row[5]);
				}
				flight.storeCategoryDetails(row[0], row[2], row[3]);
				flightDatabase.put(flightNumber, flight);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Given flight Number get that flight object 
	public Flight getFlightInfo(String flightNumber) {
		return flightDatabase.get(flightNumber);
	}

	//Updates any flight parameter by using its number
	public boolean updateFlightInfo(String flightNumber, Flight updatedFlightObject) {
		boolean isUpdate = false;
		if (flightDatabase.containsKey(flightNumber)) {
			flightDatabase.put(flightNumber, updatedFlightObject);
			isUpdate = true;
		}
		return isUpdate;
	}

	// Check if the flight exists or not using a flight number
	public boolean isFlightExsist(String flightNumber) {
		return flightDatabase.containsKey(flightNumber);
	}

	// FlightDB handler : passes the request to next handler if the Flight is valid or not
	@Override
	public void validateRequest(Data data) throws Exception {
		if (data.flightNumber != null && isFlightExsist(data.flightNumber)) {
			data.csvList.add(flightNumber);
			flight = getFlightInfo(data.flightNumber);
			validateRequestObject = setNextRequest(flight);
			validateRequestObject.validateRequest(data);
		} else {
			data.printErrorToFile(data, " : invalid flight number" + "\n");
			throw new Exception();
		}
	}
}
