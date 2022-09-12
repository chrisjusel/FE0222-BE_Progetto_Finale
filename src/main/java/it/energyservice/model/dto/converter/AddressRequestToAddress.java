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
		target.setZip(source.getZip());
		target.setCivicNumber(source.getCivicNumber());
		target.setLocality(source.getLocality());
		target.setStreet(source.getStreet());
		target.setCommon(commonService.findById(source.getCommon()));

		return target;
	}

}
