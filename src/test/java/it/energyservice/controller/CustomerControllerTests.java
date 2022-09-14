package it.energyservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.energyservice.model.Customer;
import it.energyservice.model.CustomerType;
import it.energyservice.model.dto.converter.CustomerRequestToCustomer;
import it.energyservice.model.dto.customer.AddressRequest;
import it.energyservice.model.dto.customer.CustomerRequest;
import it.energyservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user", password = "admin", roles = "ADMIN")
@Slf4j
class CustomerControllerTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerRequestToCustomer converter;

	private CustomerRequest buildCustomer() {
		CustomerRequest customerReq = new CustomerRequest();

		AddressRequest operatingSiteAddress = new AddressRequest();
		operatingSiteAddress.setStreet("test");
		operatingSiteAddress.setCivicNumber("test");
		operatingSiteAddress.setZip("test");
		operatingSiteAddress.setLocality("test");
		operatingSiteAddress.setCommon(14L);

		AddressRequest legalSiteAddress = new AddressRequest();
		legalSiteAddress.setStreet("test");
		legalSiteAddress.setCivicNumber("test");
		legalSiteAddress.setZip("test");
		legalSiteAddress.setLocality("test");
		legalSiteAddress.setCommon(13L);

		customerReq.setCompanyName("test");
		customerReq.setCustomerType(CustomerType.SRL);
		customerReq.setVatNumber("test");
		customerReq.setEmail("test");
		customerReq.setPec("test");
		customerReq.setPhone("test");
		customerReq.setContactName("test");
		customerReq.setContactSurname("test");
		customerReq.setContactPhone("test");
		customerReq.setContactEmail("test");
		customerReq.setOperatingSiteAddress(operatingSiteAddress);
		customerReq.setLegalSiteAddress(legalSiteAddress);
		customerReq.setInsertionDate(new Date());
		customerReq.setLastContactDate(new Date());
		customerReq.setAnnualTurnover(12345L);

		return customerReq;
	}
	
	@BeforeEach
	public void initCustomer() {
		Customer customer = converter.convert(buildCustomer());
		customerService.save(customer);
	}

	@Test
	public void saveCustomer() throws Exception {
		CustomerRequest customer = buildCustomer();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(customer);
		log.info(json);

		MvcResult result = mockMvc.perform(post("/api/customers/").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(content().json("{'companyName':'test'}"))
				.andReturn();

		log.info(json);
	}
	
	@Test
	public void updateCustomer() throws Exception {
		CustomerRequest customer = buildCustomer();
		customer.getOperatingSiteAddress().setId(12L);
		customer.getLegalSiteAddress().setId(11L);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(customer);
		log.info(json);
		
		MvcResult result = mockMvc.perform(put("/api/customers/1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(content().json("{'companyName':'test'}"))
				.andReturn();

		log.info(json);
	}
	
	@Test
	public void getById() throws Exception {
		mockMvc.perform(get("/api/customers/1")).andExpect(status().isOk());
	}
	
	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/api/customers/")).andExpect(status().isOk());
	}
	
	@Test
	public void getByAnnualTurnoverBetween() throws Exception {
		mockMvc.perform(get("/api/customers/annualturnover?from=1000&to=10000")).andExpect(status().isOk());
	}
	
	@Test
	public void getByinsertionDateBetween() throws Exception {
		mockMvc.perform(get("/api/customers/insertiondate?from=2019-01-01&to=2023-01-01")).andExpect(status().isOk());
	}
	
	@Test
	public void getByCompanyNameContains() throws Exception {
		mockMvc.perform(get("/api/customers/companyname?name=eba")).andExpect(status().isOk());
	}

}
