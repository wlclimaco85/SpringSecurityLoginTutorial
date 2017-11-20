package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jogo")
public class Jogo{
	 public enum Confirmacao {
	        SEMANAL, MENSAL, ANUAL, NUNCA
	    }
	 
	 public enum Dias {
	       DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
	    }
	 
	 public enum Status {
	       DISPONIVEL, ACONFIRMAR, OCUPADO, INDISPONIVEL
	    }
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jogo_id")
	private int id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_Jogo", joinColumns = @JoinColumn(name="jogo_id", referencedColumnName="jogo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> user;
	
	@Column(name = "aceitaExterno")
	private String aceitaExterno;
	
	@Column(name = "confirmacao")
	private Confirmacao confirmacao;
	
	@Column(name = "horaInicial")
	private String horaInicial;
	
	@Column(name = "horaFinal")
	private String horaFinal;
	
	@Column(name = "dia")
	private Dias dia;
	
	@Column(name = "status")
	private Status status;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public String getAceitaExterno() {
		return aceitaExterno;
	}
	public void setAceitaExterno(String aceitaExterno) {
		this.aceitaExterno = aceitaExterno;
	}
	public Confirmacao getConfirmacao() {
		return confirmacao;
	}
	public void setConfirmacao(Confirmacao confirmacao) {
		this.confirmacao = confirmacao;
	}
	public String getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}
	public String getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	public Dias getDia() {
		return dia;
	}
	public void setDia(Dias dia) {
		this.dia = dia;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
