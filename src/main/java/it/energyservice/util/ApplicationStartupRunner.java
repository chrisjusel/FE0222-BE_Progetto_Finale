package it.energyservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.energyservice.model.Province;
import it.energyservice.service.ProvinceService;

@Component
public class ApplicationStartupRunner implements CommandLineRunner{

	@Autowired
	private ProvinceService provinceService;
	
	@Override
	public void run(String... args) throws Exception {
		Province province = new Province();
		
		province.setNome("ROMA");
		province.setSigla("RM");
		
		provinceService.save(province);
	}

}
