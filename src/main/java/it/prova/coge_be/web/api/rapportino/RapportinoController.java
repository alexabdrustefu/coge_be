	package it.prova.coge_be.web.api.rapportino;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.coge_be.dto.rapportino.RapportinoDTO;
import it.prova.coge_be.dto.rapportino.RapportinoPerInsertDTO;
import it.prova.coge_be.model.Rapportino;
import it.prova.coge_be.model.Risorsa;
import it.prova.coge_be.service.rapportino.RapportinoService;
import it.prova.coge_be.service.risorsa.RisorsaService;

@RestController
@RequestMapping("api/rapportino")
@CrossOrigin
public class RapportinoController {
	
	
		
		@Autowired
		private RapportinoService rapportinoService;
		@Autowired
		private RisorsaService risorsaService;
		@GetMapping
		public List<RapportinoDTO> visualizzaRapportini(){
			return RapportinoDTO.createRapportinoDTOListFromModelList(rapportinoService.listAll(), false, false);
			
		}
		
		@GetMapping("/{id}")
		public RapportinoDTO visualizza (@PathVariable(required = true) Long id,RapportinoPerInsertDTO rapportinoInput) {
			return RapportinoDTO.buildRapportinoDTOFromModel(rapportinoService.caricaSingoloElemento(id), true, true);
		
		}
		
		
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public RapportinoDTO createNew(@Valid @RequestBody RapportinoPerInsertDTO rapportinoInput) {
		if(rapportinoInput.getId() != null) {
		throw new RuntimeException();
		}
		Risorsa risorsaCaricata = risorsaService.caricaSingoloElemento(rapportinoInput.getRisorsa());
		Rapportino rapportino = new Rapportino();
		rapportino.setNumeroGiorni(rapportinoInput.getNumeroGiorni());
		rapportino.setRisorsa(risorsaCaricata);
		Rapportino rapportinoInserito = rapportinoService.inserisciNuovo(rapportino);
		// rapportinoInserito.setRisorsa(risorsaCaricata);

		return RapportinoDTO.buildRapportinoDTOFromModel(rapportinoInserito, false, false);
		}
		@PutMapping("{id}")
		public RapportinoDTO update (@Valid @RequestBody RapportinoDTO rapportinoInput, @PathVariable(required = true) Long id) {
			Rapportino rapportino = rapportinoService.caricaSingoloElemento(id);
			
			if(rapportino == null) {
				throw new RuntimeException();
			}
			rapportinoInput.setId(id);
			
			Rapportino rapportinoAggiornato = rapportinoService.aggiorna(rapportinoInput.buildRapportinoModel());
			
			return RapportinoDTO.buildRapportinoDTOFromModel(rapportinoAggiornato, false, false);
		
	    }
		
			@DeleteMapping("{id}")
		public void delete(@PathVariable(required = true) Long id) {
			rapportinoService.rimuovi(id);
		}
  }
	
	
	
	
