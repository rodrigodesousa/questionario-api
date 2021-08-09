package com.rodrigo.questionario.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.questionario.dto.QuestaoDTO;
import com.rodrigo.questionario.entities.Questao;
import com.rodrigo.questionario.services.QuestaoService;

@RestController
@RequestMapping(value="/questoes")
public class QuestaoResource {

	@Autowired
	private QuestaoService questaoService;

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<QuestaoDTO> buscarQuestao(@PathVariable Long id) {
		Questao obj = questaoService.buscarQuestao(id);
		QuestaoDTO objDto = new QuestaoDTO(obj);
		return ResponseEntity.ok().body(objDto);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserirQuestao(@RequestBody Questao obj){
		obj = questaoService.inserirQuestao(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterarQuestao(@Valid @RequestBody Questao obj, @PathVariable Long id){
		obj.setId(id);
		obj = questaoService.alterarQuestao(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletarQuestao(@PathVariable Long id) {
		questaoService.deletarQuestao(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<QuestaoDTO>> listarQuestoes(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="numero") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Questao> list = questaoService.listarQuestaos(page, linesPerPage, orderBy, direction);
		Page<QuestaoDTO> listDto = list.map(QuestaoDTO::new);
		return ResponseEntity.ok().body(listDto);
	}
}
