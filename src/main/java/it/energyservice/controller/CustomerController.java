package it.energyservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.energyservice.model.Customer;
import it.energyservice.model.dto.converter.CustomerRequestToCustomer;
import it.energyservice.model.dto.customer.CustomerRequest;
import it.energyservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/clienti")
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRequestToCustomer customerRequestToCustomer;

	@PostMapping
	public ResponseEntity<Customer> save(@RequestBody CustomerRequest request) {
		log.info("New POST request to Customer: save");
		Customer customer = customerRequestToCustomer.convert(request);
		Customer response = customerService.save(customer);
		return new ResponseEntity<Customer>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody CustomerRequest request) {
		log.info("New PUT request to Customer: update");
		Customer customer = customerRequestToCustomer.convert(request);
		Customer response = customerService.update(id, customer);
		return new ResponseEntity<Customer>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("New DELETE request to Customer: delete");
		customerService.delete(id);
		return new ResponseEntity<String>("Customer successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) {
		log.info("New GET request to Customer: getById");
		Customer response = customerService.findById(id);
		return new ResponseEntity<Customer>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Customer>> getAll(Pageable pageable) {
		log.info("New GET request to Province: getAll");
		Page<Customer> response = customerService.getAll(pageable);
		return new ResponseEntity<Page<Customer>>(response, HttpStatus.OK);
	}

	@GetMapping("/annualturnover")
	public ResponseEntity<Page<Customer>> findByAnnualTurnoverBetween(@RequestParam double from,
			@RequestParam double to, Pageable pageable) {
		log.info("New GET request to Customer: findByAnnualTurnoverBetween");
		Page<Customer> response = customerService.findByfatturatoAnnualeBetween(from, to, pageable);
		return new ResponseEntity<Page<Customer>>(response, HttpStatus.OK);
	}

	@GetMapping("/date")
	public ResponseEntity<Page<Customer>> findByinsertionDateBetween(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Pageable pageable) {
		log.info("New GET request to Customer: findByAnnualTurnoverBetween");
		Page<Customer> response = customerService.findByInsertionDateBetween(from, to, pageable);
		return new ResponseEntity<Page<Customer>>(response, HttpStatus.OK);
	}

	@GetMapping("/companyname")
	public ResponseEntity<Page<Customer>> findByCompanyNameContains(@RequestParam String name,
			Pageable pageable) {
		log.info("New GET request to Customer: findByCompanyNameContaining");
		Page<Customer> response = customerService.findByCompanyNameContains(name, pageable);
		return new ResponseEntity<Page<Customer>>(response, HttpStatus.OK);
	}

}
