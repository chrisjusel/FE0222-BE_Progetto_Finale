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

import it.energyservice.model.Customer;
import it.energyservice.model.CustomerType;
import it.energyservice.model.dto.converter.CustomerRequestToCustomer;
import it.energyservice.model.dto.customer.CustomerRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerCsvReader {

	@Autowired
	private CustomerRequestToCustomer customerRequestToCustomer;

	public List<Customer> read(FileReader file) throws IOException, ParseException {
		CSVReader reader = new CSVReader(file, ',', '\'', 1);

		List<Customer> customers = new ArrayList<>();

		String[] record = null;

		while ((record = reader.readNext()) != null) {
			for (int i = 0; i < record.length; i++) {
				log.info("record[" + i + "] = " + record[i]);
			}
			CustomerRequest customer = new CustomerRequest();

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1, date2;

			customer.setCompanyName(record[0]);
			customer.setCustomerType(CustomerType.valueOf(record[1]));
			customer.setVatNumber(record[2]);
			customer.setEmail(record[3]);
			customer.setPec(record[4]);
			customer.setPhone(record[5]);
			customer.setContactName(record[6]);
			customer.setContactSurname(record[7]);
			customer.setContactPhone(record[8]);
			customer.setContactEmail(record[9]);
			customer.getOperatingSiteAddress().setStreet(record[10]);
			customer.getOperatingSiteAddress().setCivicNumber(record[11]);
			customer.getOperatingSiteAddress().setLocality(record[12]);
			customer.getOperatingSiteAddress().setZip(record[13]);
			customer.getOperatingSiteAddress().setCommon(Long.parseLong(record[14]));
			customer.getLegalSiteAddress().setStreet(record[15]);
			customer.getLegalSiteAddress().setCivicNumber(record[16]);
			customer.getLegalSiteAddress().setZip(record[17]);
			customer.getLegalSiteAddress().setLocality(record[18]);
			customer.getLegalSiteAddress().setCommon(Long.parseLong(record[19]));

			date1 = formatter.parse(record[20]);
			date2 = formatter.parse(record[21]);
			customer.setInsertionDate(date1);
			customer.setLastContactDate(date2);

			customer.setAnnualTurnover(Double.parseDouble(record[22]));

			customers.add(customerRequestToCustomer.convert(customer));
		}

		reader.close();

		return customers;

	}

}
