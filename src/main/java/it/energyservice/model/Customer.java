package it.energyservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String companyName;
	private String vatNumber;
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date insertionDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastContactDate;

	private double annualTurnover;
	private String pec;
	private String phone;
	private String contactEmail;
	private String contactName;
	private String contactSurname;
	private String contactPhone;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address operatingSiteAddress;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address legalSiteAddress;

	@Enumerated(EnumType.STRING)
	private CustomerType customerType;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Billing> billings = new ArrayList<>();
}
