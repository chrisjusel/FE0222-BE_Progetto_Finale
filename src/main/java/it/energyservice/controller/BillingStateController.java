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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.energyservice.model.BillingState;
import it.energyservice.service.BillingStateService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/statifattura")
@Slf4j
public class BillingStateController {

	@Autowired
	private BillingStateService billingStateService;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BillingState> save(@RequestBody BillingState request) {
		log.info("New POST request to Billing state: save");
		BillingState response = billingStateService.save(request);
		return new ResponseEntity<BillingState>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BillingState> update(@PathVariable Long id, @RequestBody BillingState request) {
		log.info("New PUT request to Billing state: update");
		BillingState response = billingStateService.update(id, request);
		return new ResponseEntity<BillingState>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("New DELETE request to Billing state: delete");
		billingStateService.delete(id);
		return new ResponseEntity<String>("Province successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BillingState> getById(@PathVariable Long id) {
		log.info("New GET request to Province: getById");
		BillingState response = billingStateService.findById(id);
		return new ResponseEntity<BillingState>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<BillingState>> getAll(Pageable pageable) {
		log.info("New GET request to Province: getAll");
		Page<BillingState> response = billingStateService.getAll(pageable);
		return new ResponseEntity<Page<BillingState>>(response, HttpStatus.OK);
	}
}
