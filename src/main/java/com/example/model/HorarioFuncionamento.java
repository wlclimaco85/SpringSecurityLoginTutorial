package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "horarioFunc")
public class HorarioFuncionamento{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "horarioFunc_id")
	private int id;
	@Column(name = "parent_id")
	private int parentId;
	
	@Column(name = "horarios_id")
	private int horariosId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getHorariosId() {
		return horariosId;
	}
	public void setHorariosId(int horariosId) {
		this.horariosId = horariosId;
	}
	
	
}
