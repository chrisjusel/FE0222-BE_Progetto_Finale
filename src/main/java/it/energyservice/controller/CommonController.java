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
import it.energyservice.model.Common;
import it.energyservice.model.dto.common.CommonRequest;
import it.energyservice.model.dto.converter.CommonRequestToCommon;
import it.energyservice.service.CommonService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/commons")
@Slf4j
public class CommonController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private CommonRequestToCommon commonRequestToCommon;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@Operation(summary = "Save a common", description = "Method to save a common")
	@ApiResponse(responseCode = "200", description = "Common saved")
	public ResponseEntity<Common> save(@RequestBody CommonRequest request) {
		log.info("New POST request to Common: save");
		Common common = commonRequestToCommon.convert(request);
		Common response = commonService.save(common);
		return new ResponseEntity<Common>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@Operation(summary = "Update a common", description = "Method to update a common")
	@ApiResponse(responseCode = "200", description = "Common saved")
	public ResponseEntity<Common> update(@PathVariable Long id, @RequestBody CommonRequest request) {
		log.info("New PUT request to Common: update");
		Common common = commonRequestToCommon.convert(request);
		Common response = commonService.update(id, common);
		return new ResponseEntity<Common>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@Operation(summary = "Delete a common", description = "Method to delete a common")
	@ApiResponse(responseCode = "200", description = "Common deleted")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("New DELETE request to Common: delete");
		commonService.delete(id);
		return new ResponseEntity<String>("Common successfully deleted", HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Retrieve a common", description = "Method to retrieve a common")
	@ApiResponse(responseCode = "200", description = "Common Retrieved")
	public ResponseEntity<Common> getById(@PathVariable Long id) {
		log.info("New GET request to Common: getById");
		Common response = commonService.findById(id);
		return new ResponseEntity<Common>(response, HttpStatus.OK);
	}
	
	@GetMapping
	@Operation(summary = "Retrieve all commons", description = "Method to retrieve all commons")
	@ApiResponse(responseCode = "200", description = "Commons Retrieved")
	public ResponseEntity<Page<Common>> getAll(Pageable pageable){
		log.info("New GET request to Province: getAll");
		Page<Common> response = commonService.getAll(pageable);
		return new ResponseEntity<Page<Common>>(response, HttpStatus.OK);
	}
}
