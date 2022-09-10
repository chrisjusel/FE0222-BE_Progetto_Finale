package it.energyservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	private int anno;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime data;
	
	private double importo;
	private int numero;
	
	@ManyToOne
	@JoinColumn
	private Customer cliente;
}
