package it.prova.coge_be.dto.rapportino;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

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
public class RapportinoPerInsertDTO {
private Long id;

@NotNull(message = "{numeroGiorni.notnull}")
@Min(0)
private Integer numeroGiorni;

private Long risorsa_id;

}