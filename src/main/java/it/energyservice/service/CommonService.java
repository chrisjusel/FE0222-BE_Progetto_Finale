package it.energyservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.energyservice.exception.CommonException;
import it.energyservice.exception.CommonNotFoundException;
import it.energyservice.model.Common;
import it.energyservice.repository.CommonRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonService {

	@Autowired
	private CommonRepository commonRepository;

	public Common save(Common common) {
		log.info("Adding new common...");
		log.info("New common '" + common.getName() + "' addedd");
		return commonRepository.save(common);
	}

	public Common update(Long id, Common common) {
		Optional<Common> commonResult = commonRepository.findById(id);
		log.info("Updating common...");

		if (commonResult.isPresent()) {
			Common commonUpdate = commonResult.get();
			commonUpdate.setName(common.getName());
			commonUpdate.setProvince(common.getProvince());
			log.info("Common '" + common.getName() + "' updated");
			return commonUpdate;
		} else {
			throw new CommonException("Error found when entering a common");
		}
	}

	public void delete(Long id) {
		log.info("Removing common");
		if (commonRepository.findById(id).isPresent()) {
			commonRepository.deleteById(id);
			log.info("Common '" + id + "' deleted");
		} else {
			throw new CommonNotFoundException("No commons are present with id \" + id");
		}
	}

	public Common findById(Long id) {
		Optional<Common> commonResult = commonRepository.findById(id);
		log.info("Recovering common...");

		if (commonResult.isPresent()) {
			log.info("Common '" + id + "' recovered");
			return commonResult.get();
		} else {
			throw new CommonNotFoundException("No commons are present with id " + id);
		}
	}

	public Page<Common> getAll(Pageable pageable) {
		log.info("Recovering all commons...");
		log.info("All commons recovered");
		return commonRepository.findAll(pageable);
	}
}
