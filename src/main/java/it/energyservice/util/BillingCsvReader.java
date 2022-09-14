package it.energyservice.util;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

import it.energyservice.model.Billing;
import it.energyservice.model.dto.billing.BillingRequest;
import it.energyservice.model.dto.converter.BillingRequestToBilling;

@Component
public class BillingCsvReader {

	@Autowired
	private BillingRequestToBilling billingRequestToBilling;

	public List<Billing> read(FileReader file) throws IOException, ParseException {
		CSVReader reader = new CSVReader(file, ',', '\'', 1);

		List<Billing> billings = new ArrayList<>();

		String[] record = null;

		while ((record = reader.readNext()) != null) {
			BillingRequest billing = new BillingRequest();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = formatter.parse(record[0]);
			billing.setDate(date);
			billing.setNumber(Integer.parseInt(record[1]));
			billing.setYear(Integer.parseInt(record[2]));
			billing.setAmount(Double.parseDouble(record[3]));
			billing.setState(record[4]);
			billing.setCustomer(Long.parseLong(record[5]));
			billings.add(billingRequestToBilling.convert(billing));
		}

		reader.close();

		return billings;
	}
}
