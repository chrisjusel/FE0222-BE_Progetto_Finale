package it.energyservice.model.dto.customer;

import java.sql.Date;

import it.energyservice.model.CustomerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRequest {

	private String companyName;
	private String vatNumber;
	private String email;
	private Date insertionDate;
	private Date lastContactDate;
	private double annualTurnover;
	private String pec;
	private String phone;
	private String contactEmail;
	private String contactName;
	private String contactSurname;
	private String contactPhone;
	private CustomerType customerType;

	private AddressRequest operatingSiteAddress;
	private AddressRequest legalSiteAddress;

}
