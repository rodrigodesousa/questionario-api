package com.rodrigo.questionario.dto;

import java.io.Serializable;

import com.rodrigo.questionario.entities.Questao;
import com.rodrigo.questionario.entities.Questionario;
import com.rodrigo.questionario.entities.TipoQuestao;

public class QuestaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private int numero;
	private String descricao;
	private TipoQuestao tipo;
	private boolean obrigatoria;
	private boolean situacao;
	private int minimoCorretas;
	private boolean alternativaComNumeroOuLetra;
	
	private QuestionarioDTO questionario;
	
	public QuestaoDTO() {
	}
	
	public QuestaoDTO(Questao obj) {
		super();
		this.id = obj.getId();
		this.numero = obj.getNumero();
		this.descricao = obj.getDescricao();
		this.tipo = obj.getTipo();
		this.obrigatoria = obj.isObrigatoria();
		this.situacao = obj.isSituacao();
		this.minimoCorretas = obj.getMinimoCorretas();
		this.alternativaComNumeroOuLetra = obj.isAlternativaComNumeroOuLetra();
		this.setQuestionario(obj.getQuestionario());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoQuestao getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuestao tipo) {
		this.tipo = tipo;
	}

	public boolean isObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public int getMinimoCorretas() {
		return minimoCorretas;
	}

	public void setMinimoCorretas(int minimoCorretas) {
		this.minimoCorretas = minimoCorretas;
	}

	public boolean isAlternativaComNumeroOuLetra() {
		return alternativaComNumeroOuLetra;
	}

	public void setAlternativaComNumeroOuLetra(boolean alternativaComNumeroOuLetra) {
		this.alternativaComNumeroOuLetra = alternativaComNumeroOuLetra;
	}

	public QuestionarioDTO getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = new QuestionarioDTO(questionario);
	}
}
