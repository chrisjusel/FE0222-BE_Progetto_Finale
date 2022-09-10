package it.energyservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.energyservice.exception.ProvinceException;
import it.energyservice.exception.ProvinceNotFoundException;
import it.energyservice.model.Province;
import it.energyservice.repository.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;

	public Province save(Province province) {
		log.info("Adding new province...");
		log.info("New province '" + province.getNome() + "' addedd");
		return provinceRepository.save(province);
	}

	public Province update(Long id, Province province) {
		Optional<Province> provinceResult = provinceRepository.findById(id);
		log.info("Updating province...");

		if (provinceResult.isPresent()) {
			Province provinceUpdate = provinceResult.get();
			provinceUpdate.setNome(province.getNome());
			provinceUpdate.setSigla(province.getSigla());
			log.info("Province '" + province.getNome() + "' updated");
			return provinceUpdate;
		} else {
			throw new ProvinceException("Error found when entering a province");
		}
	}

	public void delete(Long id) {
		log.info("Removing province...");
		if (provinceRepository.findById(id).isPresent()) {
			provinceRepository.deleteById(id);
			log.info("Province '" + id + "' deleted");
		} else
			throw new ProvinceNotFoundException("No provinces are present with id " + id);
	}

	public Province findById(Long id) {
		Optional<Province> provinceResult = provinceRepository.findById(id);
		log.info("Recovering province...");

		if (provinceResult.isPresent()) {
			log.info("Province '" + id + "' recovered");
			return provinceResult.get();
		}
		else
			throw new ProvinceNotFoundException("No provinces are present with id " + id);
	}

	public Page<Province> getAll(Pageable pageable) {
		log.info("Recovering all provinces...");
		log.info("All provinces recovered");
		return provinceRepository.findAll(pageable);
	}
}
