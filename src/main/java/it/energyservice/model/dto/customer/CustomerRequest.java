package it.energyservice.model.dto.customer;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date insertionDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastContactDate;

	private double annualTurnover;
	private String pec;
	private String phone;
	private String contactEmail;
	private String contactName;
	private String contactSurname;
	private String contactPhone;
	private CustomerType customerType;

	private AddressRequest operatingSiteAddress = new AddressRequest();
	private AddressRequest legalSiteAddress = new AddressRequest();

}
