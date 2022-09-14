package it.energyservice.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.energyservice.model.Billing;
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
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BillingResponse> save(@RequestBody BillingRequest request) {
		log.info("New POST request to Billing: save");
		Billing billing = billingRequestToBilling.convert(request);
		Billing savedBilling = billingService.save(billing);
		BillingResponse response = billingToBillingResponse.convert(savedBilling);
		return new ResponseEntity<BillingResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BillingResponse> update(@PathVariable Long id, @RequestBody BillingRequest request) {
		log.info("New PUT request to Billing: update");
		Billing billing = billingRequestToBilling.convert(request);
		Billing updatedBilling = billingService.update(id, billing);
		BillingResponse response = billingToBillingResponse.convert(updatedBilling);
		return new ResponseEntity<BillingResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
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

	@GetMapping("/customer/{id}")
	public ResponseEntity<Page<BillingResponse>> getByCustomerId(@PathVariable Long id, Pageable pageable) {
		log.info("New GET request to Billing: getByCustomerId");
		Page<Billing> billingsFound = billingService.getByCustomerId(id, pageable);
		ArrayList<BillingResponse> billingResponse = new ArrayList<>();

		for (Billing billing : billingsFound.getContent()) {
			BillingResponse res = billingToBillingResponse.convert(billing);
			billingResponse.add(res);
		}
		Page<BillingResponse> response = new PageImpl<>(billingResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/state/{id}")
	public ResponseEntity<Page<BillingResponse>> getByState(@PathVariable Long id, Pageable pageable) {
		log.info("New GET request to Billing: getByState");
		Page<Billing> billingsFound = billingService.getByState(id, pageable);
		ArrayList<BillingResponse> billingResponse = new ArrayList<>();

		for (Billing billing : billingsFound.getContent()) {
			BillingResponse res = billingToBillingResponse.convert(billing);
			billingResponse.add(res);
		}
		Page<BillingResponse> response = new PageImpl<>(billingResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/year")
	public ResponseEntity<Page<BillingResponse>> getByYear(@RequestParam Integer year, Pageable pageable) {
		log.info("New GET request to Billing: getByYear");
		Page<Billing> billingsFound = billingService.getByYear(year, pageable);
		ArrayList<BillingResponse> billingResponse = new ArrayList<>();

		for (Billing billing : billingsFound.getContent()) {
			BillingResponse res = billingToBillingResponse.convert(billing);
			billingResponse.add(res);
		}
		Page<BillingResponse> response = new PageImpl<>(billingResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/amount")
	public ResponseEntity<Page<BillingResponse>> getByAmountBetween(@RequestParam Double from, Double to,
			Pageable pageable) {
		log.info("New GET request to Billing: getByYear");
		Page<Billing> billingsFound = billingService.getByAmountBetween(from, to, pageable);
		ArrayList<BillingResponse> billingResponse = new ArrayList<>();

		for (Billing billing : billingsFound.getContent()) {
			BillingResponse res = billingToBillingResponse.convert(billing);
			billingResponse.add(res);
		}
		Page<BillingResponse> response = new PageImpl<>(billingResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/date")
	public ResponseEntity<Page<BillingResponse>> getByDate(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Pageable pageable) {
		log.info("New GET request to Billing: getByDate");
		Page<Billing> billingsFound = billingService.getByDate(date, pageable);
		ArrayList<BillingResponse> billingResponse = new ArrayList<>();

		for (Billing billing : billingsFound.getContent()) {
			BillingResponse res = billingToBillingResponse.convert(billing);
			billingResponse.add(res);
		}
		Page<BillingResponse> response = new PageImpl<>(billingResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
