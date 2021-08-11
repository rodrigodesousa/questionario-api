package com.rodrigo.questionario.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
// import com.rodrigo.questionario.dto.RespostaDTO;
import com.rodrigo.questionario.entities.Resposta;
import com.rodrigo.questionario.services.RespostaService;

@RestController
@RequestMapping(value="/respostas")
public class RespostaResource {
	@Autowired
	private RespostaService respostaService;

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Resposta> buscarResposta(@PathVariable Long id) {
		Resposta obj = respostaService.buscarResposta(id);
		// RespostaDTO objDto = new RespostaDTO(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserirResposta(@RequestBody Resposta obj){
		obj = respostaService.inserirResposta(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterarResposta(@Valid @RequestBody Resposta obj, @PathVariable Long id){
		obj.setId(id);
		obj = respostaService.alterarResposta(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletarResposta(@PathVariable Long id) {
		respostaService.deletarResposta(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Resposta>> listarRespostas(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="numero") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Resposta> list = respostaService.listarRespostas(page, linesPerPage, orderBy, direction);
		// Page<RespostaDTO> listDto = list.map(RespostaDTO::new);
		return ResponseEntity.ok().body(list);
	}
}
