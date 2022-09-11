package it.energyservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.energyservice.model.BillingState;

public interface BillingStateRepository extends JpaRepository<BillingState, Long>{

	public Optional<BillingState> findByNome(String nome);
}
