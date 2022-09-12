package it.energyservice.model.dto.billing;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillingRequest {

	private Long id;
	private int year;
	private Date date;
	private double amount;
	private int number;

	private Long customer;

	private String state;
}
