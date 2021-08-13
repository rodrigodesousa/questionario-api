package com.rodrigo.questionario.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Questao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoQuestao tipo;
	private boolean obrigatoria;
	private boolean situacao;
	
	@ManyToOne
	@JoinColumn(name="questionario_id")
	private Questionario questionario;
	
	@Column(nullable = true)
	private int minimoCorretas;
	
	@Column(nullable = true)
	private boolean alternativaComNumeroOuLetra;
	
	@OneToMany(mappedBy="questao")
	private List<Resposta> respostas = new ArrayList<>();
	
	@OneToMany(mappedBy="questao", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Alternativa> alternativas = new ArrayList<>();
	
	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public Questao() {
	}

	public Questao(Long id, int numero, String descricao, TipoQuestao tipo, boolean obrigatoria, boolean situacao,
			Questionario questionario, int minimoCorretas, boolean alternativaComNumeroOuLetra,
			List<Resposta> respostas, List<Alternativa> alternativas) {
		super();
		this.id = id;
		this.numero = numero;
		this.descricao = descricao;
		this.tipo = tipo;
		this.obrigatoria = obrigatoria;
		this.situacao = situacao;
		this.questionario = questionario;
		this.minimoCorretas = minimoCorretas;
		this.alternativaComNumeroOuLetra = alternativaComNumeroOuLetra;
		this.respostas = respostas;
		this.alternativas = alternativas;
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

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questao other = (Questao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
