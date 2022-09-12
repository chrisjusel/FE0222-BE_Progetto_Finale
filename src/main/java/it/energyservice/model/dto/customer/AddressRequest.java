package it.energyservice.model.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressRequest {

	private Long id;
	private String street;
	private String civicNumber;
	private String locality;
	private String zip;
	
	private Long common;
}
