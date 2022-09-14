package it.energyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;

import it.energyservice.model.Province;
import it.energyservice.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/province")
@Slf4j
public class ProvinceController {

	@Autowired
	private ProvinceService provinceService;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Province> save(@RequestBody Province province) {
		log.info("New POST request to Province: save");
		Province response = provinceService.save(province);
		return new ResponseEntity<Province>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Province> update(@PathVariable Long id, @RequestBody Province province) {
		log.info("New PUT request to Province: update");
		Province response = provinceService.update(id, province);
		return new ResponseEntity<Province>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("New DELETE request to Province: delete");
		provinceService.delete(id);
		return new ResponseEntity<String>("Province successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Province> getById(@PathVariable Long id){
		log.info("New GET request to Province: getById");
		Province response = provinceService.findById(id);
		return new ResponseEntity<Province>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Province>> getAll(Pageable pageable) {
		log.info("New GET request to Province: getAll");
		Page<Province> response = provinceService.getAll(pageable);
		return new ResponseEntity<Page<Province>>(response, HttpStatus.OK);
	}
}
