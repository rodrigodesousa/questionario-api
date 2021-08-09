package com.rodrigo.questionario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.questionario.entities.Questionario;
import com.rodrigo.questionario.repositories.QuestionarioRepository;

@Service
public class QuestionarioService {

	@Autowired
	QuestionarioRepository questionarioRepository;
	
	public Questionario buscarQuestionario(Long id) {
		Optional<Questionario> obj = questionarioRepository.findById(id);
		return obj.orElse(null);
	}
	
	public Questionario inserirQuestionario(Questionario obj) {
		obj.setId(null);
		return questionarioRepository.save(obj);
	}
	
	public Questionario alterarQuestionario(Questionario obj) {
		buscarQuestionario(obj.getId());
		return questionarioRepository.save(obj);
	}
	public void deletarQuestionario(Long id) {
		buscarQuestionario(id);
		questionarioRepository.deleteById(id);
	}
	
	public Page<Questionario> listarQuestionarios(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return questionarioRepository.findAll(pageRequest);
	}
	
}
