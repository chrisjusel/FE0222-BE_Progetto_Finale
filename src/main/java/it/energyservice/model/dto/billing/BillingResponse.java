package it.energyservice.model.dto.billing;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	
	private double amount;
	private int number;
	private CustomerResponse customer = new CustomerResponse();
	private BillingState state;
}
