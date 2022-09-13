package it.energyservice.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.energyservice.exception.BillingException;
import it.energyservice.exception.BillingNotFoundException;
import it.energyservice.model.Billing;
import it.energyservice.repository.BillingRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BillingService {

	@Autowired
	private BillingRepository billingRepository;

	public Billing save(Billing billing) {
		log.info("Adding new billing...");
		log.info("New billing addedd");
		return billingRepository.save(billing);
	}

	public Billing update(Long id, Billing billing) {
		Optional<Billing> billingResult = billingRepository.findById(id);
		log.info("Updating billing...");

		if (billingResult.isPresent()) {
			Billing billingUpdate = billingResult.get();
			billingUpdate.setYear(billing.getYear());
			billingUpdate.setCustomer(billing.getCustomer());
			billingUpdate.setDate(billing.getDate());
			billingUpdate.setAmount(billing.getAmount());
			billingUpdate.setNumber(billing.getNumber());
			billingUpdate.setState(billing.getState());
			return billingUpdate;
		} else {
			throw new BillingException("Error found when entering a billing");
		}
	}

	public void delete(Long id) {
		log.info("Removing billing...");
		if (billingRepository.findById(id).isPresent()) {
			billingRepository.deleteById(id);
			log.info("Billing '" + id + "' deleted");
		} else
			throw new BillingNotFoundException("No billings are present with id " + id);
	}

	public Billing findById(Long id) {
		Optional<Billing> billingResult = billingRepository.findById(id);
		log.info("Recovering billing...");

		if (billingResult.isPresent()) {
			log.info("Billing '" + id + "' recovered");
			return billingResult.get();
		} else
			throw new BillingNotFoundException("No billings are present with id " + id);
	}

	public Page<Billing> getAll(Pageable pageable) {
		log.info("Recovering all billings...");
		log.info("All billings recovered");
		return billingRepository.findAll(pageable);
	}

	public Page<Billing> getByCustomerId(Long customerId, Pageable pageable) {
		log.info("Recovering all billings by customer id...");
		log.info("All billings recovered");
		return billingRepository.getBillingsByCustomerId(customerId, pageable);
	}

	public Page<Billing> getByState(Long stateId, Pageable pageable) {
		log.info("Recovering all billings by state...");
		log.info("All billings recovered");
		return billingRepository.getBillingsByBillingState(stateId, pageable);
	}

	public Page<Billing> getByYear(Integer year, Pageable pageable) {
		log.info("Recovering all billings by year...");
		log.info("All billings recovered");
		return billingRepository.findByYear(year, pageable);
	}

	public Page<Billing> getByAmountBetween(Double from, Double to, Pageable pageable) {
		log.info("Recovering all billings by amount between...");
		log.info("All billings recovered");
		return billingRepository.findByAmountBetween(from, to, pageable);
	}

	public Page<Billing> getByDate(Date date, Pageable pageable) {
		log.info("Recovering all billings by date...");
		log.info("All billings recovered");
		Date to = new Date(date.getTime());
		to.setHours(23);
		to.setMinutes(59);
		return billingRepository.getByDate(date, to, pageable);
	}
}
