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

import it.energyservice.model.Common;
import it.energyservice.model.Province;
import it.energyservice.service.CommonService;
import it.energyservice.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user", password = "admin", roles = "ADMIN")
@Slf4j
class CommonControllerTests {
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	CommonService commonService;
	
	@Autowired
	ProvinceService provinceService;

	@BeforeEach
	public void initCommon() {
		Common common = new Common();

		common.setId(1L);
		common.setName("Roma");
		
		Province province = new Province();
		province.setId(2L);
		province.setName("Roma");
		province.setSign("RM");
		
		common.setProvince(province);

		commonService.save(common);
	}

	@Test
	public void saveCommon() throws Exception {
		Common common = new Common();

		common.setId(1L);
		common.setName("Fiumicino");
		
		Province province = new Province();
		province.setId(2L);
		province.setName("Roma");
		province.setSign("RM");
		
		common.setProvince(province);

		commonService.save(common);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(common);
		
		MvcResult result = mockMvc
				.perform(post("/api/commons/").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(content().json("{'name':'Fiumicino'}"))
												.andExpect(content().json("{'province':{"
																+ "\"name\" : \"Roma\","
																+ "\"sign\" : \"RM\"}}"))
												.andReturn();

		log.info(json);
	}
	
	@Test
	public void updateCommon() throws Exception {
		Common common = new Common();

		common.setId(1L);
		common.setName("Fiumicino");
		
		Province province = new Province();
		province.setId(2L);
		province.setName("Roma");
		province.setSign("RM");
		
		common.setProvince(province);
		
		provinceService.save(province);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(common);
		
		MvcResult result = mockMvc
				.perform(put("/api/commons/3").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(content().json("{'name':'Fiumicino'}"))
											.andExpect(content().json("{'province':{"
															+ "\"name\" : \"Roma\","
															+ "\"sign\" : \"RM\"}}"))
											.andReturn();

		log.info(json);
	}
	
	@Test
	public void getById() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/api/commons/3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("{'name':'Ala di Stura'}"))
											.andExpect(content().json("{'province':{"
														+ "\"name\" : \"TORINO\","
														+ "\"sign\" : \"TO\"}}"))
											.andReturn();
	}
	
//	@Test
//	public void deleteProvince() throws Exception {
//		mockMvc.perform(delete("/api/commons/1")).andExpect(status().isOk());
//	}
}
