package it.energyservice.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import it.energyservice.model.Common;
import it.energyservice.model.Province;
import it.energyservice.service.CommonService;
import it.energyservice.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired 
	private CommonCsvReader commonCsvReader;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		insertProvinces();
//		insertCommons();
	}

	private void insertProvinces() {
		try {
			FileReader fileReader = new FileReader("src/main/resources/static/province-italiane.csv");
			List<Province> provinces = new ArrayList<>();
			provinces = ProvinceCsvReader.read(fileReader);
			
			for(Province province : provinces) {
				provinceService.save(province);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void insertCommons() {
		try {
			FileReader fileReader = new FileReader("src/main/resources/static/comuni-italiani.csv");
			List<Common> commons = new ArrayList<>();
			commons = commonCsvReader.read(fileReader);
			
			for(Common common : commons) {
				commonService.save(common);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
