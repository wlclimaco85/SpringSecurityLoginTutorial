package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.model.Jogo.Dias;

@Entity
@Table(name = "jogo_por_data")
public class JogoPorData{
	 public enum StatusJogoPorData {
	       CONFIRMADO, ACONFIRMAR, NAOVO, TALVEZ
	    }
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jogoPorData_id")
	private Integer id;
	
	@Column(name = "Data")
	private Date data;
	
	@Column(name = "jogo_id")
	private Integer jogoId;
	
	@Column(name = "status")
	private StatusJogoPorData status;
	
	@Column(name = "nota")
	private String nota;
	
	@Column(name = "qnt_gols")
	private Integer qntGols;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getJogoId() {
		return jogoId;
	}

	public void setJogoId(Integer jogoId) {
		this.jogoId = jogoId;
	}

	public StatusJogoPorData getStatus() {
		return status;
	}

	public void setStatus(StatusJogoPorData status) {
		this.status = status;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getQntGols() {
		return qntGols;
	}

	public void setQntGols(Integer qntGols) {
		this.qntGols = qntGols;
	}

	

	public JogoPorData(Date data, Integer jogoId, Integer userId, StatusJogoPorData status, String nota, Integer qntGols,
			int quadraId, String horaInicial, String horaFinal, Dias dia) {
		super();
		this.data = data;
		this.jogoId = jogoId;
		this.status = status;
		this.nota = nota;
		this.qntGols = qntGols;

	}

	public JogoPorData() {
		super();
	}
	
	
		
}
