package it.energyservice.model.dto.billing;

import java.sql.Date;

import it.energyservice.model.BillingState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillingResponse {

	private Long id;
	private int anno;
	private Date data;
	private double importo;
	private int numero;
	private CustomerResponse cliente = new CustomerResponse();
	private BillingState stato;
}
