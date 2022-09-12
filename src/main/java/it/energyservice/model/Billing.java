package it.energyservice.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int year;
	
	//@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date date;
	
	private double amount;
	private int number;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
	@OneToOne
	private BillingState state;
}
