package test;

public class VisaCard extends Card{

	public VisaCard() {
	}

	// Visa Card : Card length should be 16 or 13
	public boolean validateCardDetails(String cardInfo) {
		return (cardInfo.length() == 16 || cardInfo.length() == 13);
	}

	
	// Payment handler : passes the request to next handler if the card is valid 
	@Override
	public void validateRequest(Data data) throws Exception {
		if(validateCardDetails(data.paymentCardNumber)) {
			setNextRequest(null);
		} else {
			data.printErrorToFile(data, " : invalid card" + "\n");
			throw new Exception();
		}
	}

}
