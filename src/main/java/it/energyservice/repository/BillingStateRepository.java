package it.energyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.energyservice.model.BillingState;

public interface BillingStateRepository extends JpaRepository<BillingState, Long>{

}
