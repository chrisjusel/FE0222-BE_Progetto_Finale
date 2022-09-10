package it.energyservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.energyservice.exception.CustomerException;
import it.energyservice.exception.CustomerNotFoundException;
import it.energyservice.model.Customer;
import it.energyservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer save(Customer customer) {
		log.info("Adding new customer...");
		log.info("New customer '" + customer.getRagioneSociale() + "' addedd");
		return customerRepository.save(customer);
	}

	public Customer update(long id, Customer customer) {
		Optional<Customer> customerResult = customerRepository.findById(id);
		log.info("Updating customer...");

		if (customerResult.isPresent()) {
			Customer customerUpdate = customerResult.get();
			customerUpdate.setCognomeContatto(customer.getCognomeContatto());
			customerUpdate.setDataInserimento(customer.getDataInserimento());
			customerUpdate.setDataUltimoContatto(customer.getDataUltimoContatto());
			customerUpdate.setEmail(customer.getEmail());
			customerUpdate.setEmailContatto(customer.getEmailContatto());
			customerUpdate.setFatturatoAnnuale(customer.getFatturatoAnnuale());
			customerUpdate.setFatture(customer.getFatture());
			customerUpdate.setIndirizzoSedeLegale(customer.getIndirizzoSedeLegale());
			customerUpdate.setIndirizzoSedeOperativa(customer.getIndirizzoSedeOperativa());
			customerUpdate.setNomeContatto(customer.getNomeContatto());
			customerUpdate.setPartitaIva(customer.getPartitaIva());
			customerUpdate.setPec(customer.getPec());
			customerUpdate.setRagioneSociale(customer.getRagioneSociale());
			customerUpdate.setTelefono(customer.getTelefono());
			customerUpdate.setTelefonoContatto(customer.getTelefonoContatto());
			log.info("Customer '" + customer.getRagioneSociale() + "' updated");
			return customerUpdate;
		} else {
			throw new CustomerException("Error found when entering a customer");
		}
	}

	public void delete(Long id) {
		log.info("Removing customer...");
		if (customerRepository.findById(id).isPresent()) {
			customerRepository.deleteById(id);
			log.info("Customer '" + id + "' deleted");
		} else
			throw new CustomerNotFoundException("No customers are present with id " + id);
	}

	public Customer findById(Long id) {
		Optional<Customer> provinceResult = customerRepository.findById(id);
		log.info("Recovering customer...");

		if (provinceResult.isPresent()) {
			log.info("Province '" + id + "' recovered");
			return provinceResult.get();
		} else
			throw new CustomerNotFoundException("No customers are present with id " + id);
	}
	
	public Page<Customer> getAll(Pageable pageable) {
		log.info("Recovering all provinces...");
		log.info("All provinces recovered");
		return customerRepository.findAll(pageable);
	}
}
