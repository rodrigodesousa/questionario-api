package com.rodrigo.questionario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.questionario.entities.Resposta;
import com.rodrigo.questionario.repositories.RespostaRepository;

@Service
public class RespostaService {
	@Autowired
	private RespostaRepository respostaRepository;

	@Autowired
	private QuestaoService questaoService;

	public Resposta buscarResposta(Long id) {
		Optional<Resposta> obj = respostaRepository.findById(id);
		return obj.orElse(null);
	}
	public Resposta inserirResposta(Resposta obj) {
		obj.setId(null);
		obj.setQuestao(questaoService.buscarQuestao(obj.getQuestao().getId()));
		return respostaRepository.save(obj);
	}
	public Resposta alterarResposta(Resposta obj) {
		buscarResposta(obj.getId());
		return respostaRepository.save(obj);
	}
	public void deletarResposta(Long id) {
		buscarResposta(id);
		respostaRepository.deleteById(id);
	}
	public Page<Resposta> listarRespostas(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return respostaRepository.findAll(pageRequest);
	}
}
