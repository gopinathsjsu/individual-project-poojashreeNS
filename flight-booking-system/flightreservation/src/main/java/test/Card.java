package test;

public abstract class Card extends ValidateRequest{
	//ValidateCardDetails : abstract method validate card
	public abstract boolean validateCardDetails(String cardInfo);
}
