package it.energyservice.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.energyservice.exception.AddressException;
import it.energyservice.exception.AddressNotFoundException;
import it.energyservice.model.Address;
import it.energyservice.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public Address save(Address address) {
		log.info("Adding new address...");
		log.info("New address '" + address.getId() + "' addedd");
		return addressRepository.save(address);
	}
	
	public Address update(Long id, Address address) {
		Optional<Address> addressResult = addressRepository.findById(id);
		log.info("Updating address...");

		if (addressResult.isPresent()) {
			Address addressUpdate = addressResult.get();
			addressUpdate.setZip(address.getZip());
			addressUpdate.setCivicNumber(address.getCivicNumber());
			addressUpdate.setCommon(address.getCommon());
			addressUpdate.setId(id);
			addressUpdate.setLocality(address.getLocality());
			addressUpdate.setStreet(address.getStreet());
			log.info("Address '" + id + "' updated");
			return addressUpdate;
		} else {
			throw new AddressException("Error found when entering an address");
		}
	}

	public void delete(Long id) {
		log.info("Removing address...");
		if (addressRepository.findById(id).isPresent()) {
			addressRepository.deleteById(id);
			log.info("Province '" + id + "' deleted");
		} else
			throw new AddressNotFoundException("No provinces are present with id " + id);
	}

	public Address findById(Long id) {
		Optional<Address> provinceResult = addressRepository.findById(id);
		log.info("Recovering address...");

		if (provinceResult.isPresent()) {
			log.info("Address '" + id + "' recovered");
			return provinceResult.get();
		} else
			throw new AddressNotFoundException("No addresses are present with id " + id);
	}

	public Page<Address> getAll(Pageable pageable) {
		log.info("Recovering all addresses...");
		log.info("All addresses recovered");
		return addressRepository.findAll(pageable);
	}
}
