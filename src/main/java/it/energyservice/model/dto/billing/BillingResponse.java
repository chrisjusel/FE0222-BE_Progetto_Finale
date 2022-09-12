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
	private int year;
	private Date date;
	private double amount;
	private int number;
	private CustomerResponse customer = new CustomerResponse();
	private BillingState state;
}
