package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_confirmacao")
public class UserConfirmar{

	 
	 public enum Status {
	       CONFIRMADO, ACONFIRMAR, NAOVO, TALVEZ
	    }
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_confirmacao_id")
	private int id;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "status")
	private Status status;
	
	@Column(name = "user_id")
	private Integer userId;
	
	

	public UserConfirmar() {
		super();
	}

	public UserConfirmar(Date data, Status status, Integer userId) {
		super();
		this.data = data;
		this.status = status;
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
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
