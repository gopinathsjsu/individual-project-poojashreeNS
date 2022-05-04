package test;

public class MasterCard extends Card {

	public MasterCard() {
	}

	// Master Card : Card length should be 16 and second Digit must be between 1 to
	// 5
	public boolean validateCardDetails(String cardInfo) {
		return (cardInfo.length() == 16 && cardInfo.substring(0, 2) == "1" || cardInfo.substring(0, 2) == "2"
				|| cardInfo.substring(0, 2) == "4" || cardInfo.substring(0, 2) == "3"
				|| cardInfo.substring(0, 2) == "5");
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
