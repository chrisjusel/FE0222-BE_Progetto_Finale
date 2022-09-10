package it.energyservice.exception;

public class CustomerNotFoundException extends EnergyServiceEntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8191810190935998074L;

	public CustomerNotFoundException(String msg) {
		super(msg);
	}

}
