package it.energyservice.model.dto.customer;

import java.sql.Date;

import it.energyservice.model.CustomerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRequest {

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
	private CustomerType tipoCliente;

	private AddressRequest indirizzoSedeOperativa;
	private AddressRequest indirizzoSedeLegale;

}
