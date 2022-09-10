package it.energyservice.model.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressRequest {

	private String via;
	private String civico;
	private String localita;
	private String cap;
	
	private Long comune;
}
