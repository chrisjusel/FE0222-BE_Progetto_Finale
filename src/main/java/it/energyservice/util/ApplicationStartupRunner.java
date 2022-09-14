//package it.energyservice.util;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.stereotype.Component;
//
//import it.energyservice.model.Billing;
//import it.energyservice.model.BillingState;
//import it.energyservice.model.Common;
//import it.energyservice.model.Customer;
//import it.energyservice.model.Province;
//import it.energyservice.model.Role;
//import it.energyservice.model.Roles;
//import it.energyservice.model.User;
//import it.energyservice.security.service.RoleService;
//import it.energyservice.security.service.UserService;
//import it.energyservice.service.BillingService;
//import it.energyservice.service.BillingStateService;
//import it.energyservice.service.CommonService;
//import it.energyservice.service.CustomerService;
//import it.energyservice.service.ProvinceService;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class ApplicationStartupRunner implements CommandLineRunner {
//
//	@Autowired
//	private ProvinceService provinceService;
//
//	@Autowired
//	private CommonService commonService;
//
//	@Autowired
//	private CommonCsvReader commonCsvReader;
//
//	@Autowired
//	private CustomerCsvReader customerCsvReader;
//
//	@Autowired
//	private BillingCsvReader billingCsvReader;
//
//	@Autowired
//	private CustomerService customerService;
//
//	@Autowired
//	private RoleService roleService;
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private BillingStateService billingStateService;
//
//	@Autowired
//	private BillingService billingService;
//
//	@Override
//	@Transactional
//	public void run(String... args) throws Exception {
//		insertProvinces();
//		insertCommons();
//		insertRoles();
//		insertUsers();
//		insertBillingState();
//		insertCustomers();
//		insertBillings();
//	}
//
//	private void insertProvinces() {
//		try {
//			FileReader fileReader = new FileReader("src/main/resources/static/province-italiane.csv");
//			List<Province> provinces = new ArrayList<>();
//			provinces = ProvinceCsvReader.read(fileReader);
//
//			for (Province province : provinces) {
//				provinceService.save(province);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void insertCommons() {
//		try {
//			FileReader fileReader = new FileReader("src/main/resources/static/comuni-italiani.csv");
//			List<Common> commons = new ArrayList<>();
//			commons = commonCsvReader.read(fileReader);
//
//			for (Common common : commons) {
//				commonService.save(common);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void insertCustomers() {
//		try {
//			FileReader fileReader = new FileReader("src/main/resources/static/customers.csv");
//			List<Customer> customers = new ArrayList<>();
//			try {
//				customers = customerCsvReader.read(fileReader);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			for (Customer customer : customers) {
//				customerService.save(customer);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void insertBillings() {
//		try {
//			FileReader fileReader = new FileReader("src/main/resources/static/billings.csv");
//			List<Billing> billings = new ArrayList<>();
//			try {
//				billings = billingCsvReader.read(fileReader);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			for (Billing billing : billings) {
//				billingService.save(billing);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void insertRoles() {
//		for (Roles role : Roles.values()) {
//			Role saveToDb = new Role();
//			saveToDb.setRoleName(role);
//			roleService.save(saveToDb);
//		}
//	}
//
//	private void insertUsers() {
//		Role admin = new Role();
//		Role user = new Role();
//		admin.setRoleName(Roles.ROLE_ADMIN);
//		user.setRoleName(Roles.ROLE_USER);
//
//		User userAdmin = new User();
//		userAdmin.setUsername("admin");
//		userAdmin.setEmail("admin@admin.it");
//		userAdmin.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
//		userAdmin.getRoles().add(admin);
//
//		User userUser = new User();
//		userUser.setUsername("user");
//		userUser.setEmail("user@user.it");
//		userUser.setPassword(BCrypt.hashpw("user", BCrypt.gensalt()));
//		userUser.getRoles().add(user);
//
//		userService.save(userUser);
//		userService.save(userAdmin);
//	}
//
//	private void insertBillingState() {
//		BillingState payed = new BillingState();
//		BillingState notPayed = new BillingState();
//		BillingState waiting = new BillingState();
//		BillingState refused = new BillingState();
//
//		payed.setName("PAGATA");
//		notPayed.setName("NON PAGATA");
//		waiting.setName("IN ATTESA");
//		refused.setName("RIFIUTATA");
//
//		billingStateService.save(payed);
//		billingStateService.save(notPayed);
//		billingStateService.save(waiting);
//		billingStateService.save(refused);
//	}
//}
