package it.prova.coge_be.dto.risorsa;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.coge_be.dto.rapportino.RapportinoPerInsertDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RisorsaPerInnserDTO {
	private Long id;

	@NotNull(message = "{numeroGiorni.notnull}")
	@Min(0)
	private String nome;
	private String cognome;
	private LocalDate dataIn;

	private LocalDate dataOut;
	private String cf;
	private String email;

	private Integer costoGiornaliero;

	private Long risorsa_id;
	private Long cv_id;

}
