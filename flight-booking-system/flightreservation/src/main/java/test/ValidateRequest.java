package test;

public abstract class ValidateRequest {
	
	protected ValidateRequest nextRequest;
	
	public ValidateRequest setNextRequest(ValidateRequest nextRequest) {
		this.nextRequest = nextRequest;
		return nextRequest;
	}
	
	public abstract void validateRequest(Data nextRequest) throws Exception;

}
