package it.energyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.energyservice.model.Common;
import it.energyservice.model.dto.common.CommonRequest;
import it.energyservice.model.dto.converter.CommonRequestToCommon;
import it.energyservice.service.CommonService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comuni")
@Slf4j
public class CommonController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private CommonRequestToCommon commonRequestToCommon;

	@PostMapping
	public ResponseEntity<Common> save(@RequestBody CommonRequest request) {
		log.info("New POST request to Common: save");
		Common common = commonRequestToCommon.convert(request);
		Common response = commonService.save(common);
		return new ResponseEntity<Common>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Common> update(@PathVariable Long id, @RequestBody CommonRequest request) {
		log.info("New PUT request to Common: update");
		Common common = commonRequestToCommon.convert(request);
		Common response = commonService.update(id, common);
		return new ResponseEntity<Common>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("New DELETE request to Common: delete");
		commonService.delete(id);
		return new ResponseEntity<String>("Common successfully deleted", HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Common> getById(@PathVariable Long id) {
		log.info("New GET request to Common: getById");
		Common response = commonService.findById(id);
		return new ResponseEntity<Common>(response, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Page<Common>> getAll(Pageable pageable){
		log.info("New GET request to Province: getAll");
		Page<Common> response = commonService.getAll(pageable);
		return new ResponseEntity<Page<Common>>(response, HttpStatus.OK);
	}
}
