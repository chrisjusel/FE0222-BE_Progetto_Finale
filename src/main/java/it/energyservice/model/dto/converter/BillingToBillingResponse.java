package it.energyservice.model.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.energyservice.model.Billing;
import it.energyservice.model.dto.billing.BillingResponse;

@Component
public class BillingToBillingResponse implements Converter<Billing, BillingResponse>{

	@Override
	public BillingResponse convert(Billing source) {
		BillingResponse target = new BillingResponse();
		target.setId(source.getId());
		target.setYear(source.getYear());
		target.setDate(source.getDate());
		target.setAmount(source.getAmount());
		target.setNumber(source.getNumber());
		target.getCustomer().setId(source.getCustomer().getId());
		target.setState(source.getState());
		return target;
	}

}
