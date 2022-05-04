package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Data {
	
	String bookingName,flightNumber, seatCategory,paymentCardNumber, numberOfSeats;
	File outputCSV, outputTxt;
	List<String> csvList;
	
	//Data : Holds the data that is passed along with the handlers upon successful processing of the request
	public Data(String bookingName, String flightNumber, String seatCategory, String numberOfSeats, String paymentCardNumber, File outputCSV, File outputTxt, List<String> csvlist2) {
		this.bookingName = bookingName;
		this.flightNumber = flightNumber;
		this.seatCategory = seatCategory;
		this.numberOfSeats = numberOfSeats;
		this.paymentCardNumber = paymentCardNumber;
		this.outputCSV = outputCSV;
		this.outputTxt = outputTxt;
		this.csvList = csvlist2;
	}

	public void printErrorToFile(Data data, String reason) throws IOException {
		FileWriter fstream = new FileWriter(data.outputTxt, true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("Please enter correct booking details for " + data.bookingName + reason);
		out.close();
	}
	
	public void printListToCsv(Data data, List<String> csvList) throws IOException {
		FileWriter writer = new FileWriter(data.outputCSV, true);
		String collect = csvList.stream().collect(Collectors.joining(","));

	    writer.write(collect);
	    writer.write("\n");
	    writer.close();

	}
}
