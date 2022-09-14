package it.energyservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import it.energyservice.model.Province;
import it.energyservice.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user", password = "admin", roles = "ADMIN")
@Slf4j
class ProvinceControllerTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ProvinceService provinceService;

	@BeforeEach
	public void initProvince() {
		Province province = new Province();

		province.setId(1L);
		province.setName("TORINO");
		province.setSign("TO");

		provinceService.save(province);
	}

	@Test
	public void saveProvince() throws Exception {
		Province province = new Province();

		province.setName("ROMA");
		province.setSign("RM");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(province);
		
		MvcResult result = mockMvc
				.perform(post("/api/provinces/").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(content().json("{'name':'ROMA'}"))
												.andExpect(content().json("{'sign':'RM'}"))
												.andReturn();

		log.info(json);
	}
	
	@Test
	public void updateProvince() throws Exception {
		Province province = new Province();

		province.setName("FIUMICINO");
		province.setSign("RM");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(province);
		
		MvcResult result = mockMvc
				.perform(put("/api/provinces/1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(content().json("{'name':'FIUMICINO'}"))
											.andExpect(content().json("{'sign':'RM'}"))
											.andReturn();

		log.info(json);
	}
	
	@Test
	public void getById() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/api/provinces/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("{'name':'TORINO'}"))
											.andExpect(content().json("{'sign':'TO'}"))
											.andReturn();
	}
	
	@Test
	public void getAll() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/api/provinces?size=3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("{'content': [{},{},{}]}"))
											.andExpect(content().json("{'pageable': {}}"))
											.andReturn();
	}


}
