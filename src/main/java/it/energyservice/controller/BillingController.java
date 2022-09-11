package it.energyservice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.energyservice.exception.BillingNotFoundException;
import it.energyservice.model.Billing;
import it.energyservice.model.Province;
import it.energyservice.model.dto.billing.BillingRequest;
import it.energyservice.model.dto.billing.BillingResponse;
import it.energyservice.model.dto.converter.BillingRequestToBilling;
import it.energyservice.model.dto.converter.BillingToBillingResponse;
import it.energyservice.service.BillingService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/fatture")
@Slf4j
public class BillingController {

	@Autowired
	private BillingService billingService;

	@Autowired
	private BillingRequestToBilling billingRequestToBilling;

	@Autowired
	private BillingToBillingResponse billingToBillingResponse;

	@PostMapping
	public ResponseEntity<BillingResponse> save(@RequestBody BillingRequest request) {
		log.info("New POST request to Billing: save");
		Billing billing = billingRequestToBilling.convert(request);
		Billing savedBilling = billingService.save(billing);
		BillingResponse response = billingToBillingResponse.convert(savedBilling);
		return new ResponseEntity<BillingResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BillingResponse> update(@PathVariable Long id, @RequestBody BillingRequest request) {
		log.info("New PUT request to Billing: update");
		Billing billing = billingRequestToBilling.convert(request);
		Billing updatedBilling = billingService.update(id, billing);
		BillingResponse response = billingToBillingResponse.convert(updatedBilling);
		return new ResponseEntity<BillingResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("New DELETE request to Billing: delete");
		billingService.delete(id);
		return new ResponseEntity<String>("Billing successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BillingResponse> getById(@PathVariable Long id) {
		log.info("New GET request to Billing: getById");
		Billing foundBilling = billingService.findById(id);
		BillingResponse response = billingToBillingResponse.convert(foundBilling);
		return new ResponseEntity<BillingResponse>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<BillingResponse>> getAll(Pageable pageable) {
		log.info("New GET request to Billing: getAll");
		Page<Billing> billingsFound = billingService.getAll(pageable);
		ArrayList<BillingResponse> billingResponse = new ArrayList<>();

		for (Billing billing : billingsFound.getContent()) {
			BillingResponse res = billingToBillingResponse.convert(billing);
			billingResponse.add(res);
		}
		Page<BillingResponse> response = new PageImpl<>(billingResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
