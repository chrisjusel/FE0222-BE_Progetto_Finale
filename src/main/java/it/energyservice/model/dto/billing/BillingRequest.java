package it.energyservice.model.dto.billing;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillingRequest {

	private Long id;
	private int anno;
	private Date data;
	private double importo;
	private int numero;

	private Long cliente;

	private String stato;
}
