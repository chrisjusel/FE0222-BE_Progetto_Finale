package it.energyservice.service;

import java.util.Date;
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

	@Autowired
	AddressService addressService;

	public Customer save(Customer customer) {
		log.info("Adding new customer...");
		log.info("New customer '" + customer.getCompanyName() + "' addedd");
		return customerRepository.save(customer);
	}

	public Customer update(Long id, Customer customer) {
		Optional<Customer> customerResult = customerRepository.findById(id);
		log.info("Updating customer...");

		if (customerResult.isPresent()) {
			Customer customerUpdate = customerResult.get();
			customerUpdate.setContactSurname(customer.getContactSurname());
			customerUpdate.setInsertionDate(customer.getInsertionDate());
			customerUpdate.setLastContactDate(customer.getLastContactDate());
			customerUpdate.setEmail(customer.getEmail());
			customerUpdate.setContactEmail(customer.getContactEmail());
			customerUpdate.setAnnualTurnover(customer.getAnnualTurnover());
			customerUpdate.setBillings(customer.getBillings());

			customerUpdate.setLegalSiteAddress(
					addressService.update(customer.getLegalSiteAddress().getId(), customer.getLegalSiteAddress()));

			customerUpdate.setOperatingSiteAddress(addressService.update(customer.getOperatingSiteAddress().getId(),
					customer.getOperatingSiteAddress()));

			customerUpdate.setContactName(customer.getContactName());
			customerUpdate.setVatNumber(customer.getVatNumber());
			customerUpdate.setPec(customer.getPec());
			customerUpdate.setCompanyName(customer.getCompanyName());
			customerUpdate.setPhone(customer.getPhone());
			customerUpdate.setContactPhone(customer.getContactPhone());
			log.info("Customer '" + customer.getCompanyName() + "' updated");
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
		log.info("Recovering all customers...");
		log.info("All customers recovered");
		return customerRepository.findAll(pageable);
	}

	public Page<Customer> findByfatturatoAnnualeBetween(double from, double to, Pageable pageable) {
		log.info("Recovering all customer by annual turnover between " + from + " and " + to + "...");
		log.info("All customers recovered");
		return customerRepository.findByAnnualTurnoverBetween(from, to, pageable);
	}

	public Page<Customer> findByInsertionDateBetween(Date from, Date to, Pageable pageable) {
		log.info("Recovering all customer by annual date between " + from + " and " + to + "...");
		log.info("All customers recovered");
		return customerRepository.findByInsertionDateBetween(from, from, pageable);
	}

	public Page<Customer> findByCompanyNameContains(String substring, Pageable pageable) {
		log.info("Recovering all customer containing " + substring + "...");
		log.info("All customers recovered");
		return customerRepository.findByCompanyNameContains(substring, pageable);
	}
}
