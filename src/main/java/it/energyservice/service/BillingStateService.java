package it.energyservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.energyservice.exception.BillingNotFoundException;
import it.energyservice.exception.BillingStateException;
import it.energyservice.exception.BillingStateNotFoundException;
import it.energyservice.model.BillingState;
import it.energyservice.repository.BillingStateRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BillingStateService {

	@Autowired
	private BillingStateRepository billingStateRepository;

	public BillingState save(BillingState billingState) {
		log.info("Adding new billing state...");
		log.info("New billing state '" + billingState.getNome() + "' addedd");
		return billingStateRepository.save(billingState);
	}

	public BillingState update(Long id, BillingState billingState) {
		Optional<BillingState> billingStateResult = billingStateRepository.findById(id);
		log.info("Updating billing state...");

		if (billingStateResult.isPresent()) {
			BillingState billingStateUpdate = billingStateResult.get();
			billingStateUpdate.setNome(billingState.getNome());
			return billingStateUpdate;
		} else {
			throw new BillingStateException("Error found when entering a billing state");
		}
	}

	public void delete(Long id) {
		log.info("Removing billing state...");
		if (billingStateRepository.findById(id).isPresent()) {
			billingStateRepository.deleteById(id);
			log.info("Billing state updated");
		} else {
			throw new BillingStateNotFoundException("No billing states found with id: " + id);
		}
	}

	public BillingState findById(Long id) {
		Optional<BillingState> billingStateResult = billingStateRepository.findById(id);
		log.info("Recovering billing state...");

		if (billingStateResult.isPresent()) {
			log.info("Billing state '" + id + "' recovered");
			return billingStateResult.get();
		} else
			throw new BillingStateNotFoundException("No billing states are present with id " + id);
	}

	public Page<BillingState> getAll(Pageable pageable) {
		log.info("Recovering all billing states...");
		log.info("All billing states recovered");
		return billingStateRepository.findAll(pageable);
	}

	public BillingState findByName(String name) {
		log.info("Recovering billing by name...");
		Optional<BillingState> billingStateResult = billingStateRepository.findByNome(name);

		if (billingStateResult.isPresent()) {
			log.info("Billing state '" + billingStateResult.get().getNome() + "' recovered");
			return billingStateResult.get();
		} else {
			throw new BillingNotFoundException("No billing states are present with name " + name);
		}

	}
}
