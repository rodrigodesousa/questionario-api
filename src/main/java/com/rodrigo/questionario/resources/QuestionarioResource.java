package com.rodrigo.questionario.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.rodrigo.questionario.dto.QuestionarioDTO;
import com.rodrigo.questionario.entities.Questionario;
import com.rodrigo.questionario.services.QuestionarioService;

@RestController
@RequestMapping(value="/questionarios")
public class QuestionarioResource {
	
	@Autowired
	private QuestionarioService questionarioService;
	
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<QuestionarioDTO>> listarTodosQuestionarios() {
		List<Questionario> list = questionarioService.listarTodosQuestionarios();
		List<QuestionarioDTO> listDTO = list.stream().map(obj -> new QuestionarioDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<QuestionarioDTO>> listarQuestionarios(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Questionario> list = questionarioService.listarQuestionarios(page, linesPerPage, orderBy, direction);
		Page<QuestionarioDTO> listDTO = list.map(obj -> new QuestionarioDTO(obj));

		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Questionario> buscarQuestionario(@PathVariable Long id) {
		Questionario obj = questionarioService.buscarQuestionario(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserirQuestionario(@RequestBody Questionario obj){
		obj = questionarioService.inserirQuestionario(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterarQuestionario(@RequestBody Questionario obj, @PathVariable Long id){
		obj.setId(id);
		obj = questionarioService.alterarQuestionario(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletarQuestionario(@PathVariable Long id) {
		questionarioService.deletarQuestionario(id);

		return ResponseEntity.noContent().build();
	}
}
