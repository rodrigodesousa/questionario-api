package com.rodrigo.questionario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.questionario.entities.Questao;
import com.rodrigo.questionario.repositories.QuestaoRepository;

@Service
public class QuestaoService {
	
	@Autowired
	private QuestaoRepository questaoRepository;

	@Autowired
	private QuestionarioService questionarioService;

	public Questao buscarQuestao(Long id) {
		Optional<Questao> obj = questaoRepository.findById(id);
		return obj.orElse(null);
	}
	public Questao inserirQuestao(Questao obj) {
		obj.setId(null);
		obj.setQuestionario(questionarioService.buscarQuestionario(obj.getQuestionario().getId()));
		return questaoRepository.save(obj);
	}
	public Questao alterarQuestao(Questao obj) {
		buscarQuestao(obj.getId());
		return questaoRepository.save(obj);
	}
	public void deletarQuestao(Long id) {
		buscarQuestao(id);
		questaoRepository.deleteById(id);
	}
	public Page<Questao> listarQuestaos(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return questaoRepository.findAll(pageRequest);
	}
	public List<Questao> listarQuestoesPorQuestionarioId(Long questionario_id) {
		return questaoRepository.findByQuestionario(questionario_id);
	}
}
