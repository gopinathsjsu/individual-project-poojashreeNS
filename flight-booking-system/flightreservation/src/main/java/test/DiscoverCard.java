package test;

public class DiscoverCard extends Card {

	public DiscoverCard() {
	}

	// Discovery Card : Card length should be 16
	public boolean validateCardDetails(String cardInfo) {
		return (cardInfo.length() == 16);
	}

	// Payment handler : passes the request to next handler if the card is valid 
	@Override
	public void validateRequest(Data data) throws Exception {
		if (validateCardDetails(data.paymentCardNumber)) {
			setNextRequest(null);
		} else {
			data.printErrorToFile(data, " : invalid card" + "\n");
			throw new Exception();
		}
	}

}
