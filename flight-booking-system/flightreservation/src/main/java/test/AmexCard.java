package test;

public class AmexCard extends Card {

	public AmexCard() {
	}

	// Amex Card : Card length should be 15 and second digit of the card should be 4
	// or 7
	public boolean validateCardDetails(String cardInfo) {
		return (cardInfo.length() == 15 && (cardInfo.substring(0, 2) == "4" || cardInfo.substring(0, 2) == "7"));
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
