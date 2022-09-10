package it.energyservice.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.energyservice.model.Billing;
import it.energyservice.model.Common;
import it.energyservice.model.Province;
import it.energyservice.service.CommonService;
import it.energyservice.service.ProvinceService;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CommonService commonService;

	@Override
	public void run(String... args) throws Exception {
		Province provincia = new Province();
		
		provincia.setNome("Roma");
		provincia.setSigla("RM");
		
		provinceService.save(provincia);
		
		Common comune = new Common();
		
		comune.setNome("Roma");
		comune.setProvincia(provincia);
		
		commonService.save(comune);
		
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
}
