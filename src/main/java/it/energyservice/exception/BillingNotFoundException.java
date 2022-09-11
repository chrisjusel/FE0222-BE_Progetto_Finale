package it.energyservice.exception;

public class BillingNotFoundException extends EnergyServiceEntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4011001088192266301L;

	public BillingNotFoundException(String msg) {
		super(msg);
	}

}
