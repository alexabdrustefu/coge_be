package it.prova.coge_be.web.api.commessa;

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

import it.prova.coge_be.dto.commessa.CommessaDTO;
import it.prova.coge_be.dto.commessa.CommessaPerInsertDTO;
import it.prova.coge_be.dto.rapportino.RapportinoDTO;
import it.prova.coge_be.dto.rapportino.RapportinoPerInsertDTO;
import it.prova.coge_be.model.Azienda;
import it.prova.coge_be.model.Commessa;
import it.prova.coge_be.model.Rapportino;
import it.prova.coge_be.model.Risorsa;
import it.prova.coge_be.service.azienda.AziendaService;
import it.prova.coge_be.service.commessa.CommessaService;

@RestController
@RequestMapping("api/commessa")
@CrossOrigin
public class CommessaController {

	@Autowired
	private CommessaService commessaService;
	@Autowired
	private AziendaService aziendaService;

	@GetMapping
	public List<CommessaDTO> visualizzaCommesse() {
		return CommessaDTO.createCommessaDTOListFromModelList(commessaService.listAll(), true, false);

	}

	@GetMapping("/{id}")
	public CommessaDTO visualizza(@PathVariable(required = true) Long id) {
		return CommessaDTO.buildCommessaDTOFromModel(commessaService.caricaSingoloElemento(id), false, false);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommessaDTO createNew(@Valid @RequestBody CommessaPerInsertDTO commessaInput) {
		if (commessaInput.getId() != null) {
			throw new RuntimeException();
		}
		Azienda risorsaCaricata = aziendaService.caricaSingolo(commessaInput.getAzienda_id());
		Commessa commessa = new Commessa();
		commessa.setDescrizione(commessaInput.getDescrizione());
		commessa.setCodice(commessaInput.getCodice());
		commessa.setDataIn(commessaInput.getDataIn());
		commessa.setDataOut(commessaInput.getDataOut());
		commessa.setImporto(commessaInput.getImporto());
		commessa.setAzienda(risorsaCaricata);
		Commessa commessaInserito = commessaService.inserisciNuovo(commessa);
		// rapportinoInserito.setRisorsa(risorsaCaricata);

		return CommessaDTO.buildCommessaDTOFromModel(commessaInserito, false, false);
	}

	@PutMapping("{id}")
	public CommessaDTO update(@Valid @RequestBody CommessaDTO commessaInput, @PathVariable(required = true) Long id) {
		Commessa commessa = commessaService.caricaSingoloElemento(id);

		if (commessa == null) {
			throw new RuntimeException();
		}
		
		if (commessaInput.getId() != null && !commessa.getId().equals(commessaInput.getId())) {
			throw new IllegalArgumentException("L'ID dell'azienda non può essere modificato.");
		}
		commessaInput.setId(id);

		commessa.setDescrizione(commessaInput.getDescrizione());
		commessa.setCodice(commessaInput.getCodice());
		commessa.setDataIn(commessaInput.getDataIn());
		commessa.setDataOut(commessaInput.getDataOut());
		commessa.setImporto(commessaInput.getImporto());

		Commessa commessaAggiornata = commessaService.aggiorna(commessa);

		return CommessaDTO.buildCommessaDTOFromModel(commessaAggiornata, false, false);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable(required = true) Long id) {
		commessaService.rimuovi(id);
	}

}
