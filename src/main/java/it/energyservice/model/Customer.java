package it.energyservice.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	private String ragioneSociale;
	private String partitaIva;
	private String email;
	private Date dataInserimento;
	private Date dataUltimoContatto;
	private double fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address indirizzoSedeOperativa;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address indirizzoSedeLegale;
	
	@Enumerated(EnumType.STRING)
	private CustomerType tipoCliente;
	
	@OneToMany(mappedBy = "cliente")
	private List<Billing> fatture = new ArrayList<>();
}
