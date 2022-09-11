package it.energyservice.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.energyservice.model.Address;
import it.energyservice.model.dto.customer.AddressRequest;
import it.energyservice.service.CommonService;

@Component
public class AddressRequestToAddress implements Converter<AddressRequest, Address>{

	@Autowired
	private CommonService commonService;
	
	@Override
	public Address convert(AddressRequest source) {
		Address target = new Address();

		target.setId(source.getId());
		target.setCap(source.getCap());
		target.setCivico(source.getCivico());
		target.setLocalita(source.getLocalita());
		target.setVia(source.getVia());
		target.setComune(commonService.findById(source.getComune()));

		return target;
	}

}
