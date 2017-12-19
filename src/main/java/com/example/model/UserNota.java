package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_nota")
public class UserNota{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_nota_id")
	private Integer id;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "nota")
	private String nota;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "jogo_id")
	private Integer jogoId;

	public UserNota(Date data, String nota, Integer userId, Integer jogoId) {
		super();
		this.data = data;
		this.nota = nota;
		this.userId = userId;
		this.jogoId = jogoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getJogoId() {
		return jogoId;
	}

	public void setJogoId(Integer jogoId) {
		this.jogoId = jogoId;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
