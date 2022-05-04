package test;

import java.util.HashMap;
import java.util.Map;

public class Flight extends ValidateRequest {

	private String flightArrival, flightDeparture, flightNumber = null;
	Map<String, SeatCategory> categorySeatmapper = new HashMap<String, SeatCategory>();
	ValidateRequest validateRequestObject;

	// Flight constructor : given the required input such as flight number, arrival
	// departure and others such as category , available seats, price. It creates a
	// flight object.
	public Flight(String flightNumber, String flightArrival, String flightDeparture) {
		this.flightNumber = flightNumber;
		this.flightArrival = flightArrival;
		this.flightDeparture = flightDeparture;
	}

	// Creating object for different categories within a flight.
	public void storeCategoryDetails(String category, String flightAvailableSeats, String flightPrice) {
		int availableSeats = Integer.parseInt(flightAvailableSeats);
		double price = Double.parseDouble(flightPrice);
		SeatCategory seatCategory = new SeatCategory(availableSeats, price);
		categorySeatmapper.put(category, seatCategory);
	}

	//set and get flight number
	public void setFlightNumber(String flightNumber) {
		flightNumber = this.flightNumber;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	//set and get flight Arrival
	public void setFlightArrival(String arrival) {
		flightArrival = arrival;
	}

	public String getFlightArrival() {
		return this.flightArrival;
	}

	//set and get flight departure
	public void setFlightDeparture(String departure) {
		flightDeparture = departure;
	}

	public String getFlightDeparture() {
		return this.flightDeparture;
	}

	//set and get available seats
	public void setAvaliableSeats(String category, int avaliableSeats) {
		categorySeatmapper.get(category).setAvaliableSeats(avaliableSeats);
	}

	public int getAvaliableSeats(String category) {
		return categorySeatmapper.get(category).getAvaliableSeats();
	}

	//set and get flight price
	public void setFlightPrice(String category, double flightPrice) {
		categorySeatmapper.get(category).setFlightPrice(flightPrice);
	}

	public double getFlightPrice(String category) {
		return categorySeatmapper.get(category).getFlightPrice();
	}

	// Check if category exists or not
	public boolean isSeatCategoryExsist(String category) {
		return categorySeatmapper.containsKey(category);
	}

	// Flight handler : passes the request to next handler if the category is valid 
	@Override
	public void validateRequest(Data data) throws Exception {
		if (data.seatCategory != null && isSeatCategoryExsist(data.seatCategory)) {
			data.csvList.add(data.seatCategory);
			validateRequestObject = setNextRequest(categorySeatmapper.get(data.seatCategory));
			validateRequestObject.validateRequest(data);
		} else {
			data.printErrorToFile(data, " : invalid seat category" + "\n");
			throw new Exception();
		}
	}

}
