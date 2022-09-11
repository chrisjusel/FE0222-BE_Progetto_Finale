package it.energyservice.exception;

public class AddressNotFoundException extends EnergyServiceEntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8817920619445939368L;

	public AddressNotFoundException(String msg) {
		super(msg);
	}

}
