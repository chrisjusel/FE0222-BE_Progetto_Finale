package it.energyservice.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import it.energyservice.model.Province;

public abstract class ProvinceCsvReader {

	public static List<Province> read(FileReader file) throws IOException {
		CSVReader reader = new CSVReader(file, ';', '\'', 1);

		List<Province> provinces = new ArrayList<>();

		String[] record = null;

		while ((record = reader.readNext()) != null) {
			if (!record[3].equals("")) {
				Province province = new Province();
				province.setName(record[1]);
				province.setSign(record[0]);
				province.setId(Long.parseLong(record[3]));
				provinces.add(province);
			}
		}

		reader.close();

		return provinces;
	}
}
