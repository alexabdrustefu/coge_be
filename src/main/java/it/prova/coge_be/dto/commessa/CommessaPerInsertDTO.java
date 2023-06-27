package it.prova.coge_be.dto.commessa;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
// Json include, se un dato è nullo non cerrà messo nell'output
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommessaPerInsertDTO {
	private Long id;
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotBlank(message = "{codice.notblank}")
	private String codice;

	@NotNull(message = "{dataIn.notnull}")
	private LocalDate dataIn;
	@NotNull(message = "{dataOut.notnull}")
	private LocalDate dataOut;

	@NotNull(message = "{importo.notnull}")
	@Min(0)
	private Double importo;
	private Long azienda_id;
	

}
