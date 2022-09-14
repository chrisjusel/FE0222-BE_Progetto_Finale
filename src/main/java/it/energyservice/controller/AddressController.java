package it.energyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.energyservice.model.Address;
import it.energyservice.model.dto.converter.AddressRequestToAddress;
import it.energyservice.model.dto.customer.AddressRequest;
import it.energyservice.service.AddressService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/indirizzi")
@Slf4j
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private AddressRequestToAddress addressRequestToAddress;

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody AddressRequest request) {
		log.info("New PUT request to Address: update");
		Address address = addressRequestToAddress.convert(request);
		Address response = addressService.update(id, address);
		return new ResponseEntity<Address>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("New DELETE request to Address: delete");
		addressService.delete(id);
		return new ResponseEntity<String>("Address successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Address> getById(@PathVariable Long id) {
		log.info("New GET request to Address: getById");
		Address response = addressService.findById(id);
		return new ResponseEntity<Address>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Address>> getAll(Pageable pageable) {
		log.info("New GET request to Address: getAll");
		Page<Address> response = addressService.getAll(pageable);
		return new ResponseEntity<Page<Address>>(response, HttpStatus.OK);
	}
}
