package it.energyservice.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

import it.energyservice.model.Common;
import it.energyservice.model.Province;
import it.energyservice.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonCsvReader {

	@Autowired
	private ProvinceService provinceService;
	
	public List<Common> read(FileReader file) throws IOException {
		CSVReader reader = new CSVReader(file, ';', '\'', 1);

		List<Common> commons = new ArrayList<>();

		String[] record = null;

		while ((record = reader.readNext()) != null) {
			if (!record[3].equals("") ) {
				if(provinceService.findProvinceByName(record[3]) != null) {
					Common common = new Common();
					common.setName(record[2]);
					common.setProvince(provinceService.findProvinceByName(record[3]));
					commons.add(common);
				}
			}
		}

		reader.close();

		return commons;

	}
}
