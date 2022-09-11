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
		target.setAnno(source.getAnno());
		target.setData(source.getData());
		target.setImporto(source.getImporto());
		target.setNumero(source.getNumero());
		target.getCliente().setId(source.getCliente().getId());
		target.setStato(source.getStato());
		return target;
	}

}
