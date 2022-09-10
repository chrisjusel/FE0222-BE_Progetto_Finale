package it.energyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return new ResponseEntity<Customer>(response, HttpStatus.OK);
	}
}
