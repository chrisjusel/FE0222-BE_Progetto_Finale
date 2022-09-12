package it.energyservice.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.energyservice.model.Address;
import it.energyservice.model.Customer;
import it.energyservice.model.dto.customer.AddressRequest;
import it.energyservice.model.dto.customer.CustomerRequest;
import it.energyservice.service.AddressService;
import it.energyservice.service.CommonService;

@Component
public class CustomerRequestToCustomer implements Converter<CustomerRequest, Customer> {

	@Autowired
	private CommonService commonService;

	@Autowired
	private AddressRequestToAddress addressConverter;

	@Override
	public Customer convert(CustomerRequest source) {
		Customer target = new Customer();

		target.setContactSurname(source.getContactSurname());
		target.setInsertionDate(source.getInsertionDate());
		target.setLastContactDate(source.getLastContactDate());
		target.setEmail(source.getEmail());
		target.setContactEmail(source.getContactEmail());
		target.setAnnualTurnover(source.getAnnualTurnover());
		target.setContactName(source.getContactName());
		target.setVatNumber(source.getVatNumber());
		target.setPec(source.getPec());
		target.setCompanyName(source.getCompanyName());
		target.setPhone(source.getPhone());
		target.setContactPhone(source.getContactPhone());
		target.setCustomerType(source.getCustomerType());

		target.setLegalSiteAddress(addressConverter.convert(source.getLegalSiteAddress()));
		target.setOperatingSiteAddress(addressConverter.convert(source.getOperatingSiteAddress()));

		return target;
	}

}
