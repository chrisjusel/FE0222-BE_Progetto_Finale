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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.energyservice.model.Province;
import it.energyservice.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/provinces")
@Slf4j
public class ProvinceController {

	@Autowired
	private ProvinceService provinceService;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@Operation(summary = "Save a province", description = "Method to save a province")
	@ApiResponse(responseCode = "200", description = "Province saved")
	public ResponseEntity<Province> save(@RequestBody Province province) {
		log.info("New POST request to Province: save");
		Province response = provinceService.save(province);
		return new ResponseEntity<Province>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@Operation(summary = "Update a province", description = "Method to update a province")
	@ApiResponse(responseCode = "200", description = "Province updated")
	public ResponseEntity<Province> update(@PathVariable Long id, @RequestBody Province province) {
		log.info("New PUT request to Province: update");
		Province response = provinceService.update(id, province);
		return new ResponseEntity<Province>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@Operation(summary = "Delete a province", description = "Method to delete a province")
	@ApiResponse(responseCode = "200", description = "Province deleted")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("New DELETE request to Province: delete");
		provinceService.delete(id);
		return new ResponseEntity<String>("Province successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retrieve a province", description = "Method to Retrieve a province")
	@ApiResponse(responseCode = "200", description = "Province Retrieved")
	public ResponseEntity<Province> getById(@PathVariable Long id){
		log.info("New GET request to Province: getById");
		Province response = provinceService.findById(id);
		return new ResponseEntity<Province>(response, HttpStatus.OK);
	}

	@GetMapping
	@Operation(summary = "Retrieve all provinces", description = "Method to Retrieve all provinces")
	@ApiResponse(responseCode = "200", description = "Provinces Retrieved")
	public ResponseEntity<Page<Province>> getAll(Pageable pageable) {
		log.info("New GET request to Province: getAll");
		Page<Province> response = provinceService.getAll(pageable);
		return new ResponseEntity<Page<Province>>(response, HttpStatus.OK);
	}
}
