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

		target.setCognomeContatto(source.getCognomeContatto());
		target.setDataInserimento(source.getDataInserimento());
		target.setDataUltimoContatto(source.getDataUltimoContatto());
		target.setEmail(source.getEmail());
		target.setEmailContatto(source.getEmailContatto());
		target.setFatturatoAnnuale(source.getFatturatoAnnuale());
		target.setNomeContatto(source.getNomeContatto());
		target.setPartitaIva(source.getPartitaIva());
		target.setPec(source.getPec());
		target.setRagioneSociale(source.getRagioneSociale());
		target.setTelefono(source.getTelefono());
		target.setTelefonoContatto(source.getTelefonoContatto());
		target.setTipoCliente(source.getTipoCliente());

		target.setIndirizzoSedeLegale(addressConverter.convert(source.getIndirizzoSedeLegale()));
		target.setIndirizzoSedeOperativa(addressConverter.convert(source.getIndirizzoSedeOperativa()));

		return target;
	}

}
