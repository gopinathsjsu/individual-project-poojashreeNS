package test;

public class SeatCategory extends ValidateRequest{
	
	private int flightAvailableSeats;
	private double flightPrice;

	public SeatCategory(int flightAvailableSeats, double flightPrice) {
		this.flightAvailableSeats = flightAvailableSeats;
		this.flightPrice = flightPrice;
	}
	
	public int getAvaliableSeats() {
		return this.flightAvailableSeats;
	}
	
	public double getFlightPrice() {
		return this.flightPrice;
	}
	
	public void setAvaliableSeats(int avaliableSeats) {
		this.flightAvailableSeats = avaliableSeats;
	}
	
	public void setFlightPrice(double flightPrice) {
		this.flightPrice = flightPrice;
	}

	
	@Override
	public void validateRequest(Data data) throws Exception {		
		int seats = getAvaliableSeats();
		double price = getFlightPrice();
		String BookingSeat = data.numberOfSeats;
		
		if (BookingSeat.matches("-?(0|[1-9]\\d*)") && Integer.parseInt(BookingSeat) <= seats) {
			double totalPrice = Integer.parseInt(BookingSeat) * price;
			data.csvList.add(data.numberOfSeats);
			data.csvList.add(String.valueOf(totalPrice));
		} else {
			data.printErrorToFile(data, " : Seats not avaliable or seat request invalid" + "\n");
			throw new Exception();
		}
		setNextRequest(null);
	}

}
