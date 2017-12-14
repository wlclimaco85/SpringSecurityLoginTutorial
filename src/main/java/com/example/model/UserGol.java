package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_gols")
public class UserGol{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_gols_id")
	private Integer id;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "quant_gol")
	private Integer quantGol;
	
	@Column(name = "user_id")
	private Integer userId;

	
	
	public UserGol() {
		super();
	}

	public UserGol(Date data, Integer string, Integer userId) {
		super();
		this.data = data;
		this.quantGol = string;
		this.userId = userId;
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

	public Integer getQuantGol() {
		return quantGol;
	}

	public void setQuantGol(Integer quantGol) {
		this.quantGol = quantGol;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	
}
