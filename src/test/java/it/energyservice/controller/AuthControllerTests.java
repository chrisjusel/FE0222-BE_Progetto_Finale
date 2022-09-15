//package it.energyservice.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//
//import javax.transaction.Transactional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import it.energyservice.model.dto.user.UserDto;
//import it.energyservice.security.controller.AuthController;
//import it.energyservice.security.model.LoginRequest;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Transactional
//class AuthControllerTests {
//	
//	@Autowired
//	MockMvc mockMvc;
//	
//	@Autowired
//	AuthController authController;
//
//	private LoginRequest initLoginData() {
//		LoginRequest req = new LoginRequest();
//		req.setUsername("admin");
//		req.setPassword("admin");
//		return req;
//	}
//	
//	private UserDto initRegisterData() {
//		UserDto req = new UserDto();
//		req.setUsername("admin2");
//		req.setPassword("admin2");
//		req.setEmail("admin2@admin2.it");
//		req.getRoles().add("ROLE_ADMIN");
//		return req;
//	}
//
//	@Test
//	public void testLogin() throws Exception {
//		LoginRequest req = initLoginData();
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(req);
//		
//		mockMvc.perform(post("/auth/login/").contentType(MediaType.APPLICATION_JSON).content(json))
//								.andExpect(status().isOk())
//								.andExpect(content().json("{'type':'Bearer'}"))
//								.andReturn();
//	}
//	
//	@Test
//	public void testRegister() throws Exception {
//		UserDto req = initRegisterData();
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json = objectMapper.writeValueAsString(req);
//		
//		mockMvc.perform(post("/auth/register/").contentType(MediaType.APPLICATION_JSON).content(json))
//								.andExpect(status().isOk())
//								.andExpect(content().json("{'username':'admin2'}"))
//								.andReturn();
//	}
//}
