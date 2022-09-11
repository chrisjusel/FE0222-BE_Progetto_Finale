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
		target.setAnno(source.getAnno());
		target.setData(source.getData());
		target.setImporto(source.getImporto());
		target.setNumero(source.getNumero());
		target.setStato(billingStateService.findByName(source.getStato()));
		target.setCliente(customerService.findById(source.getCliente()));

		return target;
	}

}
