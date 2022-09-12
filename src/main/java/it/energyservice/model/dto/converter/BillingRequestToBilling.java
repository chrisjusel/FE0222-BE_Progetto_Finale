package it.energyservice.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.energyservice.model.Billing;
import it.energyservice.model.dto.billing.BillingRequest;
import it.energyservice.service.BillingStateService;
import it.energyservice.service.CustomerService;

@Component
public class BillingRequestToBilling implements Converter<BillingRequest, Billing> {

	@Autowired
	private BillingStateService billingStateService;

	@Autowired
	private CustomerService customerService;

	@Override
	public Billing convert(BillingRequest source) {
		Billing target = new Billing();
		target.setId(source.getId());
		target.setYear(source.getYear());
		target.setDate(source.getDate());
		target.setAmount(source.getAmount());
		target.setNumber(source.getNumber());
		target.setState(billingStateService.findByName(source.getState()));
		target.setCustomer(customerService.findById(source.getCustomer()));

		return target;
	}

}
