package it.energyservice.service;

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
		log.info("New province '" + billing.getId() + "' addedd");
		return billingRepository.save(billing);
	}

	public Billing update(Long id, Billing billing) {
		Optional<Billing> billingResult = billingRepository.findById(id);
		log.info("Updating billing...");

		if (billingResult.isPresent()) {
			Billing billingUpdate = billingResult.get();
			billingUpdate.setAnno(billing.getAnno());
			billingUpdate.setCliente(billing.getCliente());
			billingUpdate.setData(billing.getData());
			billingUpdate.setImporto(billing.getImporto());
			billingUpdate.setNumero(billing.getNumero());
			billingUpdate.setStato(billing.getStato());
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
}
