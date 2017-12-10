package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	       DISPONIVEL, ACONFIRMAR, OCUPADO, INDISPONIVEL, CONFIRMAR, DESMARCAR
	    }
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jogo_id")
	private int id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descricao")
	private String descricao;
	
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@JoinColumn(name="para_jogo_id", referencedColumnName="jogo_id")
//	private List<Notificacoes> notificacoes;
	
	//@ManyToMany(cascade = CascadeType.ALL)
	//@JoinTable(name = "user_jogos", joinColumns = @JoinColumn(name = "jogo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name="para_jogo_id", referencedColumnName="jogo_id", nullable = false, insertable = false, updatable = false)
//	private List<Notificacoes> notificacoes;

//	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
//	@JoinColumn(name="user_id", referencedColumnName="user_id", nullable = false, insertable = false, updatable = false)
//	private User userId;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_jogos", joinColumns = @JoinColumn(name = "jogo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_jogos", joinColumns = @JoinColumn(name="jogo_id",  insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> usersJogo;
	
//	@Column(name = "user_id")
//	private int user_id;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "user_jogos",  joinColumns = {
//			@JoinColumn(name = "jogo_id", nullable = false, updatable = false) },
//			inverseJoinColumns = { @JoinColumn(name = "user_id",
//					nullable = false, updatable = false) })
//	private Set<User> user;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_jogos", joinColumns = @JoinColumn(name = "jogo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private List<User> users;
	
	@Column(name = "aceitaExterno")
	private String aceitaExterno;
	
	@Column(name = "confirmacao")
	private Confirmacao confirmacao;
	
	@Column(name = "quadra_id")
	private int quadraId;
	
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
	public int getQuadraId() {
		return quadraId;
	}
	public void setQuadraId(int quadraId) {
		this.quadraId = quadraId;
	}

	public List<User> getUsersJogo() {
		return usersJogo;
	}
	public void setUsersJogo(List<User> usersJogo) {
		this.usersJogo = usersJogo;
	}

	
}
